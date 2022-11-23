package driver.chao.com.qtan.video.bean;

import java.io.Serializable;

public class DataInfo implements Serializable {
    // 左侧名称
    public String title;
    // 是否显示名称（否则在颜色组件中显示 ）
    public boolean isShowName;
    // 左侧序号(从1开始)
    public int num;
    // 是否显示序号
    public boolean isShowNum = true;
    // 左侧间距
    public int leftMargin = 0;
    // 数量
    public double value;
    // 宽度
    public int width;
    // 颜色
    public String color = "#FFFFFF";
    // 总量前缀
    public String preContent = "";
    // 总量后缀
    public String lastContent = "";
    // money Icon
    public boolean isShowMoney = false;
    // 是否统计数据
    public boolean isStatistics = true;
    // 自定义文案
    public String valueContent = "";
}
