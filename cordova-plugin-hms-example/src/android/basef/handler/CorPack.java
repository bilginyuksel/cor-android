package com.huawei.hms.cordova.example.basef.handler;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaWebView;

public class CorPack {
	public final CordovaWebView webView;
	public final CordovaInterface cordova;
	public final CordovaEventRunner eventRunner;

	CorPack(final CordovaWebView webView, final CordovaInterface cordova, final CordovaEventRunner eventRunner) {
		this.webView = webView;
		this.cordova = cordova;
		this.eventRunner = eventRunner;
	}
}
