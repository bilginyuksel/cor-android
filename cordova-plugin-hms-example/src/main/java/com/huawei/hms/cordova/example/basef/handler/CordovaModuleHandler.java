package com.huawei.hms.cordova.ads.basef.handler;

import com.huawei.hms.cordova.ads.basef.CordovaBaseModule;
import com.huawei.hms.cordova.ads.basef.CordovaEvent;
import com.huawei.hms.cordova.ads.basef.CordovaMethod;

import java.lang.reflect.Method;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CordovaModuleHandler<T extends CordovaBaseModule> {
    private final Map<String, Method> lookupTable = new HashMap<>();
    private final List<Method> eventCache = new ArrayList<>();
    private final T instance;

    public CordovaModuleHandler(T moduleInstance) {
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

    Method getModuleMethod(String action) throws NoSuchMethodException {
        if(!lookupTable.containsKey(action)) throw new NoSuchMethodException();
        return lookupTable.get(action);
    }

    List<Method> getEventCache() {
        return eventCache;
    }
    T getInstance() {
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
