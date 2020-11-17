package cordova.base;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

final class CordovaModuleTable<T extends CordovaBaseModule> {
    private final T instance;
    private final Map<String, Method> lookupTable = new HashMap<>();

    public CordovaModuleTable(T instance) {
        this.instance = instance;
        this.fillLookupTable();
    }

    private void fillLookupTable(){
        for(Method m : instance.getClass().getMethods()) {
            if(m.isAnnotationPresent(CordovaMethod.class))
                lookupTable.put(m.getName(), m);
        }
    }

    private Method getMethod(String name) throws NoSuchMethodException {
        if(!lookupTable.containsKey(name))
            throw new NoSuchMethodException();
        return lookupTable.get(name);
    }

    public String getName(){
        return instance.getClass().getSimpleName();
    }

    public boolean runMethod(Object args, Object callback) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method method = getMethod(args.toString());
        Object result = method.invoke(instance, args, callback);
        return false;
    }
}
