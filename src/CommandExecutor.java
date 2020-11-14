import test.Demo1;
import test.Demo2;
import test.Demo3;

public class CommandExecutor extends PluginExecute{

    public void pluginInitialize() {
        initialize(new Class[]{Demo1.class, Demo2.class, Demo3.class});
    }
}
