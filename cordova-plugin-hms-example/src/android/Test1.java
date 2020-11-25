package com.huawei.hms.cordova.example;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;
import com.huawei.hms.cordova.example.basef.CordovaEvent;
import com.huawei.hms.cordova.example.basef.CordovaMethod;
import com.huawei.hms.cordova.example.basef.HMSLog;
import com.huawei.hms.cordova.example.basef.handler.CordovaEventRunner;
import com.huawei.hms.cordova.example.basef.handler.Promise;

import org.apache.cordova.CallbackContext;
import org.apache.cordova.CordovaInterface;
import org.json.JSONArray;
import org.json.JSONObject;

public class Test1 extends CordovaBaseModule {
    private Context context;
    private Activity activity;
    private SampleFileForEventTest eventTest;
    public Test1(Context context, Activity activity){
        super(Test1.class.getSimpleName(), false);
        this.context = context;
        this.activity= activity;
        eventTest = new SampleFileForEventTest();
    }

    @CordovaMethod
    @HMSLog
    public void showToast(JSONArray args, final Promise promise){
        activity.runOnUiThread(() -> {
//            try {
//                Thread.sleep(2000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            Toast.makeText(context, "Hello from cordova", Toast.LENGTH_SHORT).show();
            promise.success();
        });
    }


    @CordovaMethod
    public void test(JSONArray args, final Promise promise){
        promise.success();
    }

    @CordovaEvent
    public void sampleEvent(CordovaEventRunner eventRunner) {
        eventTest.setSampleEvent(()->{
            eventRunner.sendEvent("sampleEvent");
        });
    }

}
