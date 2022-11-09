package driver.chao.com.qtan.video;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.util.Utils;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    // 默认左边距
    public static final int DEFAULT_LEFT_MARGIN = 80;
    // 默认名称文案颜色
    public static final String DEFAULT_FONT_COLOR = "#FFFF00";
    private List<DataInfo> data = null;
    public String fontColor = "";
    public String numColor = "";
    public String itemColor = "";

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_video_detail_item, viewGroup, false);
        return new VideoViewHolder(itemView);
    }

    @SuppressLint({"WrongConstant", "SetTextI18n"})
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
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
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(nameContent + " " + num);
        } else {
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(nameContent);
        }
        // 名称颜色
        if (!TextUtils.isEmpty(fontColor)) {
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setTextColor(Color.parseColor(fontColor));
        } else {
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setTextColor(Color.parseColor(DEFAULT_FONT_COLOR));
        }
        // 设置左侧边距
        if (dataInfo.leftMargin != 0) {
            TextView nameTv = ((TextView)itemView.findViewById(R.id.item_name_tv));
            ViewGroup.LayoutParams layoutParams = nameTv.getLayoutParams();
            layoutParams.width = Utils.dip2px(nameTv.getContext(), dataInfo.leftMargin);
        } else {
            TextView nameTv = ((TextView)itemView.findViewById(R.id.item_name_tv));
            ViewGroup.LayoutParams layoutParams = nameTv.getLayoutParams();
            layoutParams.width = Utils.dip2px(nameTv.getContext(), DEFAULT_LEFT_MARGIN);
        }
        // 设置背景
        ViewGroup imageLayout = itemView.findViewById(R.id.item_image_layout);
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(9);
        if (!TextUtils.isEmpty(itemColor)) {
            drawable.setColor(Color.parseColor(itemColor));
        } else {
            drawable.setColor(Color.parseColor(dataInfo.color));
        }
        imageLayout.setBackground(drawable);
        // 设置宽度
        ViewGroup.LayoutParams layoutParams = imageLayout.getLayoutParams();
        layoutParams.width = dataInfo.width;
        imageLayout.setLayoutParams(layoutParams);
        // 设置背景里面的名称
        if (!dataInfo.isShowName) {
            itemView.findViewById(R.id.item_name_tv2).setVisibility(View.VISIBLE);
            ((TextView) itemView.findViewById(R.id.item_name_tv2)).setText(dataInfo.title);
        } else {
            itemView.findViewById(R.id.item_name_tv2).setVisibility(View.GONE);
        }

        // 设置数值
        if (dataInfo.isShowMoney) {
            ((TextView)itemView.findViewById(R.id.item_num_tv)).setText(dataInfo.preContent + dataInfo.value + dataInfo.lastContent + "💰");
        } else {
            ((TextView)itemView.findViewById(R.id.item_num_tv)).setText(dataInfo.preContent + dataInfo.value + dataInfo.lastContent);
        }
        // 设置数值颜色
        if (!TextUtils.isEmpty(numColor)) {
            ((TextView)itemView.findViewById(R.id.item_num_tv)).setTextColor(Color.parseColor(numColor));
        } else {
            ((TextView)itemView.findViewById(R.id.item_num_tv)).setTextColor(Color.WHITE);
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

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
