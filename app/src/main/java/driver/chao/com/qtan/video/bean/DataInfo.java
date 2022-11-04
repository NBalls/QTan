package driver.chao.com.qtan.video.bean;

import java.io.Serializable;

public class DataInfo implements Serializable {

    public String title;
    public double value;

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
}
