package driver.chao.com.qtan.util;

import driver.chao.com.qtan.bean.MainBean;

/**
 * Created by aaron on 2018/8/6.
 */

public class ParserUtil {

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


    public static String reversalPan(String pan) {
        String mlevel = "";
        if (pan.equals("0")) {//OK
            mlevel = "平手";
        } else if (pan.equals("0.25")) {// OK
            mlevel = "平/半";
        } else if (pan.equals("0.5")) {// OK
            mlevel = "半球";
        } else if (pan.equals("0.75")) {// OK
            mlevel = "半/一";
        } else if (pan.equals("1.0")) {// OK
            mlevel = "一球";
        } else if (pan.equals("1.25")) {// OK
            mlevel = "一/球半";
        } else if (pan.equals("1.5")) {// OK
            mlevel = "球半";
        } else if (pan.equals("1.75")) {// OK
            mlevel = "球半/两";
        } else if (pan.equals("2.0")) {// OK
            mlevel = "两球";
        } else if (pan.equals("2.25")) {// OK
            mlevel = "两/两球半";
        } else if (pan.equals("2.5")) {// OK
            mlevel = "两球半";
        } else if (pan.equals("2.75")) {// OK
            mlevel = "两球半/三";
        } else if (pan.equals("3.0")) {// OK
            mlevel = "三球";
        } else if (pan.equals("-0.25")) {// OK
            mlevel = "受让平/半";
        } else if (pan.equals("-0.5")) {// OK
            mlevel = "受让半球";
        } else if (pan.equals("-0.75")) {// OK
            mlevel = "受让半/一";
        } else if (pan.equals("-1.0")) {// OK
            mlevel = "受让一球";
        } else if (pan.equals("-1.25")) {// OK
            mlevel = "受让一/球半";
        } else if (pan.equals("-1.5")) {// OK
            mlevel = "受让球半";
        } else if (pan.equals("-1.75")) {// OK
            mlevel = "受让球半/两";
        } else if (pan.equals("-2.0")) {// OK
            mlevel = "受让两球";
        } else if (pan.equals("-2.25")) {// OK
            mlevel = "受让两/两球半";
        } else if (pan.equals("-2.5")) {// OK
            mlevel = "受让两球半";
        } else if (pan.equals("-2.75")) {// OK
            mlevel = "受让两球半/三";
        } else if (pan.equals("-3.0")) {// OK
            mlevel = "受让三球";
        }

        return String.valueOf(mlevel);
    }

    public static String getStartPan(MainBean mainBean) {
        for (int i = 0; i < mainBean.yList.size(); i ++) {
            if (mainBean.yList.get(i).company.contains("365")) {
                return mainBean.yList.get(i).startPan;
            }
        }

        for (int i = 0; i < mainBean.yList.size(); i ++) {
            if (mainBean.yList.get(i).company.contains("Crown")) {
                return mainBean.yList.get(i).startPan;
            }
        }

        return "";
    }

    public static String getEndPan(MainBean mainBean) {
        for (int i = 0; i < mainBean.yList.size(); i ++) {
            if (mainBean.yList.get(i).company.contains("365")) {
                return mainBean.yList.get(i).endPan;
            }
        }

        for (int i = 0; i < mainBean.yList.size(); i ++) {
            if (mainBean.yList.get(i).company.contains("Crown")) {
                return mainBean.yList.get(i).endPan;
            }
        }

        return "";
    }

}
