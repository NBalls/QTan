package driver.chao.com.qtan;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import driver.chao.com.qtan.hook.InstrumentationHook;
import driver.chao.com.qtan.hook.StartActivityHook;
import driver.chao.com.qtan.screen.ScreenActivity;
import driver.chao.com.qtan.video.VideoActivity;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        // Hook的方式，Hook instrumentation
        InstrumentationHook.instrumentationProxy(this);
        StartActivityHook.startActivityHook(this);
        // 跳转制作视频
        findViewById(R.id.start_goto_video).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });
        // 跳转盘口验证
        findViewById(R.id.start_goto_pan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
        // 跳转制作截屏
        findViewById(R.id.start_goto_screen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, ScreenActivity.class);
                startActivity(intent);
            }
        });
        // 跳转即嗨文案制作
        findViewById(R.id.start_goto_jihai).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(StartActivity.this, OfficialActivity.class);
                startActivity(intent);
            }
        });
    }
}
