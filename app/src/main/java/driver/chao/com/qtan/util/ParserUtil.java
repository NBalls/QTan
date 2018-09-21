package driver.chao.com.qtan.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by aaron on 2018/8/6.
 */

public class ParserUtil {

    public static void printSplit(String title) {
        System.out.println();
        System.out.println("#####################" + title + "#####################");
        System.out.println();
    }

    public static String changePan(String pan) {
        float mlevel = 0f;
        if (pan.equals("平手")) {//OK
            mlevel = 0f;
        } else if (pan.equals("平/半")) {// OK
            mlevel = 0.25f;
        } else if (pan.equals("半球")) {// OK
            mlevel = 0.5f;
        } else if (pan.equals("半/一")) {// OK
            mlevel = 0.75f;
        } else if (pan.equals("一球")) {// OK
            mlevel = 1.0f;
        } else if (pan.equals("一/球半")) {// OK
            mlevel = 1.25f;
        } else if (pan.equals("球半")) {// OK
            mlevel = 1.5f;
        } else if (pan.equals("球半/两")) {// OK
            mlevel = 1.75f;
        } else if (pan.equals("两球")) {// OK
            mlevel = 2.0f;
        } else if (pan.equals("两/两球半")) {// OK
            mlevel = 2.25f;
        } else if (pan.equals("两球半")) {// OK
            mlevel = 2.5f;
        } else if (pan.equals("两球半/三")) {// OK
            mlevel = 2.75f;
        } else if (pan.equals("三球")) {// OK
            mlevel = 3.0f;
        } else if (pan.equals("受让平/半")) {// OK
            mlevel = -0.25f;
        } else if (pan.equals("受让半球")) {// OK
            mlevel = -0.5f;
        } else if (pan.equals("受让半/一")) {// OK
            mlevel = -0.75f;
        } else if (pan.equals("受让一球")) {// OK
            mlevel = -1.0f;
        } else if (pan.equals("受让一/球半")) {// OK
            mlevel = -1.25f;
        } else if (pan.equals("受让球半")) {// OK
            mlevel = -1.5f;
        } else if (pan.equals("受让球半/两")) {// OK
            mlevel = -1.75f;
        } else if (pan.equals("受让两球")) {// OK
            mlevel = -2.0f;
        } else if (pan.equals("受让两/两球半")) {// OK
            mlevel = -2.25f;
        } else if (pan.equals("受让两球半")) {// OK
            mlevel = -2.5f;
        } else if (pan.equals("受让两球半/三")) {// OK
            mlevel = -2.75f;
        } else if (pan.equals("受让三球")) {// OK
            mlevel = -3.0f;
        }

        return String.valueOf(mlevel);
    }

    public static boolean isNearDate(String strDate) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            Date date1 = sdf.parse(strDate);
            Date date2 = Calendar.getInstance().getTime();
            if (date2.getTime() - date1.getTime() <= 365 * 24 * 60 * 60 * 1000) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
}
