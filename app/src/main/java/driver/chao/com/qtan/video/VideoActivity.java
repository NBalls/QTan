package driver.chao.com.qtan.video;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        // 跳转视频详情页绘制视频
        findViewById(R.id.video_goto_layout).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(VideoActivity.this, VideoDetailActivity.class);
                ArrayList<DataInfo> dataInfoList = new ArrayList<>();
                DataInfo dataInfo = new DataInfo();
                dataInfo.title = "北京";
                dataInfo.value = 123;
                dataInfoList.add(dataInfo);
                DataInfo dataInfo1 = new DataInfo();
                dataInfo1.title = "上海";
                dataInfo1.value = 145;
                dataInfoList.add(dataInfo1);
                DataInfo dataInfo2 = new DataInfo();
                dataInfo2.title = "天津";
                dataInfo2.value = 99;
                dataInfoList.add(dataInfo2);
                Bundle bundle = new Bundle();
                bundle.putSerializable("dataInfo", dataInfoList);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
}