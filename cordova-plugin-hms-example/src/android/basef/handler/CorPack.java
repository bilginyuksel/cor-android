package com.huawei.hms.cordova.example.basef.handler;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class CorPack {
	private final CordovaPlugin cordovaPlugin;
	public final CordovaWebView webView;
	public final CordovaInterface cordova;
	public final CordovaEventRunner eventRunner;

	CorPack(final CordovaPlugin cordovaPlugin, final CordovaEventRunner eventRunner) {
		this.cordovaPlugin = cordovaPlugin;
		this.webView = cordovaPlugin.webView;
		this.cordova = cordovaPlugin.cordova;
		this.eventRunner = eventRunner;
	}

	public void requestPermission(int requestCode, String permission) {
		cordova.requestPermission(cordovaPlugin, requestCode, permission);
	}

	public boolean hasPermission(String permission) {
		return cordova.hasPermission(permission);
	}

}
