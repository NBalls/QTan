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

                DataInfo dataInfo3 = new DataInfo();
                dataInfo3.title = "苏州";
                dataInfo3.value = 94;
                dataInfoList.add(dataInfo3);

                DataInfo dataInfo4 = new DataInfo();
                dataInfo4.title = "杭州";
                dataInfo4.value = 167;
                dataInfoList.add(dataInfo4);

                DataInfo dataInfo5 = new DataInfo();
                dataInfo5.title = "南京";
                dataInfo5.value = 104;
                dataInfoList.add(dataInfo5);

                DataInfo dataInfo6 = new DataInfo();
                dataInfo6.title = "广州";
                dataInfo6.value = 88;
                dataInfoList.add(dataInfo6);

                DataInfo dataInfo7 = new DataInfo();
                dataInfo7.title = "深圳";
                dataInfo7.value = 161;
                dataInfoList.add(dataInfo7);

                DataInfo dataInfo8 = new DataInfo();
                dataInfo8.title = "郑州";
                dataInfo8.value = 144;
                dataInfoList.add(dataInfo8);

                DataInfo dataInfo9 = new DataInfo();
                dataInfo9.title = "秦皇岛";
                dataInfo9.value = 67;
                dataInfoList.add(dataInfo9);

                Bundle bundle = new Bundle();
                bundle.putSerializable("dataInfo", dataInfoList);
                intent.putExtra("bundle", bundle);
                startActivity(intent);
            }
        });
    }
}