package driver.chao.com.qtan.hook;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class StartActivityHook {

    public static void startActivityHook() {
        try {
            Class<?> clazz = Class.forName("android.app.Activity");
            Field field = clazz.getDeclaredField("mInstrumentation");
            field.setAccessible(true);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class InnerInvocationHandler<T> implements InvocationHandler {

        private T mTarget = null;

        public InnerInvocationHandler(T target) {
            mTarget = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            String name = method.getName();

            if (method.getName().equalsIgnoreCase("startActivity")) {
                return method.invoke(proxy, args);
            } else {
                return method.invoke(mTarget, args);
            }
        }
    }

}
