package app.com.ravi.callrecorder.model;


/**
 * Created by Moweb on 04-01-2016.
 */
public class SectionItem implements Item {

    String day;
    String time;


    public SectionItem(String day, String time) {
        this.day = day;
        this.time = time;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public boolean isSection() {
        return true;
    }
}
