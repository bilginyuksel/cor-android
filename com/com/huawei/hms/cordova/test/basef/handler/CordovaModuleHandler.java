package com.huawei.hms.cordova.test.basef.handler;


import com.huawei.hms.cordova.test.basef.CordovaBaseModule;
import com.huawei.hms.cordova.test.basef.CordovaMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class CordovaModuleHandler<T extends CordovaBaseModule> {
    //private static final String TAG = CordovaModuleHandler.class.getSimpleName();
    private final Map<String, Method> lookupTable = new HashMap<>();
    private final T instance;

    public CordovaModuleHandler(T moduleInstance) {
        this.instance = moduleInstance;
        fillLookupTable();
    }

    private void fillLookupTable(){
        Method[] methods = this.instance.getClass().getMethods();
        for(Method method : methods) {
            if (method.isAnnotationPresent(CordovaMethod.class)) {
                lookupTable.put(method.getName(), method);
            }
        }
    }


    Method getModuleMethod(String action) throws NoSuchMethodException {
        if(!lookupTable.containsKey(action)) throw new NoSuchMethodException();
        return lookupTable.get(action);
    }

    T getInstance() {
        return instance;
    }
}
