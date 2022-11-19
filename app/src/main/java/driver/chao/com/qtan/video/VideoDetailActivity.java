package driver.chao.com.qtan.video;

import static driver.chao.com.qtan.video.VideoActivity.DATA_INFO_BUNDLE;
import static driver.chao.com.qtan.video.VideoActivity.DATA_INFO_CONTENT;
import static driver.chao.com.qtan.video.VideoActivity.VIDEO_INFO_CONTENT;

import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Handler;
import android.os.Looper;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.util.Utils;
import driver.chao.com.qtan.video.bean.DataInfo;
import driver.chao.com.qtan.video.bean.VideoInfo;
import okhttp3.internal.Util;

public class VideoDetailActivity extends AppCompatActivity {

    private static final String MAIN_BACK_COLOR = "#000000";
    /** 动画时长 **/
    private long DURATION_TIME = 1500;
    /** 起始动画延时时间 **/
    private long DELAY_TIME = 1000;
    /** 添加Item动画时长 **/
    private long DURATION_ADD_TIME = 900;
    /** 移除Item动画时长**/
    private long DURATION_REMOVE_TIME = 300;

    private List<DataInfo> result = null;
    private VideoInfo videoInfo = null;
    private RecyclerView recyclerView;
    private VideoAdapter videoAdapter;
    private Handler mHandler = new Handler(Looper.getMainLooper());
    private double total = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_detail);
        recyclerView = findViewById(R.id.recyclerView);
        videoAdapter = new VideoAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setAdapter(videoAdapter);
        MDefaultItemAnimator defaultItemAnimator = new MDefaultItemAnimator();
        defaultItemAnimator.setAddDuration(DURATION_ADD_TIME);
        defaultItemAnimator.setRemoveDuration(DURATION_REMOVE_TIME);
        recyclerView.setItemAnimator(defaultItemAnimator);

        // 初始化数据
        Bundle bundle = getIntent().getBundleExtra(DATA_INFO_BUNDLE);
        result = (ArrayList<DataInfo>) bundle.getSerializable(DATA_INFO_CONTENT);
        videoInfo = (VideoInfo) bundle.getSerializable(VIDEO_INFO_CONTENT);
        if (result == null || result.size() == 0) {
            return;
        }
        // 设置整体背景
        if (!TextUtils.isEmpty(videoInfo.backEdtContent)) {
            findViewById(R.id.video_container_layout).setBackgroundColor(Color.parseColor(videoInfo.backEdtContent));
        } else {
            findViewById(R.id.video_container_layout).setBackgroundColor(Color.parseColor(MAIN_BACK_COLOR));
        }
        // 设置名称颜色
        if (!TextUtils.isEmpty(videoInfo.fontColorContent)) {
            videoAdapter.fontColor = videoInfo.fontColorContent;
        }
        // 设置数值颜色
        if (!TextUtils.isEmpty(videoInfo.numColorContent)) {
            videoAdapter.numColor = videoInfo.numColorContent;
        }
        // 设置Item颜色
        if (!TextUtils.isEmpty(videoInfo.itemColorContent)) {
            videoAdapter.itemColor = videoInfo.itemColorContent;
        }
        // 设置总计位置
        if (!TextUtils.isEmpty(videoInfo.totalLocation)) {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById(R.id.right_content_layout).getLayoutParams();
            if (videoInfo.totalLocation.equals("上")) {
                layoutParams.gravity = Gravity.TOP|Gravity.RIGHT;
            } else if (videoInfo.totalLocation.equals("下")) {
                layoutParams.gravity = Gravity.BOTTOM|Gravity.RIGHT;
            } else {
                layoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.RIGHT;
            }
        } else {
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById(R.id.right_content_layout).getLayoutParams();
            layoutParams.gravity = Gravity.CENTER_VERTICAL|Gravity.RIGHT;
        }
        // 设置总计字号
        if (!TextUtils.isEmpty(videoInfo.totalFontSize)) {
            ((TextView) findViewById(R.id.video_source_tv)).setTextSize(Integer.parseInt(videoInfo.totalFontSize));
            ((TextView) findViewById(R.id.video_name_tv)).setTextSize(Integer.parseInt(videoInfo.totalFontSize));
            ((TextView) findViewById(R.id.video_total_tv)).setTextSize(Integer.parseInt(videoInfo.totalFontSize));
        } else {
            ((TextView) findViewById(R.id.video_source_tv)).setTextSize(16);
            ((TextView) findViewById(R.id.video_name_tv)).setTextSize(16);
            ((TextView) findViewById(R.id.video_total_tv)).setTextSize(16);
        }
        // 设置总计字色
        if (!TextUtils.isEmpty(videoInfo.totalFontColor)) {
            ((TextView) findViewById(R.id.video_source_tv)).setTextColor(Color.parseColor(videoInfo.totalFontColor));
            ((TextView) findViewById(R.id.video_name_tv)).setTextColor(Color.parseColor(videoInfo.totalFontColor));
            ((TextView) findViewById(R.id.video_total_tv)).setTextColor(Color.parseColor(videoInfo.totalFontColor));
        } else {
            ((TextView) findViewById(R.id.video_source_tv)).setTextColor(Color.parseColor("#FFFF00"));
            ((TextView) findViewById(R.id.video_name_tv)).setTextColor(Color.parseColor("#FFFF00"));
            ((TextView) findViewById(R.id.video_total_tv)).setTextColor(Color.parseColor("#FFFF00"));
        }
        // 设置总计右边距
        if (!TextUtils.isEmpty(videoInfo.totalRightMargin)) {
            ((FrameLayout.LayoutParams) findViewById(R.id.right_content_layout).getLayoutParams()).rightMargin = Utils.dip2px(getApplicationContext(), Integer.parseInt(videoInfo.totalRightMargin));
        } else {
            ((FrameLayout.LayoutParams) findViewById(R.id.right_content_layout).getLayoutParams()).rightMargin = Utils.dip2px(getApplicationContext(), 10);
        }
        if (!TextUtils.isEmpty(videoInfo.customContent)) {
            findViewById(R.id.video_custom_layout).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.video_custom_tv)).setText(videoInfo.customContent);
        } else {
            findViewById(R.id.video_custom_layout).setVisibility(View.GONE);
        }

        // 是否快照
        if (videoInfo.isQuick) {
            videoAdapter.setData(result);
            videoAdapter.notifyDataSetChanged();
            if (videoInfo.isShowTotal) {
                findViewById(R.id.video_total_layout).setVisibility(View.VISIBLE);
                double totalValue = result.stream().mapToDouble(value -> value.value).sum();
                if (!TextUtils.isEmpty(videoInfo.totalRatio)) {
                    totalValue = totalValue * Double.parseDouble(videoInfo.totalRatio);
                }
                String totalContent = "";
                if (!TextUtils.isEmpty(videoInfo.totalDecimalCount)) {
                    totalContent = String.format("%." + Integer.parseInt(videoInfo.totalDecimalCount)  + "f", totalValue);
                } else {
                    totalContent = String.format("%.2f", totalValue);
                }
                ((TextView) findViewById(R.id.video_total_tv)).setText("总计:" + totalContent + videoInfo.totalUnit);
            } else {
                findViewById(R.id.video_total_layout).setVisibility(View.GONE);
            }
            updateTotalAndAuthorLayout();
        } else {
            new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
                @Override
                public void run() {
                    updateTotalAndAuthorLayout();
                    doAnimator(result, 0);
                }
            }, DELAY_TIME);
        }
    }

    private void doAnimator(List<DataInfo> result, int curIndex) {
        if (curIndex >= result.size()) {
            if (videoInfo.isTailAnimation) {
                doAnimationFinish();
            }
            return;
        }
        if (curIndex >= 10) {
            videoAdapter.removeData();
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    videoAdapter.addData(result.get(curIndex));
                    updateTotal(result.get(curIndex));
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
        updateTotal(result.get(curIndex));
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

    @SuppressLint("SetTextI18n")
    private void updateTotal(DataInfo dataInfo) {
        if (videoInfo.isShowTotal) {
            findViewById(R.id.video_total_layout).setVisibility(View.VISIBLE);
        } else {
            findViewById(R.id.video_total_layout).setVisibility(View.GONE);
        }
        double temp = total;
        total = total + dataInfo.value;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat((float) temp, (float)total);
        valueAnimator.setDuration(DURATION_ADD_TIME);
        valueAnimator.setStartDelay(0);
        valueAnimator.setInterpolator(new LinearInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                double value = (Float) animation.getAnimatedValue();
                if (!TextUtils.isEmpty(videoInfo.totalRatio)) {
                    value = value * Double.parseDouble(videoInfo.totalRatio);
                }
                String totalContent = "";
                if (!TextUtils.isEmpty(videoInfo.totalDecimalCount)) {
                    totalContent = String.format("%." + Integer.parseInt(videoInfo.totalDecimalCount)  + "f", value);
                } else {
                    totalContent = String.format("%.2f", value);
                }
                ((TextView) findViewById(R.id.video_total_tv)).setText("总计:" + totalContent + videoInfo.totalUnit);
            }
        });
        valueAnimator.start();
    }

    private void updateTotalAndAuthorLayout() {
        if (!TextUtils.isEmpty(videoInfo.source)) {
            findViewById(R.id.video_source_layout).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.video_source_tv)).setText("来源:" + videoInfo.source);
        } else {
            findViewById(R.id.video_source_layout).setVisibility(View.GONE);
        }
        if (!TextUtils.isEmpty(videoInfo.author)) {
            findViewById(R.id.video_name_layout).setVisibility(View.VISIBLE);
            ((TextView) findViewById(R.id.video_name_tv)).setText("整理:" + videoInfo.author);
        } else {
            findViewById(R.id.video_name_layout).setVisibility(View.GONE);
        }
    }
}