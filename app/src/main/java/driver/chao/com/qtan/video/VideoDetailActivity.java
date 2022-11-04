package driver.chao.com.qtan.video;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String TAG = "######";
    /** 页面背景颜色 **/
    private String BACK_COLOR = "#AAAAAA";
    /** 是否降序 **/
    private boolean isAsc = false;
    /** 是否显示需要 **/
    private boolean isShowNum = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        findViewById(R.id.video_container_layout).setBackgroundColor(Color.parseColor(BACK_COLOR));
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ArrayList<DataInfo> result = (ArrayList<DataInfo>) bundle.getSerializable("dataInfo");
        init(result);
    }

    private void init(final ArrayList<DataInfo> res) {
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
        // 获取最大值和最大值位置Index
        int maxIndex = 0;
        double max = result.get(0).value;
        for (int i = 0; i < result.size(); i++) {
            if (result.get(i).value > max) {
                max = result.get(i).value;
                maxIndex = i;
            }
        }
        final ViewGroup parent = findViewById(R.id.video_container_layout);
        for (int i = 0; i < result.size(); i++) {
            // 设置Title
            View itemView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.activity_video_detail_item, null);
            if (isShowNum) {
                String num = i < 10 ? "0" + (i + 1) : (i + 1) + "";
                ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(result.get(i).title + " " + num);
            } else {
                ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(result.get(i).title);
            }
            // 设置背景
            ViewGroup imageLayout = itemView.findViewById(R.id.item_image_layout);
            imageLayout.setBackgroundColor(Color.RED);
            // 设置数值
            ((TextView)itemView.findViewById(R.id.item_num_tv)).setText(result.get(i).value + "");
            parent.addView(itemView);
        }
        parent.measure(View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED), View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));

        final int finalMaxIndex = maxIndex;
        final double finalMax = max;
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                ViewGroup viewGroup = parent.getChildAt(finalMaxIndex).findViewById(R.id.item_image_layout);
                int maxWidth = viewGroup.getWidth();
                double maxValue = finalMax;
                // 获取总的宽度
                for (int i = 0; i < parent.getChildCount(); i++) {
                    if (i != finalMaxIndex) {
                        ViewGroup imageLayout = parent.getChildAt(i).findViewById(R.id.item_image_layout);
                        int curWidth = (int)(result.get(i).value / maxValue * maxWidth);
                        ViewGroup.LayoutParams layoutParams = imageLayout.getLayoutParams();
                        layoutParams.width = curWidth;
                        imageLayout.setLayoutParams(layoutParams);
                    }
                }
            }
        });
    }
}