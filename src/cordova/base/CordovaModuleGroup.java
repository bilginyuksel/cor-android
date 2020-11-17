package cordova.base;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class CordovaModuleGroup {
    private final Map<String, CordovaModuleTable> tables = new HashMap<>();

    private <T extends CordovaBaseModule> void addCordovaModule(T table) {
        CordovaModuleTable<T> cordovaModuleTable = new CordovaModuleTable<>(table);
        tables.put(cordovaModuleTable.getName(), cordovaModuleTable);
    }

    public boolean executeCordova(String action, Object args, final Object callback) {
        try {
            return tables.get(action).runMethod(args, callback);
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            return false;
        }
    }

    public <T extends CordovaBaseModule> void setModules(List<T> moduleInstances) {
        for (T obj : moduleInstances)
            addCordovaModule(obj);
    }
}
