package com.example.cordova.demo.basef;

public class CordovaBaseModule {
    private boolean isSingle;
    private String reference;

    public CordovaBaseModule(String reference, boolean isSingle) {
        this.reference = reference;
        this.isSingle = isSingle;
    }

    public CordovaBaseModule() {
        this.reference = this.getClass().getSimpleName();
        this.isSingle = true;
    }

    public String getReference(){
        return reference;
    }

    public boolean isSingle(){
        return isSingle;
    }
}
