package driver.chao.com.qtan.hook;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class InstrumentationHook {

    /**
     * Hook的三个步骤：
     * 1、找到合适的Hook点（尽量选择成员变量或者是静态变量）
     * 2、选择合适的Hook方式（接口就用动态代理方式，类就用静态代理方式）
     * 3、偷梁换柱，使用代理对象替换原始对象
     * @param activity
     */
    public static void instrumentationProxy(Activity activity) {
        try {
            Field filed = Activity.class.getDeclaredField("mInstrumentation");
            filed.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) filed.get(activity);
            InstrumentationProxy instrumentationProxy = new InstrumentationProxy(instrumentation);
            filed.set(activity, instrumentationProxy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static class InstrumentationProxy extends Instrumentation {
        private Instrumentation mInstrumentation;

        public InstrumentationProxy(Instrumentation instrumentation) {
            mInstrumentation = instrumentation;
        }

        public ActivityResult execStartActivity(
                Context who, IBinder contextThread, IBinder token, Activity target,
                Intent intent, int requestCode, Bundle options) {

            Log.i("#####", "Hook成功" + "--who:" + who);
            try {
                Method execStartActivityMethod = Instrumentation.class.getDeclaredMethod("execStartActivity",
                        Context.class, IBinder.class, IBinder.class, Activity.class,
                        Intent.class, int.class, Bundle.class);
                return (ActivityResult) execStartActivityMethod.invoke(mInstrumentation, who, contextThread, token, target,
                        intent, requestCode, options);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
