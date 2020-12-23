package com.cor.android.framework.basef;

import android.content.Intent;

public abstract class CordovaBaseModule {
    private final String reference;

    public CordovaBaseModule() {
        this.reference = this.getClass().getSimpleName();
    }
    public void onDestroy(){}
    public void onPause(boolean multitasking){}
    public void onResume(boolean multitasking){}
    public void onReset(){}
    public void onStart(){}
    public void onStop(){}
    public void onActivityResult(int requestCode, int resultCode, Intent data){}
    public void onRequestPermissionResult(int requestCode, String[] permissions, int[] grantResults){}

    public String getReference(){
        return reference;
    }

}
