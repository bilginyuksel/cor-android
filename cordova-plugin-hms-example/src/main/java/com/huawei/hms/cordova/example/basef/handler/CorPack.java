package com.huawei.hms.cordova.ads.basef.handler;

import org.apache.cordova.CordovaInterface;
import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CordovaWebView;

public class CorPack {
    private final HMSLogger hmsLogger;
    private final CordovaPlugin cordovaPlugin;
    private final CordovaWebView webView;
    private final CordovaInterface cordova;
    private final CordovaEventRunner eventRunner;

    CorPack(final HMSLogger hmsLogger, final CordovaPlugin cordovaPlugin, final CordovaEventRunner eventRunner) {
        this.hmsLogger = hmsLogger;
        this.cordovaPlugin = cordovaPlugin;
        this.webView = cordovaPlugin.webView;
        this.cordova = cordovaPlugin.cordova;
        this.eventRunner = eventRunner;
    }

    public void requestPermission(int requestCode, String permission) {
        cordova.requestPermission(cordovaPlugin, requestCode, permission);
    }

    public void requestPermissions(int requestCode, String[] permissions) {
        cordova.requestPermissions(cordovaPlugin, requestCode, permissions);
    }

    public boolean hasPermission(String permission) {
        return cordova.hasPermission(permission);
    }

    public void enableLogger() {
        hmsLogger.enableLogger();
    }

    public void disableLogger() {
        hmsLogger.disableLogger();
    }

    public void startMethodExecution(String methodName) {
        hmsLogger.startMethodExecutionTimer(methodName);
    }

    public void sendSingleEvent(String methodName) {
        hmsLogger.sendSingleEvent(methodName);
    }

    public void sendSingleEvent(String methodName, String errorCode) {
        hmsLogger.sendSingleEvent(methodName, errorCode);
    }

    public CordovaWebView getCordovaWebView() {
        return webView;
    }

    public CordovaInterface getCordova() {
        return cordova;
    }

    public CordovaEventRunner getEventRunner() {
        return eventRunner;
    }
}
