package com.huawei.hms.cordova.example.basef.handler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaWebView;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

public class Promise {

	private final CallbackContext callbackContext;

	private HMSLogger hmsLogger;
	private String methodName;
	private boolean isLoggerRunning;

	public Promise(final CallbackContext callbackContext) {
		this.callbackContext = callbackContext;
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

	public void success() {
		callbackContext.success();
		sendLogEvent(null);
	}

	public void success(int message) {
		callbackContext.success(message);
		sendLogEvent("" + message);
	}

	public void success(byte[] message) {
		callbackContext.success(message);
		sendLogEvent(message.toString());
	}

	public void success(String message) {
		callbackContext.success(message);
		sendLogEvent(message);
	}

	public void success(JSONArray message) {
		callbackContext.success(message);
		sendLogEvent(message.toString());
	}

	public void success(JSONObject message) {
		callbackContext.success(message);
		sendLogEvent(message.toString());
	}

	public void success(boolean message) {
		callbackContext.sendPluginResult(new PluginResult(PluginResult.Status.OK, message));
		sendLogEvent("" + message);
	}

	public void error(int message) {
		callbackContext.error(message);
		sendLogEvent("" + message);
	}

	public void error(String message) {
		callbackContext.error(message);
		sendLogEvent(message);
	}

	public void error(JSONObject message) {
		callbackContext.error(message);
		sendLogEvent(message.toString());
	}


	private void sendLogEvent(String nullable) {
		if (!isLoggerRunning) return;
		if (nullable == null) hmsLogger.sendSingleEvent(methodName);
		else hmsLogger.sendSingleEvent(methodName, nullable);
	}

}