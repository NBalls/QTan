package driver.chao.com.qtan.hook;

import android.annotation.SuppressLint;
import android.app.ActivityManager;
import android.text.TextUtils;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.concurrent.atomic.AtomicInteger;

public class ActivityManagerHooker {
    private static final String TAG = "BroadcastHooker";

    @SuppressLint({"PrivateApi", "DiscouragedPrivateApi"})
    public static void hookActivityManagerService() {
        Log.i(TAG, "hookActivityManagerService");
        try {
            Class<?> singletonClass = Class.forName("android.util.Singleton");
            Field mInstanceField = singletonClass.getDeclaredField("mInstance");
            mInstanceField.setAccessible(true);
            Method singletonGetMethod = singletonClass.getDeclaredMethod("get");

            Class activityManagerClass = Class.forName(ActivityManager.class.getName());
            Field activityManagerSingletonField = activityManagerClass.getDeclaredField("IActivityManagerSingleton");
            activityManagerSingletonField.setAccessible(true);
            Object activityManagerSingletonInstance = activityManagerSingletonField.get(activityManagerClass);
            Object activityManagerInstance = singletonGetMethod.invoke(activityManagerSingletonInstance);
            HookInvocationHandler handler = new HookInvocationHandler(activityManagerInstance);
            Class<?> IActivityManagerClass = Class.forName("android.app.IActivityManager");
            Object proxy = Proxy.newProxyInstance(ActivityManagerHooker.class.getClassLoader(), new Class<?>[]{IActivityManagerClass}, handler);
            mInstanceField.set(activityManagerSingletonInstance, proxy);
        } catch (Throwable throwable) {
            Log.i(TAG, "hookActivityManagerService throwable=" + throwable);
        }
    }

    private static class HookInvocationHandler implements InvocationHandler {
        private final Object mTargetObject;
        private volatile AtomicInteger atomicInteger = new AtomicInteger();

        private HookInvocationHandler(Object target) {
            this.mTargetObject = target;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            final String methodName = method.getName();
            String stackTraceToString = stackTraceToString(Thread.currentThread().getStackTrace());
            if (TextUtils.equals("registerReceiver", methodName) || TextUtils.equals("registerReceiverWithFeature", methodName)) {
                Log.i(TAG, "registerReceiver count=" + atomicInteger.incrementAndGet() + " stack=" + stackTraceToString);
            } else if (TextUtils.equals("unregisterReceiver", methodName)) {
                Log.i(TAG, "unregisterReceiver count=" + atomicInteger.decrementAndGet() + " stack=" + stackTraceToString);
            }
            return method.invoke(mTargetObject, args);
        }

        private static String stackTraceToString(final StackTraceElement[] arr) {
            if (arr == null) {
                return "";
            }

            StringBuilder sb = new StringBuilder();

            for (StackTraceElement stackTraceElement : arr) {
                String className = stackTraceElement.getClassName();
                // remove unused stacks
                if (className.contains("java.lang.Thread")) {
                    continue;
                }
                if (className.contains("dalvik.system.VMStack")) {
                    continue;
                }
                if (className.contains("com.didi.davinci.map.control.test.ActivityManagerHooker")) {
                    continue;
                }
                if (className.contains("java.lang.reflect.Proxy")) {
                    continue;
                }
                if (className.contains("android.app.ContextImpl")) {
                    continue;
                }
                if (className.contains("android.content.ContextWrapper")) {
                    continue;
                }

                sb.append(stackTraceElement).append(';');
            }
            return sb.toString();
        }

    }

}
