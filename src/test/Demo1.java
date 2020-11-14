package test;

@ExecutorClass
public class Demo1 {

    @ExecutorMethod
    private void cantRunBecausePrivate(){

    }

    @ExecutorMethod
    public void runThisMethodWithTheMethodName(){

    }
}
