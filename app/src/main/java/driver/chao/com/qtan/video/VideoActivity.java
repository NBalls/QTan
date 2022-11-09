package driver.chao.com.qtan.video;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        ((CheckBox) findViewById(R.id.video_num_operate)).setChecked(true);

        // 跳转视频详情页绘制视频
        findViewById(R.id.video_goto_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 获取数据
                String content = ((EditText) findViewById(R.id.content_edt)).getText().toString();
                String[] contentArray = content.split("\n");
                ArrayList<DataInfo> dataInfoList = new ArrayList<>();
                for (int i = 0; i < contentArray.length; i++) {
                    String[] infoArray = contentArray[i].split(" ");
                    if (infoArray.length >= 2) {
                        DataInfo dataInfo = new DataInfo();
                        dataInfo.title = infoArray[0];
                        String value = infoArray[infoArray.length - 1];
                        if (!TextUtils.isEmpty(value)) {
                            dataInfo.value = Double.parseDouble(value);
                        }
                        dataInfoList.add(dataInfo);
                    }
                }
                // 是否显示序号
                boolean isSelected = findViewById(R.id.video_num_operate).isSelected();
                Intent intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataInfo", dataInfoList);
                intent.putExtra("bundle", bundle);
                intent.putExtra("show_num", isSelected);
                startActivity(intent);
            }
        });
    }
}