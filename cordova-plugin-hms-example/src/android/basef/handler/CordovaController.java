package com.huawei.hms.cordova.example.basef.handler;


import android.content.Context;
import android.util.Log;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;
import com.huawei.hms.cordova.example.basef.HMSLog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CordovaController {
	private static final String TAG = CordovaController.class.getSimpleName();

	private CordovaModuleGroupHandler groupHandler;
	private final HMSLogger hmsLogger;
	private final CordovaEventRunner eventRunner;
	private final CordovaInterface cordova;
	private final CordovaWebView webView;
	private final List<String> moduleReferences = new ArrayList<>();

	public <T extends CordovaBaseModule> CordovaController(CordovaInterface cordova, CordovaWebView webView,
														   String service, String version, List<T> cordovaModules) {
		List<CordovaModuleHandler> cordovaModuleHandlers = new ArrayList<>();
		for (T cordovaModule : cordovaModules) {
			CordovaModuleHandler cordovaModuleHandler = new CordovaModuleHandler(cordovaModule);
			cordovaModuleHandlers.add(cordovaModuleHandler);
			moduleReferences.add(cordovaModule.getReference());
		}
		this.webView = webView;
		this.cordova = cordova;
		this.groupHandler = new CordovaModuleGroupHandler(cordovaModuleHandlers);
		this.hmsLogger = HMSLogger.getInstance(webView.getContext(), service, version);
		this.eventRunner = CordovaEventRunner.getInstance(hmsLogger);

		prepareEvents();
	}

	private void prepareEvents() {
		for(String ref : moduleReferences) {
			List<Method> eventCache = groupHandler.getCordovaModuleHandler(ref).getEventCache();
			runAllEventMethods(groupHandler.getCordovaModuleHandler(ref).getInstance(), eventCache);
		}
	}

	private <T> void runAllEventMethods(T instance, List<Method> eventCache) {
		for(Method method : eventCache) {
			try {
				method.invoke(instance, eventRunner);
				Log.i(TAG, "runAllEventMethods: " + method.getName() + " ready.");
			} catch (IllegalAccessException | InvocationTargetException e) {
				Log.e(TAG, "runAllEventMethods: " );
			}
		}
	}

	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		try {
			CordovaModuleHandler moduleHandler = groupHandler.getCordovaModuleHandler(action);
			String methodName = args.optString(0);
			Method method = moduleHandler.getModuleMethod(methodName);



			args.remove(0);
			boolean isLoggerActive = false;
			if (method.isAnnotationPresent(HMSLog.class)) {
				isLoggerActive = true;
				hmsLogger.startMethodExecutionTimer(methodName);
			}
			method.invoke(moduleHandler.getInstance(), args,
					convertCallbackToPromise(callbackContext, methodName, isLoggerActive));
			return true;
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
			return false;
		}
	}

	private Promise convertCallbackToPromise(final CallbackContext callbackContext, String methodName, boolean isLoggerActive) {
		final Promise myCallback = new Promise(callbackContext); //(Promise) callbackContext;
		myCallback.setHmsLogger(hmsLogger);
		myCallback.setMethodName(methodName);
		myCallback.setLoggerRunning(isLoggerActive);
		return myCallback;
	}

//    class CallbackResultHandlerThread extends Thread{
//        private long executionStartingTime;
//        private final CallbackContext callbackContext;
//        private Object hmsLogger;
//        private final long timeout = 3000;
//        public CallbackResultHandlerThread(final CallbackContext callbackContext, Object hmsLogger){
//            this.callbackContext=callbackContext;
//            this.hmsLogger=hmsLogger;
//            //startExecution
//            executionStartingTime=System.currentTimeMillis();
//        }
//
//        @Override
//        public void run() {
//            long passedTime = System.currentTimeMillis()-executionStartingTime;
//            while ((passedTime <= timeout) && !callbackContext.isFinished())
//                passedTime =  System.currentTimeMillis()-executionStartingTime;
//
//            //sendEvent
//            Log.i("Logger ", "sendEvent -- time passed (ms)= " + (System.currentTimeMillis()-executionStartingTime));
//        }
//    }

}

