package driver.chao.com.qtan.util;

import android.content.Context;

public class Utils {
    /**
     * dip转换px
     *
     * @param dip
     * @return
     */
    public static int dip2px(Context context, float dip) {
        float f = context.getResources().getDisplayMetrics().density;
        return (int) (dip * f + 0.5F);
    }
}
