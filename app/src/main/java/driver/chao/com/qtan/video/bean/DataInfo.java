package driver.chao.com.qtan.video.bean;

import java.io.Serializable;

public class DataInfo implements Serializable {
    // 左侧名称
    public String title;
    // 数量
    public double value;
    // 宽度
    public int width;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
