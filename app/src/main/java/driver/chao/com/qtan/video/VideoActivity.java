package driver.chao.com.qtan.video;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import driver.chao.com.qtan.R;
import driver.chao.com.qtan.video.bean.DataInfo;
import driver.chao.com.qtan.video.bean.DataInfoUtil;
import driver.chao.com.qtan.video.bean.VideoInfo;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // 跳转视频详情页绘制视频
        findViewById(R.id.video_goto_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取数据
                String preContent = ((EditText)findViewById(R.id.video_pre_content)).getText().toString();
                String lastContent = ((EditText)findViewById(R.id.video_last_content)).getText().toString();
                String ratioContent = ((EditText)findViewById(R.id.video_ratio_content)).getText().toString();
                String ratioCount = ((EditText)findViewById(R.id.video_ratio_count)).getText().toString();
                boolean isShowMoney = ((CheckBox)findViewById(R.id.video_money_operate)).isChecked();
                boolean isShowNum = ((CheckBox)findViewById(R.id.video_num_operate)).isChecked();
                boolean isDesc = ((CheckBox)findViewById(R.id.video_desc_operate)).isChecked();
                boolean isTailAnimation = ((CheckBox)findViewById(R.id.video_tail_animation)).isChecked();
                boolean isShowTotal = ((CheckBox)findViewById(R.id.video_total_check)).isChecked();
                boolean isShowName = ((CheckBox)findViewById(R.id.video_name_position)).isChecked();
                boolean isQuick = ((CheckBox)findViewById(R.id.video_quick_operate)).isChecked();
                String width = ((EditText)findViewById(R.id.video_width_value)).getText().toString();
                String leftMargin = ((EditText)findViewById(R.id.video_left_margin)).getText().toString();
                String source = ((EditText)findViewById(R.id.video_source_edt)).getText().toString();
                String author = ((EditText)findViewById(R.id.video_author_edt)).getText().toString();
                String backEdtContent = ((EditText)findViewById(R.id.video_back_edt)).getText().toString();
                String fontColorContent = ((EditText)findViewById(R.id.video_font_color)).getText().toString();
                String numColorContent = ((EditText)findViewById(R.id.video_num_color)).getText().toString();
                String itemColorContent = ((EditText)findViewById(R.id.video_item_color)).getText().toString();
                String totalLocation = ((EditText)findViewById(R.id.video_total_location)).getText().toString();
                String totalFontSize = ((EditText)findViewById(R.id.video_total_font_size)).getText().toString();
                String totalFontColor = ((EditText)findViewById(R.id.video_total_font_color)).getText().toString();
                String totalRightMargin = ((EditText)findViewById(R.id.video_total_right_margin)).getText().toString();
                String totalRatioContent = ((EditText)findViewById(R.id.video_total_ratio)).getText().toString();
                String totalDecimalCount = ((EditText)findViewById(R.id.video_total_decimal_count)).getText().toString();
                String totalUnitContent = ((EditText)findViewById(R.id.video_total_unit)).getText().toString();

                String content = ((EditText) findViewById(R.id.content_edt)).getText().toString();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(getApplicationContext(), "请输入内容!", Toast.LENGTH_SHORT).show();
                    return;
                }
                String[] contentArray = content.split("\n");
                ArrayList<DataInfo> dataInfoList = new ArrayList<>();
                for (int i = 0; i < contentArray.length; i++) {
                    String[] infoArray = contentArray[i].split(" ");
                    if (infoArray.length < 2) {
                        infoArray = contentArray[i].split("\t");
                    }
                    if (infoArray.length >= 2) {
                        DataInfo dataInfo = new DataInfo();
                        dataInfo.title = infoArray[0];
                        String value = infoArray[infoArray.length - 1];
                        if (!TextUtils.isEmpty(value)) {
                            dataInfo.value = Double.parseDouble(value);
                        }
                        if (!TextUtils.isEmpty(ratioContent)) {
                            Double ratio = Double.parseDouble(ratioContent);
                            dataInfo.value = dataInfo.value * ratio;
                        }
                        if (!TextUtils.isEmpty(ratioCount)) {
                            Integer integer = Integer.parseInt(ratioCount);
                            String infoValue = String.format("%." + integer + "f", dataInfo.value);
                            dataInfo.value = Double.parseDouble(infoValue);
                        }
                        if (!TextUtils.isEmpty(leftMargin)) {
                            Integer integer = Integer.parseInt(leftMargin);
                            dataInfo.leftMargin = integer;
                        }
                        dataInfo.isShowName = isShowName;
                        dataInfo.isShowNum = isShowNum;
                        dataInfo.isShowMoney = isShowMoney;
                        dataInfo.preContent = preContent;
                        dataInfo.lastContent = lastContent;
                        dataInfoList.add(dataInfo);
                    }
                }
                ArrayList<DataInfo> result = DataInfoUtil.transferDataInfo(getApplicationContext(), dataInfoList, isDesc, width);
                VideoInfo videoInfo = new VideoInfo();
                videoInfo.isQuick = isQuick;
                videoInfo.isTailAnimation = isTailAnimation;
                videoInfo.isShowTotal = isShowTotal;
                videoInfo.source = source;
                videoInfo.author = author;
                videoInfo.backEdtContent = backEdtContent;
                videoInfo.fontColorContent = fontColorContent;
                videoInfo.numColorContent = numColorContent;
                videoInfo.itemColorContent = itemColorContent;
                videoInfo.totalLocation = totalLocation;
                videoInfo.totalFontSize = totalFontSize;
                videoInfo.totalFontColor = totalFontColor;
                videoInfo.totalRightMargin = totalRightMargin;
                videoInfo.totalRatio = totalRatioContent;
                videoInfo.totalDecimalCount = totalDecimalCount;
                videoInfo.totalUnit = totalUnitContent;
                Intent intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(DATA_INFO_CONTENT, result);
                bundle.putSerializable(VIDEO_INFO_CONTENT, videoInfo);
                intent.putExtra(DATA_INFO_BUNDLE, bundle);
                startActivity(intent);
            }
        });
    }

    public static final String DATA_INFO_CONTENT = "data_info_content";
    public static final String DATA_INFO_BUNDLE = "data_info_bundle";
    public static final String VIDEO_INFO_CONTENT = "video_info_content";
}