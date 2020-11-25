package com.huawei.hms.cordova.example.basef.handler;

import com.huawei.hms.cordova.example.Test1;
import com.huawei.hms.cordova.example.basef.CordovaBaseModule;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class TSCGenerator {

    private CordovaModuleGroupHandler groupHandler;
    private final List<String> moduleReferences = new ArrayList<>();

    public static final String TS_UTILS_FILE = "import {exec} from 'cordova';\n" +
            "\n" +
            "export function asyncExec (clazz: string, ref: string, args: any[]=[]) : Promise<any>{\n" +
            "    return new Promise<any>((resolve, reject) => {\n" +
            "        exec(resolve, reject, clazz, ref, args);\n" +
            "    });\n" +
            "}\n" +
            "\n" +
            "type Handler = (data: any) => void;\n" +
            "\n" +
            "declare global {\n" +
            "    interface Window {\n" +
            "        hmsEventHandlers: {\n" +
            "            [key: string]: Handler[]\n" +
            "        },\n" +
            "        hmsEventHandler: (eventName: string, data: any) => void,\n" +
            "        registerHMSEvent: (eventName: string, handler: Handler) => void,\n" +
            "        unregisterHMSEvent: (eventName: string, handler: Handler) => void,\n" +
            "        [key: string]: any\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "initEventHandler();\n" +
            "\n" +
            "function initEventHandler() {\n" +
            "    if (window.hmsEventHandler != null) {\n" +
            "        return;\n" +
            "    }\n" +
            "\n" +
            "    window.hmsEventHandlers = {};\n" +
            "\n" +
            "    window.hmsEventHandler = (eventName, data) => {\n" +
            "        console.log('eventReceived: ' + eventName + ' with data: ', data);\n" +
            "        if (window.hmsEventHandlers.hasOwnProperty(eventName)) {\n" +
            "            window.hmsEventHandlers[eventName].forEach(handler => {\n" +
            "                handler(data);\n" +
            "            });\n" +
            "        }\n" +
            "    }\n" +
            "\n" +
            "    window.registerHMSEvent = (eventName, handler) => {\n" +
            "        if (window.hmsEventHandlers.hasOwnProperty(eventName)) {\n" +
            "            window.hmsEventHandlers[eventName].push(handler);\n" +
            "        } else {\n" +
            "            window.hmsEventHandlers[eventName] = [handler];\n" +
            "        }\n" +
            "    };\n" +
            "\n" +
            "    window.unregisterHMSEvent = (eventName, handler) => {\n" +
            "        if (window.hmsEventHandlers.hasOwnProperty(eventName)) {\n" +
            "            const idx = window.hmsEventHandlers[eventName].indexOf(handler);\n" +
            "            if (idx > -1) {\n" +
            "                window.hmsEventHandlers[eventName].splice(idx, 1);\n" +
            "            }\n" +
            "        }\n" +
            "\n" +
            "    };\n" +
            "}";

    public static void main(String[] args) {
        System.out.println("Hello World");

//        TSCGenerator tscGenerator = new TSCGenerator(
//                Arrays.asList(new CordovaBaseModule[]{
//                        new Test1(null, null)
//                }));
//
//        tscGenerator.generateTSCModule("test1");
    }


    public <T extends CordovaBaseModule> TSCGenerator(List<T> cordovaModules) {
        List<CordovaModuleHandler> cordovaModuleHandlers = new ArrayList<>();
        for (T cordovaModule : cordovaModules) {
            CordovaModuleHandler cordovaModuleHandler = new CordovaModuleHandler(cordovaModule);
            cordovaModuleHandlers.add(cordovaModuleHandler);
            moduleReferences.add(cordovaModule.getReference());
        }

        this.groupHandler = new CordovaModuleGroupHandler(cordovaModuleHandlers);
    }

    public void generateTSCModule(String reference) {
        // "test1"
        CordovaModuleHandler cordovaModuleHandler = groupHandler.getCordovaModuleHandler(reference);

        String className = cordovaModuleHandler.getInstance().getReference();
        Map<String, Method> methods = cordovaModuleHandler.getLookupTable();

        String wholeClass = generateTSCHeader(className);


        for (String key: methods.keySet()) {
            // key = methodName
            // addMethod
            wholeClass += generateFunction(className, key);
        }
        wholeClass += generateTSCEnd();

        System.out.println(wholeClass);
    }


    private String generateTSCHeader(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import { asyncExec } from './utils';\n");
        sb.append(String.format("export class %s {\n", className));
        return sb.toString();
    }

    private String generateFunction(String className, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n\t%s(params: any) {%n", methodName));
        sb.append(String.format("\t\treturn asyncExec('TODO', '%s', ['%s', params]);%n", className, methodName));
        sb.append("\t}\n");
        return sb.toString();
    }

    private String generateTSCEnd() {
        return "}";
    }

}
