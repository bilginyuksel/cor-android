package cordova;

import cordova.base.CordovaBaseModule;
import cordova.base.CordovaModuleGroup;
import test.Demo1;

import java.util.ArrayList;
import java.util.List;

public class TestPlugin extends CordovaPlugin{

    private final CordovaModuleGroup cordovaModuleGroup = new CordovaModuleGroup();

    @Override
    public void pluginInitialize(Object cordova, Object webView) {
        super.pluginInitialize(cordova, webView);
        cordovaModuleGroup.setModules(getModuleInstances());
    }

    private <T extends CordovaBaseModule> List<T> getModuleInstances() {
        List moduleInstances = new ArrayList<T>();
        moduleInstances.add(new Demo1());
        return moduleInstances;
    }

    @Override
    public boolean execute(String action, Object args, final Object callback) {
        return cordovaModuleGroup.executeCordova(action, args, callback);
    }
}
