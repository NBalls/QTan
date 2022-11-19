package driver.chao.com.qtan.video.bean;

import java.io.Serializable;

public class VideoInfo implements Serializable {
    // 是否快照
    public boolean isQuick;
    // 是否需要结尾动画
    public boolean isTailAnimation;
    // 是否展示总计
    public boolean isShowTotal;
    // 来源
    public String source;
    // 作者
    public String author;
    // 整体背景
    public String backEdtContent;
    // 字体颜色
    public String fontColorContent;
    // 数值颜色
    public String numColorContent;
    // Item颜色
    public String itemColorContent;
    // 总计位置（上中下）
    public String totalLocation;
    // 总计字体大小
    public String totalFontSize;
    // 总计文案颜色
    public String totalFontColor;
    // 总计右侧边距
    public String totalRightMargin;
    // 总计系数
    public String totalRatio;
    // 总计小数位
    public String totalDecimalCount;
    // 总计单位
    public String totalUnit;
    // 自定义内容
    public String customContent;
}
