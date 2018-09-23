package driver.chao.com.qtan.bean;

import java.io.Serializable;

/**
 * Created by aaron on 2018/9/13.
 * 近期
 */
public class NBean implements Serializable {
    public String date = "";
    public String zhudui = "";
    public String kedui = "";
    public String liansai = "";
    public String zhuPai = "";
    public String kePai = "";
    public String zhuPoint = "";
    public String kePoint = "";
    public String ids = "";
    public String zRate = "";
    public String kRate = "";
    public String yPan = "";
    public String osRate = "";
    public String opRate = "";
    public String ofRate = "";

    @Override
    public String toString() {
        return liansai + " " + date + " " + zhudui + "VS" + kedui + " " + zhuPoint + ":"
                + kePoint + " " + zRate + " " + yPan + " " + kRate + " " + osRate + " "
                + opRate + " " + ofRate + "\n";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getZhudui() {
        return zhudui;
    }

    public void setZhudui(String zhudui) {
        this.zhudui = zhudui;
    }

    public String getKedui() {
        return kedui;
    }

    public void setKedui(String kedui) {
        this.kedui = kedui;
    }

    public String getLiansai() {
        return liansai;
    }

    public void setLiansai(String liansai) {
        this.liansai = liansai;
    }

    public String getZhuPai() {
        return zhuPai;
    }

    public void setZhuPai(String zhuPai) {
        this.zhuPai = zhuPai;
    }

    public String getKePai() {
        return kePai;
    }

    public void setKePai(String kePai) {
        this.kePai = kePai;
    }

    public String getZhuPoint() {
        return zhuPoint;
    }

    public void setZhuPoint(String zhuPoint) {
        this.zhuPoint = zhuPoint;
    }

    public String getKePoint() {
        return kePoint;
    }

    public void setKePoint(String kePoint) {
        this.kePoint = kePoint;
    }

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getzRate() {
        return zRate;
    }

    public void setzRate(String zRate) {
        this.zRate = zRate;
    }

    public String getkRate() {
        return kRate;
    }

    public void setkRate(String kRate) {
        this.kRate = kRate;
    }

    public String getyPan() {
        return yPan;
    }

    public void setyPan(String yPan) {
        this.yPan = yPan;
    }

    public String getOsRate() {
        return osRate;
    }

    public void setOsRate(String osRate) {
        this.osRate = osRate;
    }

    public String getOpRate() {
        return opRate;
    }

    public void setOpRate(String opRate) {
        this.opRate = opRate;
    }

    public String getOfRate() {
        return ofRate;
    }

    public void setOfRate(String ofRate) {
        this.ofRate = ofRate;
    }
}
