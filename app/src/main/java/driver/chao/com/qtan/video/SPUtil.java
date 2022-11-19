package driver.chao.com.qtan.video;

import android.content.Context;
import android.content.SharedPreferences;

public class SPUtil {

    private static final String SHARE_DATA_FILE_NAME = "share_data_info";
    private static final String SHARE_DATA_KEY = "share_data_key";
    private static SharedPreferences sharedPreferences = null;

    public static void init(Context context) {
        sharedPreferences = context.getSharedPreferences(SHARE_DATA_FILE_NAME, Context.MODE_PRIVATE);
    }

    public static void saveData(String content) {
        if (sharedPreferences == null) {
            return;
        }
        sharedPreferences.edit().putString(SHARE_DATA_KEY, content).commit();
    }

    public static String getData() {
        if (sharedPreferences == null) {
            return "";
        }
        return sharedPreferences.getString(SHARE_DATA_KEY, "");
    }
}
