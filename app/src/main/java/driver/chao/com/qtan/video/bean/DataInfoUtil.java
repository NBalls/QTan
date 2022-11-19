package driver.chao.com.qtan.video.bean;

import android.content.Context;
import android.text.TextUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import driver.chao.com.qtan.util.Utils;

public class DataInfoUtil {

    private static final String COLOR_1 = "#FF0000";
    private static final String COLOR_2 = "#1d6996";
    private static final String COLOR_3 = "#edad08";
    private static final String COLOR_4 = "#73af48";
    private static final String COLOR_5 = "#94346e";
    private static final String COLOR_6 = "#38a6a5";
    private static final String COLOR_7 = "#e17c05";
    private static final String COLOR_8 = "#5f4690";
    private static final String COLOR_9 = "#0f8554";
    private static final String COLOR_10 = "#6f4070";


    public static ArrayList<DataInfo> transferDataInfo(Context context, List<DataInfo> res, boolean isDesc, String widthValue) {
        if (res == null || res.size() == 0) {
            return new ArrayList<>();
        }
        List<DataInfo> result = res.stream().sorted(new Comparator<DataInfo>() {
            @Override
            public int compare(DataInfo o1, DataInfo o2) {
                if (isDesc) {
                    return (int)(o2.value * 10000 - o1.value * 10000);
                } else {
                    return (int)(o1.value * 10000 - o2.value * 10000);
                }
            }
        }).collect(Collectors.toList());
        for (int i = 0; i < result.size(); i++) {
            result.get(i).num = i + 1;
        }
        double maxValue = 0;
        int    maxIndex = 0;
        if (isDesc) {
            maxValue = result.get(0).value;
            maxIndex = 0;
        } else {
            maxValue = result.get(result.size() - 1).value;
            maxIndex = result.size() - 1;
        }
        if (!TextUtils.isEmpty(widthValue)) {
            result.get(maxIndex).width = Utils.dip2px(context, Integer.parseInt(widthValue));
        } else {
            result.get(maxIndex).width = Utils.dip2px(context, 100);
        }
        for (int i = 0; i < result.size(); i++) {
            if (i != maxIndex) {
                result.get(i).width = ((int)(result.get(i).value / maxValue * result.get(maxIndex).width));
            }

            int position = i;
            if (position % 10 == 0) {
                result.get(i).color = COLOR_1;
            } else if (position % 10 == 1) {
                result.get(i).color = COLOR_2;
            } else if (position % 10 == 2) {
                result.get(i).color = COLOR_3;
            } else if (position % 10 == 3) {
                result.get(i).color = COLOR_4;
            } else if (position % 10 == 4) {
                result.get(i).color = COLOR_5;
            } else if (position % 10 == 5) {
                result.get(i).color = COLOR_6;
            } else if (position % 10 == 6) {
                result.get(i).color = COLOR_7;
            } else if (position % 10 == 7) {
                result.get(i).color = COLOR_8;
            } else if (position % 10 == 8) {
                result.get(i).color = COLOR_9;
            } else if (position % 10 == 9) {
                result.get(i).color = COLOR_10;
            }
        }

        return new ArrayList<>(result);
    }

}
