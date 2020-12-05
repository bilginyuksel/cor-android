package com.huawei.hms.cordova.ads.basef.handler;

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

    boolean hasCordovaModuleHandler(String reference) {
        return lookupTable.containsKey(reference);
    }

    CordovaModuleHandler getCordovaModuleHandler(String reference) throws NoSuchCordovaModuleException {
        if(!hasCordovaModuleHandler(reference)) throw new NoSuchCordovaModuleException();
        return lookupTable.get(reference);
    }

    void clear() {
        lookupTable.clear();
        for(CordovaModuleHandler moduleHandler: cordovaModuleHandlers)
            moduleHandler.clear();
        cordovaModuleHandlers.clear();
    }
}
