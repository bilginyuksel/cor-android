package com.cor.android.framework.basef.handler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CordovaModuleGroupHandler {
    private final Map<String, CordovaModuleHandler> lookupTable = new HashMap<>();
    public CordovaModuleGroupHandler(List<CordovaModuleHandler> cordovaModuleHandlerList){
        fillLookupTable(cordovaModuleHandlerList);
    }

    private void fillLookupTable(List<CordovaModuleHandler> cordovaModuleHandlers){
        for(CordovaModuleHandler cordovaModuleHandler : cordovaModuleHandlers) {
            String reference = cordovaModuleHandler.getInstance().getReference();
            lookupTable.put(reference, cordovaModuleHandler);
        }
    }

    boolean hasCordovaModuleHandler(String reference) {
        return lookupTable.containsKey(reference);
    }

    CordovaModuleHandler getCordovaModuleHandler(String reference)  {
        return lookupTable.get(reference);
    }

    public Map<String, CordovaModuleHandler> getLookupTable() {
        return lookupTable;
    }

    void clear() {
        for(Map.Entry<String, CordovaModuleHandler> moduleHandler: lookupTable.entrySet())
            moduleHandler.getValue().clear();
        lookupTable.clear();
    }
}

