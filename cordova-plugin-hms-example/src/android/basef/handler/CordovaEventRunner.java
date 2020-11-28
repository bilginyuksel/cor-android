package com.huawei.hms.cordova.example.basef.handler;

import android.app.Activity;
import android.util.Log;

import org.apache.cordova.CordovaWebView;
import org.json.JSONArray;
import org.json.JSONObject;

public class CordovaEventRunner {
	private static final String TAG = CordovaEventRunner.class.getName();

	private final HMSLogger hmsLogger;
	private final CordovaWebView webView;
	private final Activity activity;

	CordovaEventRunner(final CordovaWebView cordovaWebView, final Activity activity, final HMSLogger hmsLogger) {
		this.hmsLogger = hmsLogger;
		this.webView = cordovaWebView;
		this.activity = activity;
	}

	public void sendEvent(String event, JSONObject... params) {
		hmsLogger.sendPeriodicEvent(event);
		sendEventToJS(event, (Object[]) params);
	}

	public void sendEvent(String event, JSONArray... params) {
		hmsLogger.sendPeriodicEvent(event);
		sendEventToJS(event, (Object[]) params);
	}

	public void sendEvent(String event) {
		hmsLogger.sendPeriodicEvent(event);
		sendEventToJS(event);
	}

	private void sendEventToJS(String event, Object... objects) {
		Log.i(TAG,"Periodic event "+ event +" captured and event "+ event +" is sending to JS.");
		StringBuilder jsFunctionBuilder = new StringBuilder();
		jsFunctionBuilder.append("javascript:");
		jsFunctionBuilder.append("window.runHMSEvent('%s'");
		if(objects.length>0) jsFunctionBuilder.append(buildJSEventParameters(objects));
		jsFunctionBuilder.append(");");

		activity.runOnUiThread(() -> {
			webView.loadUrl(jsFunctionBuilder.toString());
		});
	}

	private String buildJSEventParameters(Object... objects) {
		StringBuilder eventParametersBuilder = new StringBuilder();

		for (Object obj : objects)
			eventParametersBuilder.append(obj.toString()).append(",");

		eventParametersBuilder.deleteCharAt(eventParametersBuilder.length() - 1); // delete comma.
		return eventParametersBuilder.toString();
	}
}
