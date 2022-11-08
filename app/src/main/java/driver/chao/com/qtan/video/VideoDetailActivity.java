package driver.chao.com.qtan.video;

import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.ScaleAnimation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import driver.chao.com.qtan.R;
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
        videoAdapter.setScale(true);
        videoAdapter.notifyDataSetChanged();
        int count = result.size();
        float scale = 558f / (55 * count);
        recyclerView.startAnimation(new ScaleAnimation(1, 1, 1, 0.5f));
        Log.i("##########", "recyclerView.Height: " + recyclerView.getHeight() + "  " + recyclerView.getChildAt(0).getHeight());

    }
}