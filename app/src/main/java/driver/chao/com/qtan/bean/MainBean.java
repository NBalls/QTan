package driver.chao.com.qtan.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by aaron on 2018/7/23.
 * 比赛数据
 */
public class MainBean implements Serializable {

    private String id = "";
    private String liansai = "";
    private String time = "";
    private String status = "未开始";
    private String zhu = "";
    private String ke = "";
    private String bifen = "未解析";
    private String bigUrl = "";
    private String result = "-";
    private String ypan = "";
    private Boolean isLike = false;
    public List<YBean> yList = new ArrayList();// 亚盘
    public List<OBean> oList = new ArrayList();// 欧盘
    public List<NBean> dList = new ArrayList();// 对战历史
    public List<NBean> zList = new ArrayList();// 主队近况
    public List<NBean> kList = new ArrayList();// 客队近况

    @Override
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append(liansai + " " + time + "  " + status + "  " + bifen + "  " + zhu + "VS" + ke + "\n");

        sb.append(getYaUrl() + "\n");
        for (int i = 0; i < yList.size(); i ++) {
            sb.append(yList.get(i).toString());
        }

        for (int i = 0; i < oList.size(); i ++) {
            sb.append(oList.get(i).toString());
        }
        return sb.toString();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLiansai() {
        return liansai;
    }

    public void setLiansai(String liansai) {
        this.liansai = liansai;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getZhu() {
        return zhu;
    }

    public void setZhu(String zhu) {
        this.zhu = zhu;
    }

    public String getKe() {
        return ke;
    }

    public void setKe(String ke) {
        this.ke = ke;
    }

    public String getBifen() {
        return bifen;
    }

    public void setBifen(String bifen) {
        this.bifen = bifen;
    }

    public String getYaUrl() {
        return "http://vip.win007.com/AsianOdds_n.aspx?id=" + id;
    }

    public String getOuUrl() {
        return "http://1x2d.win007.com/" + id + ".js";
    }

    public String getAUrl() {
        return "http://info.win0168.com/analysis/" + id +"sb.htm";
    }

    public List<YBean> getyList() {
        return yList;
    }

    public void setyList(List<YBean> yList) {
        this.yList = yList;
    }

    public List<OBean> getoList() {
        return oList;
    }

    public void setoList(List<OBean> oList) {
        this.oList = oList;
    }

    public String getBigUrl() {
        return "http://vip.win007.com/OverDown_n.aspx?id=" + id;
    }

    public void setBigUrl(String bigUrl) {
        this.bigUrl = bigUrl;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getYpan() {
        return ypan;
    }

    public void setYpan(String ypan) {
        this.ypan = ypan;
    }

    public Boolean getLike() {
        return isLike;
    }

    public void setLike(Boolean like) {
        isLike = like;
    }

    public List<NBean> getdList() {
        return dList;
    }

    public void setdList(List<NBean> dList) {
        this.dList = dList;
    }

    public List<NBean> getzList() {
        return zList;
    }

    public void setzList(List<NBean> zList) {
        this.zList = zList;
    }

    public List<NBean> getkList() {
        return kList;
    }

    public void setkList(List<NBean> kList) {
        this.kList = kList;
    }
}
