package driver.chao.com.qtan.video;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import driver.chao.com.qtan.R;
import driver.chao.com.qtan.util.Utils;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String TAG = "######";
    /** 页面背景颜色 **/
    private String BACK_COLOR = "#222222";
    /** 是否降序 **/
    private boolean isAsc = false;
    /** 是否显示需要 **/
    private boolean isShowNum = true;
    /** 动画时长 **/
    private long DURATION_TIME = 3000;
    /** 起始动画延时时间 **/
    private long DELAY_TIME = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        initView();

        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            @Override
            public void run() {
                initData();
            }
        }, DELAY_TIME);
    }

    private void initView() {
        findViewById(R.id.video_container_layout).setBackgroundColor(Color.parseColor(BACK_COLOR));
    }

    private void initData() {
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ArrayList<DataInfo> res = (ArrayList<DataInfo>) bundle.getSerializable("dataInfo");
        if (res == null || res.size() == 0) {
            return;
        }
        List<DataInfo> result = res.stream().sorted(new Comparator<DataInfo>() {
            @Override
            public int compare(DataInfo o1, DataInfo o2) {
                if (isAsc) {
                    return (int)(o1.value - o2.value);
                } else {
                    return (int)(o2.value - o1.value);
                }
            }
        }).collect(Collectors.toList());

        double maxValue = 0;
        int    maxIndex = 0;
        if (isAsc) {
            maxValue = result.get(result.size() - 1).value;
            maxIndex = result.size() - 1;
        } else {
            maxValue = result.get(0).value;
            maxIndex = 0;
        }
        result.get(maxIndex).width = Utils.dip2px(getApplicationContext(), 200);
        for (int i = 0; i < result.size(); i++) {
            if (i != maxIndex) {
                result.get(i).setWidth((int)(result.get(i).value / maxValue * result.get(maxIndex).getWidth()));
            }
        }
        doAnimator(result, 0);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private void doAnimator(List<DataInfo> result, int curIndex) {
        if (curIndex >= result.size()) {
            return;
        }
        init(result.get(curIndex), curIndex);

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int index = curIndex + 1;
                doAnimator(result, index);
            }
        }, DURATION_TIME);
    }

    private void init(DataInfo dataInfo, int index) {
        final ViewGroup parent = findViewById(R.id.video_container_layout);
        // 设置Title
        View itemView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_video_detail_item, null);
        if (isShowNum) {
            String num = index < 10 ? "0" + (index + 1) : (index + 1) + "";
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(dataInfo.title + " " + num);
        } else {
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(dataInfo.title);
        }
        // 设置背景
        ViewGroup imageLayout = itemView.findViewById(R.id.item_image_layout);
        imageLayout.setBackgroundResource(R.drawable.animation_back_color);
        ViewGroup.LayoutParams layoutParams = imageLayout.getLayoutParams();
        layoutParams.width = dataInfo.width;
        imageLayout.setLayoutParams(layoutParams);
        // 设置数值
        ((TextView)itemView.findViewById(R.id.item_num_tv)).setText(dataInfo.value + "");
        parent.addView(itemView);

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                itemView.setAlpha(value);
                itemView.setTranslationY(100 * (1 - value));
            }
        });
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.setDuration(1000);
        valueAnimator.setStartDelay(0);
        valueAnimator.start();
    }
}