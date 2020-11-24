package com.huawei.hms.cordova.example.basef.handler;

import org.json.JSONArray;
import org.json.JSONObject;

public class CordovaEventRunner {

	private static CordovaEventRunner instance = null;
	private HMSLogger hmsLogger;
	private CordovaEventRunner(HMSLogger hmsLogger){
		this.hmsLogger = hmsLogger;
	}

	public static CordovaEventRunner getInstance(HMSLogger hmsLogger) {
		if(instance == null) instance = new CordovaEventRunner(hmsLogger);
		return instance;
	}

	public void sendEvent(String event, JSONObject params) {
		hmsLogger.sendPeriodicEvent(event);
	}

	public void sendEvent(String event, JSONArray params) {
		hmsLogger.sendPeriodicEvent(event);
	}

	public void sendEvent(String event) {
		hmsLogger.sendPeriodicEvent(event);
	}
}
