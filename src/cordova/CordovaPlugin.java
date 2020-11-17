package cordova;

public abstract class CordovaPlugin {
    public abstract boolean execute(String action, Object args, final Object callback);
    public void pluginInitialize(Object cordova, Object webView){

    }
}
