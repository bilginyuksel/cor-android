package com.huawei.hms.cordova.example.basef;

public class CordovaBaseModule {
    private boolean isSingle;
    private String reference;

    public CordovaBaseModule(String reference, boolean isSingle) {
        this.reference = reference;
        this.isSingle = isSingle;
    }

    public String getReference(){
        return reference;
    }

    public boolean isSingle(){
        return isSingle;
    }
}
