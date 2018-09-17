package driver.chao.com.qtan.bean;

import java.io.Serializable;

/**
 * Created by aaron on 2018/7/24.
 * 亚盘
 */
public class YBean implements Serializable {
    public String company = "";
    public String startTime = "";
    public String startZRate = "";
    public String startPan = "";
    public String startKRate = "";
    public String endZRate = "";
    public String endPan = "";
    public String endKRate = "";

    @Override
    public String toString() {
        return company + "  " + startTime + "  " + startZRate + "  " + startPan + "  " + startKRate
                + "  " + endZRate + "  " + endPan + "  " + endKRate + "\n";
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getStartZRate() {
        return startZRate;
    }

    public void setStartZRate(String startZRate) {
        this.startZRate = startZRate;
    }

    public String getStartPan() {
        return startPan;
    }

    public void setStartPan(String startPan) {
        this.startPan = startPan;
    }

    public String getStartKRate() {
        return startKRate;
    }

    public void setStartKRate(String startKRate) {
        this.startKRate = startKRate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndZRate() {
        return endZRate;
    }

    public void setEndZRate(String endZRate) {
        this.endZRate = endZRate;
    }

    public String getEndPan() {
        return endPan;
    }

    public void setEndPan(String endPan) {
        this.endPan = endPan;
    }

    public String getEndKRate() {
        return endKRate;
    }

    public void setEndKRate(String endKRate) {
        this.endKRate = endKRate;
    }
}
