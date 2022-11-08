package driver.chao.com.qtan.video.bean;

import java.io.Serializable;

public class DataInfo implements Serializable {
    // 左侧名称
    public String title;
    // 左侧序号(从1开始)
    public int num;
    // 数量
    public double value;
    // 宽度
    public int width;
    // 颜色
    public String color = "#FFFFFF";

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

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
