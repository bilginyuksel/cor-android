package com.huawei.hms.cordova.example.basef.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CordovaModuleGroupHandler {
    private final Map<String, CordovaModuleHandler> lookupTable = new HashMap<>();
    private final List<CordovaModuleHandler> cordovaModuleHandlers;
    public CordovaModuleGroupHandler(List<CordovaModuleHandler> cordovaModuleHandlerList){
        this.cordovaModuleHandlers = cordovaModuleHandlerList;
        this.fillLookupTable();
    }

    private void fillLookupTable(){
        for(CordovaModuleHandler cordovaModuleHandler : cordovaModuleHandlers) {
            String reference = cordovaModuleHandler.getInstance().getReference();
            lookupTable.put(reference, cordovaModuleHandler);
        }
    }

    CordovaModuleHandler getCordovaModuleHandler(String reference) throws NoSuchCordovaModuleException {
        if(!lookupTable.containsKey(reference)) throw new NoSuchCordovaModuleException();
        return lookupTable.get(reference);
    }
}
