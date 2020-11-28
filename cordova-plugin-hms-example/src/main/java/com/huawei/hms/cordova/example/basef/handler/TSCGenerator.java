package com.huawei.hms.cordova.example.basef.handler;

import com.huawei.hms.cordova.example.basef.CordovaBaseModule;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TSCGenerator {
    private String pluginName;

    private CordovaModuleGroupHandler groupHandler;
    private final List<String> moduleReferences = new ArrayList<>();

    public static final String TS_UTILS_FILE = "import {exec} from 'cordova';\n" +
			"\n" +
			"export function asyncExec(clazz: string, func: string, args:any[] = []): Promise<any> {\n" +
			"    return new Promise((resolve, reject) => {\n" +
			"        exec(resolve, reject, clazz, func, args);\n" +
			"    });\n" +
			"}\n" +
			"\n" +
			"declare global {\n" +
			"    interface Window {\n" +
			"        hmsEvents: {\n" +
			"            [key: string]: (data: any) => void\n" +
			"        },\n" +
			"        runHMSEvent: (eventName: string, data: any) => void,\n" +
			"        subscribeHMSEvent: (eventName: string, callback: (data: any) => void) => void\n" +
			"        [key: string]: any\n" +
			"    }\n" +
			"}\n" +
			"\n" +
			"function initEventHandler() {\n" +
			"    if (window.hmsEvents != null) return;\n" +
			"    window.hmsEvents = {};\n" +
			"    window.runHMSEvent = (eventName, data) => {\n" +
			"        if (window.hmsEvents.hasOwnProperty(eventName))\n" +
			"            window.hmsEvents[eventName](data);\n" +
			"    };\n" +
			"    window.subscribeHMSEvent = (eventName, handler) => {\n" +
			"        window.hmsEvents[eventName] = handler;\n" +
			"    };\n" +
			"}\n" +
			"\n" +
			"initEventHandler()";

    public <T extends CordovaBaseModule> TSCGenerator(String pluginName, List<T> cordovaModules) {
        this.pluginName = pluginName;
        List<CordovaModuleHandler> cordovaModuleHandlers = new ArrayList<>();
        for (T cordovaModule : cordovaModules) {
            CordovaModuleHandler cordovaModuleHandler = new CordovaModuleHandler(cordovaModule);
            cordovaModuleHandlers.add(cordovaModuleHandler);
            moduleReferences.add(cordovaModule.getReference());
        }
        this.groupHandler = new CordovaModuleGroupHandler(cordovaModuleHandlers);
    }

    public void generateTSCModules() {
        TSCFileUtils.createFile("utils.ts", TS_UTILS_FILE);
        for (String moduleRef: moduleReferences) {
            generateTSCModule(moduleRef);
        }
    }

    private void generateTSCModule(String reference) {
        CordovaModuleHandler cordovaModuleHandler = groupHandler.getCordovaModuleHandler(reference);

        String className = cordovaModuleHandler.getInstance().getReference();
        Map<String, Method> methods = cordovaModuleHandler.getLookupTable();

        String wholeClass = generateTSCHeader(className);

        for (String key: methods.keySet()) {
            wholeClass += generateFunction(className, key);
        }

        if (!cordovaModuleHandler.getEventCache().isEmpty())
            wholeClass += generateEventMethod();

        wholeClass += generateTSCEnd();
        TSCFileUtils.createFile(className+".ts", wholeClass);
    }

    private String generateEventMethod() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n\ton(eventName: string, callback: (val: any) => void): void {\n");
        sb.append("\t\twindow.subscribeHMSEvent(eventName, callback);\n");
        sb.append("\t}\n");
        return sb.toString();
    }

    private String generateTSCHeader(String className) {
        StringBuilder sb = new StringBuilder();
        sb.append("import { asyncExec } from './utils';\n");
        sb.append(String.format("\nexport class %s {\n", className));
        return sb.toString();
    }

    private String generateFunction(String className, String methodName) {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%n\t%s(params: any): Promise<void> {%n", methodName));
        sb.append(String.format("\t\treturn asyncExec('%s', '%s', ['%s', params]);%n", pluginName, className, methodName));
        sb.append("\t}\n");
        return sb.toString();
    }

    private String generateTSCEnd() {
        return "}";
    }
}
