package com.cor.android.framework.basef.handler;

import android.app.Activity;

import org.apache.cordova.CordovaWebView;

public class CordovaEventRunner {
	private static final String TAG = CordovaEventRunner.class.getName();
	private static final String TO_STR_NOT_VALID_ERR = "Sent event parameter value is not valid! Please add toString() method to the object you " +
			"are passing or do not pass this object as an event parameter.";
	private final CordovaWebView webView;
	private final Activity activity;

	protected CordovaEventRunner(final CordovaWebView cordovaWebView, final Activity activity) {
		this.webView = cordovaWebView;
		this.activity = activity;
	}

	public void sendEvent(String event, Object... params) {
		sendEventToJS(event, params);
	}

	public void sendEvent(String event) {
		sendEventToJS(event);
	}

	private void sendEventToJS(String event, Object... objects) {
		CorLog.info(TAG, "Periodic event " + event + " captured and event " + event + " is sending to JS.");
		StringBuilder jsFunctionBuilder = new StringBuilder();
		jsFunctionBuilder.append("javascript:");
		jsFunctionBuilder.append("window.runHMSEvent('").append(event).append("'");
		if (objects.length > 0) jsFunctionBuilder.append(buildJSEventParameters(objects));
		jsFunctionBuilder.append(");");
		activity.runOnUiThread(() -> webView.loadUrl(jsFunctionBuilder.toString()));
	}

	private String buildJSEventParameters(Object... objects) {
		StringBuilder eventParametersBuilder = new StringBuilder();
		for (Object obj : objects) {
			if (!isToStringValueValid(obj))
				CorLog.warn(TAG, TO_STR_NOT_VALID_ERR);
			eventParametersBuilder.append(",").append(obj.toString());
		}
		return eventParametersBuilder.toString();
	}

	private boolean isToStringValueValid(Object object) {
		String originalToStr = object.getClass().getSimpleName() + "@" + Integer.toHexString(object.hashCode());
		String currentToStr = object.toString();
		return originalToStr.equals(currentToStr);
	}
}


