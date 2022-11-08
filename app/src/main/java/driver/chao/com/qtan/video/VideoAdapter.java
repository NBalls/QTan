package driver.chao.com.qtan.video;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import driver.chao.com.qtan.R;
import driver.chao.com.qtan.video.bean.DataInfo;

public class VideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<DataInfo> data = null;
    private boolean isScale = false;
    private float scale = 0.5f;

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.activity_video_detail_item, viewGroup, false);
        return new VideoViewHolder(itemView);
    }

    @SuppressLint("WrongConstant")
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View itemView = holder.itemView;
        Log.i("###########", "position: " + position);
        // if (isShowNum) {
            int index = data.get(position).num;
            String num = index < 10 ? "0" + index : "" + index;
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(data.get(position).title + " " + num);
        /*} else {
            ((TextView)itemView.findViewById(R.id.item_name_tv)).setText(dataInfo.title);
        }*/
        // 设置背景
        ViewGroup imageLayout = itemView.findViewById(R.id.item_image_layout);
        GradientDrawable drawable=new GradientDrawable();
        drawable.setShape(GradientDrawable.RECTANGLE);
        drawable.setGradientType(GradientDrawable.RECTANGLE);
        drawable.setCornerRadius(9);
        drawable.setColor(Color.parseColor(data.get(position).getColor()));
        imageLayout.setBackground(drawable);

        // imageLayout.setBackgroundResource(R.drawable.animation_back_color);
        ViewGroup.LayoutParams layoutParams = imageLayout.getLayoutParams();
        layoutParams.width = data.get(position).width;
        imageLayout.setLayoutParams(layoutParams);
        // 设置数值
        ((TextView)itemView.findViewById(R.id.item_num_tv)).setText(data.get(position).value + "");

        if (isScale) {
            itemView.setScaleY(0.5f);
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

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    public boolean isScale() {
        return isScale;
    }

    public void setScale(boolean scale) {
        isScale = scale;
    }

    static class VideoViewHolder extends RecyclerView.ViewHolder {

        public VideoViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
