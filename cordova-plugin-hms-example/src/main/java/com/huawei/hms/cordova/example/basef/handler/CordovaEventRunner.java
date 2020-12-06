package com.huawei.hms.cordova.ads.basef.handler;

import android.app.Activity;
import android.util.Log;

import org.apache.cordova.CordovaWebView;

import java.util.Locale;

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

    public void sendEvent(String event, Object... params) {
        hmsLogger.sendPeriodicEvent(event);
        sendEventToJS(event, params);
    }

    public void sendEvent(String event) {
        hmsLogger.sendPeriodicEvent(event);
        sendEventToJS(event);
    }

    private void sendEventToJS(String event, Object... objects) {
        Log.i(TAG, "Periodic event " + event + " captured and event " + event + " is sending to JS.");
        StringBuilder jsFunctionBuilder = new StringBuilder();
        jsFunctionBuilder.append("javascript:");
        jsFunctionBuilder.append("window.runHMSEvent('").append(event).append("'");
        if (objects.length > 0) jsFunctionBuilder.append(buildJSEventParameters(objects));
        jsFunctionBuilder.append(");");
        activity.runOnUiThread(() -> webView.loadUrl(jsFunctionBuilder.toString()));
    }

    private String buildJSEventParameters(Object... objects) {
        final String TO_STR_NOT_VALID_ERR = "Sent event parameter value is not valid! Please add toString() method to the object you " +
                "are passing or do not pass this object as an event parameter. Objects current toString value is %s.";
        StringBuilder eventParametersBuilder = new StringBuilder();
        for (Object obj : objects) {
            if (!isToStringValueValid(obj))
                Log.w(TAG, String.format(Locale.ENGLISH, TO_STR_NOT_VALID_ERR, obj.toString()));
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
