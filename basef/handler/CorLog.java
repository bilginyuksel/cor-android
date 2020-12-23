package com.cor.android.framework.basef.handler;

import android.util.Log;

public final class CorLog {
	private CorLog(){}
	private static boolean enable = false;

	public static void setEnable(boolean enable) {
		CorLog.enable = enable;
	}

	public static void log(int priority, String tag, String message) {
		if(enable)
			Log.println(priority, tag, message);
	}

	public static void info(String tag, String message) {
		log(Log.INFO, tag, message);
	}

	public static void warn(String tag, String message){
		log(Log.WARN, tag, message);
	}

	public static void debug(String tag, String message){
		log(Log.DEBUG, tag, message);
	}

	public static void err(String tag, String message) {
		log(Log.ERROR, tag, message);
	}

	public static void verb(String tag, String message) {
		log(Log.VERBOSE, tag, message);
	}

	public static void asst(String tag, String message){
		log(Log.ASSERT, tag, message);
	}

}

