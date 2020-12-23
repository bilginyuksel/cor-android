package com.cor.android.framework.basef.handler;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.PluginResult;
import org.json.JSONArray;
import org.json.JSONObject;

import static org.apache.cordova.PluginResult.Status.OK;

public final class Promise {

    private final CallbackContext callbackContext;
    private final String methodName;

    public Promise(final CallbackContext callbackContext, String method) {
        this.callbackContext = callbackContext;
        this.methodName = method;
    }

    public void success() {
        callbackContext.success();
    }
    public void success(int message) {
        callbackContext.success(message);
    }
    public void success(byte[] message) {
        callbackContext.success(message);
    }
    public void success(String message) {
        callbackContext.success(message);
    }
    public void success(JSONArray message) {
        callbackContext.success(message);
    }
    public void success(JSONObject message) {
        callbackContext.success(message);
    }
    public void success(boolean message) {
        callbackContext.sendPluginResult(new PluginResult(OK, message));
    }
    public void success(float message) {
        callbackContext.sendPluginResult(new PluginResult(OK, message));
    }
    public void error(int message) {
        callbackContext.error(message);
    }
    public void  error(CorError corError) {
        callbackContext.error(corError.toJson());
    }
    public void error(String message) {
        callbackContext.error(message);
    }
    public void error(JSONObject message) {
        callbackContext.error(message);
    }
    public void sendPluginResult(PluginResult pluginResult) {
        callbackContext.sendPluginResult(pluginResult);
    }

}
