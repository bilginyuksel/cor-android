package com.huawei.hms.cordova.example;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;
import org.json.JSONException;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;
import com.huawei.hms.cordova.example.basef.handler.CordovaController;
import com.huawei.hms.cordova.example.basef.handler.Promise;

import java.util.Arrays;

public class HMSExample extends CordovaPlugin {

	private final static String TAG = HMSExample.class.getSimpleName();
	private CordovaController cordovaController;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		cordovaController = new CordovaController(cordova, webView, "ExamplePlugin", "1.0.0",
				Arrays.asList(new CordovaBaseModule[]{
						new Test1(webView.getContext(), cordova.getActivity())
				}));

	}

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
		return cordovaController.execute(action, args, callbackContext);
	}
}
