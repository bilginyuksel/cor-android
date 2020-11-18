package com.huawei.hms.cordova.test;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.huawei.hms.cordova.test.basef.CordovaBaseModule;
import com.huawei.hms.cordova.test.basef.CordovaMethod;
import com.huawei.hms.cordova.test.basef.HMSLog;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;

public class Test1 extends CordovaBaseModule {
    private Context context;
    private Activity activity;
    public Test1(Context context, Activity activity){
        super("test1", false);
        this.context = context;
        this.activity= activity;
    }

    @CordovaMethod
    @HMSLog
    public void showToast(JSONArray args, final CallbackContext callbackContext){
        activity.runOnUiThread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Toast.makeText(context, "Hello from cordova", Toast.LENGTH_SHORT).show();
            callbackContext.success();
        });
    }
}
