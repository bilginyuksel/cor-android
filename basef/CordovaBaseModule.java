package com.cor.android.framework.basef;

import android.content.Intent;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class CordovaBaseModule {
    private final String reference;
    protected boolean isSubModule;
    private final Map<String, CordovaSubModule> subModuleMap = new ConcurrentHashMap<>();

    public CordovaBaseModule() {
        this.reference = this.getClass().getSimpleName();
        this.isSubModule = false;
    }
    public void onDestroy(){}
    public void onPause(boolean multitasking){}
    public void onResume(boolean multitasking){}
    public void onReset(){}
    public void onStart(){}
    public void onStop(){}
    public void onActivityResult(int requestCode, int resultCode, Intent data){}
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){}

    public void addSubModule(CordovaSubModule subModule) {
        subModuleMap.put(subModule.getReference(), subModule);
    }

    public Map<String, CordovaSubModule> getSubModuleMap() {
        return subModuleMap;
    }

    public String getReference(){
        return reference;
    }

    public boolean isSubModule() {
        return isSubModule;
    }
}
