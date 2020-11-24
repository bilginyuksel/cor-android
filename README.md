# Example Usage

In `pluginInitialize()` method, you should give all cordova modules as a list to `cordovaContoller` constructor and you must use the `execute` method of `cordovaController` class like below.

```java
public class ExamplePlugin extends CordovaPlugin {
    
   // ...
   // ...
    private CordovaController cordovaController;
    
    @Override
    public void initialize(){
        cordovaController = new CordovaController(Arrays.asList(new CordovaBaseModule[]{
                // new Test1(webView.getContext())
            	// You must add the cordova modules here
        }));
    }
    
    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext){
    	return cordovaController.execute(action, args, callbackContext);
    }
    
}
```

After this setup, you can start using the third-party framework. To create new cordova modules, first you need to extend `CordovaBaseModule` class. Then put the `@CordovaMethod`  annotation the methods you want to expose. Sample class will be like below:

```java
public class SampleModule extends CordovaBaseModule {
    
    private static final String TAG = SampleModule.class.getSimpleName();
    
    public SampleModule(){
        super("custom_module_ref", false);
    }
    
    @CordovaMethod
    public void showToast(JSONArray args, final Promise promise){
        Toast.makeText(context, "Hello from cordova", Toast.LENGTH_SHORT).show();
        promise.success();
    }
    
    @CordovaMethod
    public void infoLog(JSONArray args, final Promise promise){
        Log.i(TAG,"InfoLog from cordova");
        promise.success();
    }
    // ...
    // ...
    
}
```

