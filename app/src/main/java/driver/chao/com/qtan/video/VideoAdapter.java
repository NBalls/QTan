package driver.chao.com.qtan.video;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.util.Utils;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.VideoViewHolder> {
    // 默认左边距
    public static final int DEFAULT_LEFT_MARGIN = 80;
    // 默认名称文案颜色
    public static final String DEFAULT_FONT_COLOR = "#FFFF00";
    private List<DataInfo> data = null;
    public String fontColor = "";
    public String numColor = "";
    public String itemColor = "";
    public String defaultCount = "10";

    @NonNull
    @Override
    public VideoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_video_detail_item, viewGroup, false);
        return new VideoViewHolder(itemView);
    }

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull VideoViewHolder holder, int position) {
        View itemView = holder.itemView;
        DataInfo dataInfo = data.get(position);
        String nameContent = "";
        if (dataInfo.isShowName) {
            nameContent = dataInfo.title;
        }
        // 设置名称
        if (dataInfo.isShowNum) {
            int index = data.get(position).num;
            String num = index < 10 ? "0" + index : "" + index;
            holder.nameTv.setText(nameContent + " " + num);
        } else {
            holder.nameTv.setText(nameContent);
        }
        // 名称颜色
        if (!TextUtils.isEmpty(fontColor)) {
            holder.nameTv.setTextColor(Color.parseColor(fontColor));
        } else {
            holder.nameTv.setTextColor(Color.parseColor(DEFAULT_FONT_COLOR));
        }
        // 设置左侧边距
        if (dataInfo.leftMargin != 0) {
            ViewGroup.LayoutParams layoutParams = holder.nameTv.getLayoutParams();
            layoutParams.width = Utils.dip2px(holder.nameTv.getContext(), dataInfo.leftMargin);
        } else {
            ViewGroup.LayoutParams layoutParams = holder.nameTv.getLayoutParams();
            layoutParams.width = Utils.dip2px(holder.nameTv.getContext(), DEFAULT_LEFT_MARGIN);
        }
        // 设置背景
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(9);
        if (!TextUtils.isEmpty(itemColor)) {
            drawable.setColor(Color.parseColor(itemColor));
        } else {
            drawable.setColor(Color.parseColor(dataInfo.color));
        }
        holder.imageLayout.setBackground(drawable);
        // 设置宽度
        ViewGroup.LayoutParams layoutParams = holder.imageLayout.getLayoutParams();
        layoutParams.width = dataInfo.width;
        holder.imageLayout.setLayoutParams(layoutParams);
        // 设置背景里面的名称
        if (!dataInfo.isShowName) {
            holder.nameTv2.setVisibility(View.VISIBLE);
            holder.nameTv2.setText(dataInfo.title);
        } else {
            holder.nameTv2.setVisibility(View.GONE);
        }

        // 设置数值
        if (dataInfo.isStatistics) {
            if (dataInfo.isShowMoney) {
                holder.numTv.setText(dataInfo.preContent + dataInfo.value + dataInfo.lastContent + "💰");
            } else {
                holder.numTv.setText(dataInfo.preContent + dataInfo.value + dataInfo.lastContent);
            }
        } else {
            holder.numTv.setText(dataInfo.preContent + dataInfo.valueContent + dataInfo.lastContent);
        }
        // 设置数值颜色
        if (!TextUtils.isEmpty(numColor)) {
            holder.numTv.setTextColor(Color.parseColor(numColor));
        } else {
            holder.numTv.setTextColor(Color.WHITE);
        }
        // 数值是否可展示
        if (dataInfo.isNumVisible) {
            holder.numTv.setVisibility(View.VISIBLE);
        } else {
            holder.numTv.setVisibility(View.GONE);
        }
        // 判断默认个数
        if (!TextUtils.isEmpty(defaultCount)) {
            if (Integer.parseInt(defaultCount) == 15) {
                holder.constraintLayout.getLayoutParams().height = Utils.dip2px(holder.imageLayout.getContext(), 13.5f);
                holder.nameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9.5f);
                holder.imageLayout.getLayoutParams().height = Utils.dip2px(holder.imageLayout.getContext(), 11);
                holder.nameTv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9);
                holder.numTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 9.5f);
            } else {
                // 默认10个
                holder.constraintLayout.getLayoutParams().height = Utils.dip2px(holder.imageLayout.getContext(), 20);
                holder.nameTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
                holder.imageLayout.getLayoutParams().height = Utils.dip2px(holder.imageLayout.getContext(), 15);
                holder.nameTv2.setTextSize(TypedValue.COMPLEX_UNIT_SP, 11);
                holder.numTv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 13);
            }
        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size(): 0;
    }

    public List<DataInfo> getData() {
        return data;
    }

    public void setData(List<DataInfo> data) {
        this.data = data;
    }

    public void addData(DataInfo dataInfo) {
        if (this.data == null) {
            data = new ArrayList<>();
        }
        data.add(dataInfo);
        notifyItemInserted(data.size() - 1);
    }

    public void removeData() {
        if (this.data == null || data.size() == 0) {
            data = new ArrayList<>();
            return;
        }
        data.remove(0);
        notifyItemRemoved(0);
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        public TextView nameTv;
        public LinearLayout imageLayout;
        public TextView nameTv2;
        public TextView numTv;
        public ConstraintLayout constraintLayout;

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.item_layout_container);
            nameTv = itemView.findViewById(R.id.item_name_tv);
            imageLayout = itemView.findViewById(R.id.item_image_layout);
            nameTv2 = itemView.findViewById(R.id.item_name_tv2);
            numTv = itemView.findViewById(R.id.item_num_tv);
        }
    }
}
