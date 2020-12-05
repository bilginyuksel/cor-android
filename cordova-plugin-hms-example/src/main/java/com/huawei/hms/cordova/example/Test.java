package com.huawei.hms.cordova.ads;

import android.content.Context;
import android.widget.Toast;

import com.huawei.hms.cordova.ads.basef.CordovaBaseModule;
import com.huawei.hms.cordova.ads.basef.CordovaMethod;
import com.huawei.hms.cordova.ads.basef.HMSLog;
import com.huawei.hms.cordova.ads.basef.handler.CorPack;
import com.huawei.hms.cordova.ads.basef.handler.Promise;

import org.json.JSONArray;
import org.json.JSONException;

public class Test extends CordovaBaseModule {
    private Context context;
    public Test(Context context) {
        this.context = context;
    }

    @HMSLog
    @CordovaMethod
    public void showToast(final CorPack corPack, JSONArray args, final Promise promise) throws JSONException {
        String message = args.getString(0);
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
        promise.success();
    }

}

