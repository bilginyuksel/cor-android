import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main {

    public static void main(String[] args) {
        try {
            test();
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    static void test() throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        Method[] m = Main.class.getDeclaredMethods();
        for (Method method : m) {
            for (Annotation annotation : method.getAnnotations())
                if (annotation.annotationType() == CordovaEvent.class)
                    method.invoke(Main.class);
        }

    }

    @CordovaEvent
    public static Event something() {
        System.out.println("hello world");
        return new Event("something", new String("Hello world"));
    }

}
