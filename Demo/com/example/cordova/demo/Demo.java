package com.example.cordova.demo;

import android.util.Log;
import android.widget.Toast;

import com.example.cordova.demo.basef.CordovaBaseModule;
import com.example.cordova.demo.basef.handler.CordovaController;

import org.json.JSONArray;
import org.json.JSONException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class Demo extends CordovaPlugin {

	private final static String TAG = Demo.class.getSimpleName();
	private CordovaController cordovaController;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		cordovaController = new CordovaController(Arrays.asList(new CordovaBaseModule[]{

		}));
	}

	@Override
	public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
		return cordovaController.execute(action, args, callbackContext);
	}

	private void showToast(JSONArray args, final CallbackContext callbackContext) {
		String message = args.optString(0);
		Toast.makeText(webView.getContext(), message, Toast.LENGTH_SHORT).show();
		callbackContext.success();
	}
}