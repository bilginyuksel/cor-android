package production.abstraction;

import production.CordovaBaseModule;
import production.Pair;
import cordova.CordovaMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class MyPlugin extends CordovaPlugin {

    private final Map<String, Pair<Object, Method>> lookupTable = new ConcurrentHashMap<>();

    @Override
    public void pluginInitialize(Object webView, Object cordova) {
        super.pluginInitialize(webView, cordova);

        fillLookupTable(getModuleInstances());
    }

    @Override
    public boolean execute(String action, Object args, Object callbackContext) {
        if(!lookupTable.containsKey(action)) {
            // callbackContext.error();
            return false;
        }

        try {
            Pair<?, Method> objectMethodPair = lookupTable.get(action);
            objectMethodPair.getValue().invoke(objectMethodPair.getKey(), args, callbackContext);
            return true;
        } catch (InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
            return false;
        }
    }

    public abstract <T extends CordovaBaseModule> List<T> getModuleInstances();


    private <T extends CordovaBaseModule> void fillLookupTable(List<T> modules) {
        for(T object : modules) {

            Method[] methods = (Method[]) Arrays.stream(object.getClass().getMethods())
                    .filter(method -> method.isAnnotationPresent(CordovaMethod.class)).toArray();

            for(Method method : methods)
                lookupTable.put(method.getName(), new Pair<>(object, method));
        }
    }
}
