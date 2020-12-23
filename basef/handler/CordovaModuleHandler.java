package com.cor.android.framework.basef.handler;

import com.cor.android.framework.basef.CordovaBaseModule;
import com.cor.android.framework.basef.CordovaEvent;
import com.cor.android.framework.basef.CordovaMethod;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CordovaModuleHandler {
    private final Map<String, Method> lookupTable = new HashMap<>();
    private final List<Method> eventCache = new ArrayList<>();
    private final CordovaBaseModule instance;

    public CordovaModuleHandler(CordovaBaseModule moduleInstance) {
        this.instance = moduleInstance;
        fillLookupTable();
    }

    private void fillLookupTable(){
        Method[] methods = this.instance.getClass().getMethods();
        for(Method method : methods) {
            if (method.isAnnotationPresent(CordovaMethod.class))
                lookupTable.put(method.getName(), method);
            if(method.isAnnotationPresent(CordovaEvent.class))
                eventCache.add(method);
        }
    }


    Method getModuleMethod(String action)  {
        return lookupTable.get(action);
    }

    boolean hasModuleMethod(String action) {
        return lookupTable.containsKey(action);
    }

    List<Method> getEventCache() {
        return eventCache;
    }
    CordovaBaseModule getInstance() {
        return instance;
    }
    public Map<String, Method> getLookupTable() {
        return lookupTable;
    }

    public void clear() {
        eventCache.clear();
        lookupTable.clear();
    }
}

