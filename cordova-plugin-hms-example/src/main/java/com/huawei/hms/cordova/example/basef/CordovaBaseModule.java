package com.huawei.hms.cordova.ads.basef;

public abstract class CordovaBaseModule {
    private boolean isSingle;
    private String reference;

    public CordovaBaseModule() {
        this.reference = this.getClass().getSimpleName();
        this.isSingle = false;
    }
    public void onDestroy(){}
    public void onPause(boolean multitasking){}
    public void onResume(boolean multitasking){}
    public void onReset(){}
    public void onStart(){}
    public void onStop(){}


    public String getReference(){
        return reference;
    }
    public boolean isSingle(){
        return isSingle;
    }
}
