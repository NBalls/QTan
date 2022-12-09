package driver.chao.com.qtan.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class StartActivityHook {

    public static void startActivityHook(Activity activity) {
        try {
            // Class<?> clazz = Class.forName("android.app.Activity");
            Class<?> clazz = Activity.class;
            Field field = clazz.getDeclaredField("mInstrumentation");
            field.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) field.get(activity);

            InnerInvocationHandler innerInvocationHandler = new InnerInvocationHandler(instrumentation);
            Object proxy = Proxy.newProxyInstance(activity.getClassLoader(), new Class[]{Object.class}, innerInvocationHandler);
            field.set(activity, proxy);
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
            Log.i("#########", "invoke name: hook!!!!");
            if (method.getName().equalsIgnoreCase("execStartActivity")) {
                Log.i("#########", "invoke name: hook!");
                return method.invoke(proxy, args);
            } else {
                return method.invoke(mTarget, args);
            }
        }
    }

}
