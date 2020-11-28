package com.huawei.hms.cordova.example;

import org.apache.cordova.CordovaWebView;
import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaInterface;

import org.json.JSONArray;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;
import com.huawei.hms.cordova.example.basef.handler.CordovaController;

import java.util.Arrays;

public class HMSExample extends CordovaPlugin {

	private CordovaController cordovaController;

	@Override
	public void initialize(CordovaInterface cordova, CordovaWebView webView) {
		super.initialize(cordova, webView);
		final String SERVICE = "CordovaExample";
		final String VERSION = "5.0.0.300";
		cordovaController = new CordovaController(this, SERVICE, VERSION,
				Arrays.asList(new CordovaBaseModule[]{
						new Test1(webView.getContext(), cordova.getActivity())
				}));
	}

	@Override
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
		return cordovaController.execute(action, args, callbackContext);
	}
}
