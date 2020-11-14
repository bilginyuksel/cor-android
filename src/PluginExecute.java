import test.ExecutorClass;
import test.ExecutorMethod;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class PluginExecute {

    private final Map<String, Method> lookupTable = new ConcurrentHashMap<>();

    public void initialize(Class[] classes) {
        for (Class cls : classes) {
            if (cls.isAnnotationPresent(ExecutorClass.class))
                throw new AnnotationNotPresentException();

            fillLookUpTableWithAnnotatedMethods(cls);
        }
    }

    public boolean execute(String action, Object jsonArray, Object callbackContext) {
        if (!lookupTable.containsKey(action)) {
            // callbackContext.error("No action defined");
            return false;
        }
        try {
            Method method = lookupTable.get(action);
            method.invoke(this, jsonArray, callbackContext);
            return true;
        } catch (IllegalAccessException | InvocationTargetException e) {
            // callbackContext.error();
            return false;
        }
    }

    private void fillLookUpTableWithAnnotatedMethods(Class cls) {
        for (Method method : cls.getMethods()) {
            if (method.isAnnotationPresent(ExecutorMethod.class)) {
                String methodName = method.getName();
                lookupTable.put(methodName, method);
            }
        }
    }
}
