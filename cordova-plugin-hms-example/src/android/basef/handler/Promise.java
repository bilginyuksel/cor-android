package com.huawei.hms.cordova.example.basef.handler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

public class Promise extends CallbackContext {

	private HMSLogger hmsLogger;
	private String methodName;
	private boolean isLoggerRunning;

	public Promise(String callbackId, CordovaWebView webView) {
		super(callbackId, webView);
	}

	public void setHmsLogger(HMSLogger hmsLogger) {
		this.hmsLogger = hmsLogger;
	}

	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}

	public void setLoggerRunning(boolean loggerRunning) {
		isLoggerRunning = loggerRunning;
	}

	@Override
	public void success() {
		super.success();
		sendLogEvent(null);
	}

	@Override
	public void success(int message) {
		super.success(message);
		sendLogEvent("" + message);
	}

	@Override
	public void success(byte[] message) {
		super.success(message);
		sendLogEvent(message.toString());
	}

	@Override
	public void success(String message) {
		super.success(message);
		sendLogEvent(message);
	}

	@Override
	public void success(JSONArray message) {
		super.success(message);
		sendLogEvent(message.toString());
	}

	@Override
	public void success(JSONObject message) {
		super.success(message);
		sendLogEvent(message.toString());
	}

	@Override
	public void error(int message) {
		super.error(message);
		sendLogEvent("" + message);
	}

	@Override
	public void error(String message) {
		super.error(message);
		sendLogEvent(message);
	}

	@Override
	public void error(JSONObject message) {
		super.error(message);
		sendLogEvent(message.toString());
	}

	private void sendLogEvent(String nullable) {
		if (!isLoggerRunning) return;
		if (nullable == null) hmsLogger.sendSingleEvent(methodName);
		else hmsLogger.sendSingleEvent(methodName, nullable);
	}

	// NOT RECOMMENDED
	@Override
	public void sendPluginResult(PluginResult pluginResult) {
		super.sendPluginResult(pluginResult);
	}
}