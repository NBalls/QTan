package driver.chao.com.qtan.video;

import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.ScaleAnimation;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import driver.chao.com.qtan.R;
import driver.chao.com.qtan.util.Utils;
import driver.chao.com.qtan.video.bean.DataInfo;
import driver.chao.com.qtan.video.bean.DataInfoUtil;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String TAG = "######";
    /** 页面背景颜色 **/
    private String BACK_COLOR = "#000000";
    /** 是否降序 **/
    private boolean isAsc = false;
    /** 是否显示需要 **/
    private boolean isShowNum = true;
    /** 动画时长 **/
    private long DURATION_TIME = 2000;
    /** 起始动画延时时间 **/
    private long DELAY_TIME = 2000;

    private long DURATION_ADD_TIME = 800;
    private long DURATION_REMOVE_TIME = 400;

    private List<DataInfo> result = null;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;

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
        recyclerView = findViewById(R.id.recyclerView);
        videoAdapter = new VideoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(videoAdapter);

        MDefaultItemAnimator defaultItemAnimator = new MDefaultItemAnimator();
        defaultItemAnimator.setAddDuration(DURATION_ADD_TIME);
        defaultItemAnimator.setRemoveDuration(DURATION_REMOVE_TIME);
        recyclerView.setItemAnimator(defaultItemAnimator);
    }

    private void initData() {
        boolean isSelected = getIntent().getBooleanExtra("show_num", false);
        videoAdapter.isShowNum = isSelected;
        Bundle bundle = getIntent().getBundleExtra("bundle");
        ArrayList<DataInfo> res = (ArrayList<DataInfo>) bundle.getSerializable("dataInfo");
        if (res == null || res.size() == 0) {
            return;
        }
        // 初始RecyclerView的height=558
        Log.i("##########", "recyclerView.height: " + recyclerView.getHeight());
        result = DataInfoUtil.transferDataInfo(getApplicationContext(), res, isAsc);
        doAnimator(result, 0);
    }

    private Handler mHandler = new Handler(Looper.getMainLooper());

    private void doAnimator(List<DataInfo> result, int curIndex) {
        if (curIndex >= result.size()) {
            doAnimationFinish();
            return;
        }
        if (curIndex >= 10) {
            videoAdapter.removeData();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    videoAdapter.addData(result.get(curIndex));

                    mHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            int index = curIndex + 1;
                            doAnimator(result, index);
                        }
                    }, DURATION_TIME);
                }
            }, DURATION_REMOVE_TIME  + 50);
            return;
        }
        videoAdapter.addData(result.get(curIndex));

        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                int index = curIndex + 1;
                doAnimator(result, index);
            }
        }, DURATION_TIME);
    }

    private void doAnimationFinish() {
        videoAdapter.setData(result);
        LinearLayout linearLayout = findViewById(R.id.linearLayout);
        for (int i = 0; i < result.size(); i++) {
            //手动调用建立和绑定ViewHolder方法，
            RecyclerView.ViewHolder holder = videoAdapter.createViewHolder(recyclerView, videoAdapter.getItemViewType(i));
            videoAdapter.onBindViewHolder(holder, i);
            //测量
            holder.itemView.measure(
                    View.MeasureSpec.makeMeasureSpec(recyclerView.getWidth(), View.MeasureSpec.EXACTLY),
                    View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED));
            //布局
            holder.itemView.layout(0, 0, holder.itemView.getMeasuredWidth(),
                    holder.itemView.getMeasuredHeight());
            //开启绘图缓存
                    /*holder.itemView.setDrawingCacheEnabled(true);
                    holder.itemView.buildDrawingCache();
                    Bitmap drawingCache = holder.itemView.getDrawingCache();
                    if (drawingCache != null) {
                        bitmapCache.put(String.valueOf(i), drawingCache);
                    }
                    //获取itemView的实际高度并累加
                    height += holder.itemView.getMeasuredHeight();*/
            linearLayout.setVisibility(View.VISIBLE);
            linearLayout.addView(holder.itemView);
        }

        linearLayout.post(new Runnable() {
            @Override
            public void run() {
                recyclerView.setVisibility(View.INVISIBLE);
                float scale = (((float) recyclerView.getHeight())) / (linearLayout.getHeight());
                linearLayout.setPivotX(0);
                linearLayout.setPivotY(linearLayout.getHeight());
                ValueAnimator valueAnimator = ValueAnimator.ofFloat(1, scale);
                valueAnimator.setDuration(1000);
                valueAnimator.setStartDelay(0);
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        float value = (Float) animation.getAnimatedValue();
                        linearLayout.setScaleX(value);
                        linearLayout.setScaleY(value);
                    }
                });
                valueAnimator.start();
            }
        });
    }
}