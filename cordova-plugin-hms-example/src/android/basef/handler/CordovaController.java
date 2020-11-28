package com.huawei.hms.cordova.example.basef.handler;

import android.util.Log;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;
import com.huawei.hms.cordova.example.basef.HMSLog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CordovaController {
	private static final String TAG = CordovaController.class.getSimpleName();

	private CordovaModuleGroupHandler groupHandler;
	private final HMSLogger hmsLogger;
	private final CordovaEventRunner eventRunner;
	private final CordovaPlugin cordovaPlugin;
	private final List<String> moduleReferences = new ArrayList<>();

	public <T extends CordovaBaseModule> CordovaController(CordovaPlugin cordovaPlugin,
														   String service, String version, List<T> cordovaModules) {
		List<CordovaModuleHandler> moduleHandlerList = new ArrayList<>();
		for (T cordovaModule : cordovaModules) {
			CordovaModuleHandler moduleHandler = new CordovaModuleHandler(cordovaModule);
			moduleHandlerList.add(moduleHandler);
			moduleReferences.add(cordovaModule.getReference());
		}
		this.cordovaPlugin = cordovaPlugin;
		this.groupHandler = new CordovaModuleGroupHandler(moduleHandlerList);
		this.hmsLogger = HMSLogger.getInstance(cordovaPlugin.webView.getContext(), service, version);
		this.eventRunner = new CordovaEventRunner(cordovaPlugin.webView, cordovaPlugin.cordova.getActivity(), hmsLogger);

		prepareEvents();
		clearEventCache();
	}

	private void prepareEvents() {
		for (String ref : moduleReferences) {
			List<Method> eventCache = groupHandler.getCordovaModuleHandler(ref).getEventCache();
			runAllEventMethods(groupHandler.getCordovaModuleHandler(ref).getInstance(), eventCache);
		}
	}

	private <T> void runAllEventMethods(T instance, List<Method> eventCache) {
		for (Method method : eventCache) {
			try {
				method.invoke(instance, new CorPack(cordovaPlugin, eventRunner));
				Log.i(TAG, "Event " + method.getName() + " is ready.");
			} catch (IllegalAccessException | InvocationTargetException e) {
				Log.e(TAG, "Event couldn't initialized. " + e.getMessage());
			}
		}
	}

	private void clearEventCache() {
		for (String ref : moduleReferences)
			groupHandler.getCordovaModuleHandler(ref).getEventCache().clear();
	}

	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		try {
			CordovaModuleHandler moduleHandler = groupHandler.getCordovaModuleHandler(action);
			Log.i(TAG, "Module " + action + " called.");
			String methodName = args.getString(0); // JSONException if not exists
			Method method = moduleHandler.getModuleMethod(methodName);
			Log.i(TAG, "Method " + methodName + " called of module " + action + ".");
			args.remove(0);
			boolean isLoggerActive = false;
			if (method.isAnnotationPresent(HMSLog.class)) {
				isLoggerActive = true;
				hmsLogger.startMethodExecutionTimer(methodName);
			}
			CorPack corPack = new CorPack(cordovaPlugin, eventRunner);
			Promise promise = createPromiseFromCallbackContext(callbackContext, methodName, isLoggerActive);
			method.invoke(moduleHandler.getInstance(), corPack, args, promise);
			return true;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | JSONException e) {
			Log.e(TAG, "Error captured when execute method run for reference= " + action);
			Log.e(TAG, e.getMessage() + " ---- " + e.getClass().getSimpleName());
			callbackContext.error(e.getMessage()); // is it necessary ???
			return false;
		}
	}

	private Promise createPromiseFromCallbackContext(final CallbackContext callbackContext, String methodName, boolean isLoggerActive) {
		final Promise promise = new Promise(callbackContext);
		promise.setHmsLogger(hmsLogger);
		promise.setMethodName(methodName);
		promise.setLoggerRunning(isLoggerActive);
		return promise;
	}

}

