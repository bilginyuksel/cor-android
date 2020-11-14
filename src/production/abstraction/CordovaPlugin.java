package production.abstraction;

public abstract class CordovaPlugin {

    Object webView;
    Object cordova;
    public void pluginInitialize(Object webView, Object cordova){
        this.webView = webView;
        this.cordova = cordova;
    }

    public abstract boolean execute(String action, Object args, Object callbackContext);
}
