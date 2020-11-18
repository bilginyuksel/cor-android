package com.example.cordova.demo;

import android.content.Context;
import android.widget.Toast;

import com.example.cordova.demo.basef.CordovaBaseModule;
import com.example.cordova.demo.basef.CordovaMethod;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

public class Test1 extends CordovaBaseModule {
    private Context context;
    public Test1(Context context){
        super("test1", false);
        this.context = context;
    }

    @CordovaMethod
    public void showToast(JSONArray args, final CallbackContext callbackContext){
        Toast.makeText(context, "Hello from cordova", Toast.LENGTH_SHORT).show();
        callbackContext.success();
    }

}
