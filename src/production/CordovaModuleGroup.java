package production;

import cordova.base.CordovaMethod;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CordovaModuleGroup {
    private Map<String, Pair<?, Method>> lookupTable = new HashMap<>();


    public <T extends CordovaBaseModule> void initializeCordovaModules() {
        List<T> modules = getModuleInstances();
        fillLookupTable(modules);
    }

    private <T extends CordovaBaseModule> void fillLookupTable(List<T> modules) {
        for (T obj : modules) {
            List<Method> methods = findAnnotatedMethods(obj.getClass().getMethods());
            for(Method method: methods)
                lookupTable.put(method.getName(), new Pair<>(obj, method));
        }
    }

    private List<Method> findAnnotatedMethods(Method[] methods) {
        List<Method> annotatedMethods = new ArrayList<>();
        for (Method method : methods) {
            if (method.isAnnotationPresent(CordovaMethod.class))
                annotatedMethods.add(method);
        }
        return annotatedMethods;
    }

    private <T extends CordovaBaseModule> List<T> getModuleInstances() {
        // List<T> modules = new ArrayList<>();
        // modules.add((T) new Demo1());
        // modules.add((T) new Demo2());
        // return modules;
        return null;
    }


    public void executeCordovaMethod(Object cordova, Object webview, Object args, final Object callback) {

    }
}
