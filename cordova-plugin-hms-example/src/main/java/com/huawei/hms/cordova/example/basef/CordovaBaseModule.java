package com.huawei.hms.cordova.example.basef;

public class CordovaBaseModule {
    private boolean isSingle;
    private String reference;

    public CordovaBaseModule() {
        this.reference = this.getClass().getSimpleName();
        this.isSingle = false;
    }

    public String getReference(){
        return reference;
    }

    public boolean isSingle(){
        return isSingle;
    }
}
