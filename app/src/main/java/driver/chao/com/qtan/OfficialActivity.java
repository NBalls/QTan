package driver.chao.com.qtan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class OfficialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_official);

        findViewById(R.id.official_button_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean checked = ((CheckBox) findViewById(R.id.official_num_check)).isChecked();
                boolean daiCheck = ((CheckBox) findViewById(R.id.official_dai_check)).isChecked();
                String count = ((EditText) findViewById(R.id.official_num_count)).getText().toString();
                String position1 = ((EditText) findViewById(R.id.official_position_one)).getText().toString();
                String position2 = ((EditText) findViewById(R.id.official_position_two)).getText().toString();
                boolean isY = Integer.parseInt(count) > 5;

                String content = ((EditText) findViewById(R.id.official_content_edt_content)).getText().toString();
                String[] conArray = content.split(" ");
                if (conArray.length < 3) {
                    return;
                }
                String[] contentArray = conArray[2].split("\n");
                List<String> mList = new ArrayList<>();
                for (int i = 0; i < conArray.length - 1; i++) {
                    mList.add(conArray[i]);
                }
                for (int i = 0; i < contentArray.length; i++) {
                    mList.add(contentArray[i]);
                }
                StringBuffer result = new StringBuffer();
                if (daiCheck) {
                    result.append("今天给大家带来一场").append(mList.get(0)).append("比赛。");
                }
                String[] dui = mList.get(2).split("VS");
                if (dui.length != 2) {
                    return;
                }
                result.append("主队").append(dui[0]).append("目前排名联赛第").append(position1).append("，");
                if (Integer.parseInt(position1) <= 3) {
                    result.append("排名靠前");
                } else if (Integer.parseInt(position1) <= 6) {
                    result.append("排名一般");
                } else {
                    result.append("排名靠后");
                }
                result.append("。");
                result.append("客队").append(dui[1]).append("目前排名联赛第").append(position2).append("，");
                if (Integer.parseInt(position2) <= 3) {
                    result.append("排名靠前");
                } else if (Integer.parseInt(position2) <= 6) {
                    result.append("排名一般");
                } else {
                    result.append("排名靠后");
                }
                result.append("。");
                result.append("从对战历史上看：").append("主队").append(dui[0]).append("和客队").append(dui[1]).append("的近10场比赛");
                result.append("取得了").append(count).append("胜").append(10 - Integer.parseInt(count)).append("负的战绩，").append("占据");
                if (isY) {
                    result.append("优势。");
                } else {
                    result.append("劣势。");
                }
                result.append("结合两队对战历史和近期状态，推荐");
                if (checked) {
                    result.append("比分方向是个更好的选择，");
                } else {
                    result.append("大小分方向是个更好的选择，");
                }
                result.append(mList.get(3));
                ((EditText) findViewById(R.id.screen_title_edt_content2)).setText(result.toString());
            }
        });
    }
}