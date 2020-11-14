package production;

import production.abstraction.MyPlugin;
import test.Demo1;
import test.Demo2;

import java.util.ArrayList;
import java.util.List;

public class DeveloperPlugin extends MyPlugin {

    @Override
    public void pluginInitialize(Object webView, Object cordova) {
        super.pluginInitialize(webView, cordova);
    }

    @Override
    public List getModuleInstances() {
        List<CordovaBaseModule> objects = new ArrayList<>();
        objects.add(new Demo1());
        objects.add(new Demo2());
        return objects;
    }


}
