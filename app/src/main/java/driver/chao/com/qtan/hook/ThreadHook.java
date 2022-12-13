package driver.chao.com.qtan.hook;

import android.annotation.SuppressLint;
import android.util.Log;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;

public class ThreadHook {

    public static void threadHook() {
        try {
            Field field = Thread.class.getDeclaredField("group");
            field.setAccessible(true);

            Constructor<?> constructor = Thread.class.getConstructor();
            constructor.newInstance();

            Runtime.getRuntime().gc();
            Runtime.getRuntime().gc();
            Field field2 = Thread.class.getDeclaredField("");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static class MyThreadGroup extends ThreadGroup {

        public MyThreadGroup(String name) {
            super(name);
        }

        public MyThreadGroup(ThreadGroup parent, String name) {
            super(parent, name);
        }

        void add(Thread t) {
            Log.i("#########", "hook thread success!!!");
        }
    }
}
