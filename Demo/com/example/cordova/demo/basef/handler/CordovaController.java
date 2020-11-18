package com.example.cordova.demo.basef.handler;

import com.example.cordova.demo.basef.CordovaBaseModule;

import org.apache.cordova.CallbackContext;
import org.json.JSONArray;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class CordovaController {
    private CordovaModuleGroupHandler groupHandler;

    public <T extends CordovaBaseModule> CordovaController(List<T> cordovaModules) {
        List<CordovaModuleHandler> cordovaModuleHandlers = new ArrayList<>();
        for (T cordovaModule : cordovaModules) {
            CordovaModuleHandler cordovaModuleHandler = new CordovaModuleHandler(cordovaModule);
            cordovaModuleHandlers.add(cordovaModuleHandler);
        }
        this.groupHandler = new CordovaModuleGroupHandler(cordovaModuleHandlers);
    }

    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) {
        try {
            CordovaModuleHandler moduleHandler = groupHandler.getCordovaModuleHandler(action);
            String methodName = args.optString(0);
            Method method = moduleHandler.getModuleMethod(methodName);
            args.remove(0);
            method.invoke(args, callbackContext);
            return true;
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            return false;
        }
    }


}
