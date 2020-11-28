package com.huawei.hms.cordova.example.basef.util;

import android.content.res.Resources;

public class Px2Dp {
	private static final float SYSTEM_DENSITY = Resources.getSystem().getDisplayMetrics().density;

	public static int pxToDp(int px) {
		return Math.round(px * SYSTEM_DENSITY);
	}

	public static int dpToPx(int dp) {
		return Math.round(dp / SYSTEM_DENSITY);
	}
}
