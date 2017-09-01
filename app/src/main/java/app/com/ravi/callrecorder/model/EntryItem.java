package app.com.ravi.callrecorder.model;

/**
 * Created by Moweb on 04-01-2016.
 */
public class EntryItem implements Item {


    String name;
    String callType;
    String isCloud;
    String callDuration;
    String perPic;
    String time;
    String isSave;
    String tempfilepath;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsSave() {
        return isSave;
    }

    public void setIsSave(String isSave) {
        this.isSave = isSave;
    }

    public String getTempfilepath() {
        return tempfilepath;
    }

    public void setTempfilepath(String tempfilepath) {
        this.tempfilepath = tempfilepath;
    }

    public EntryItem(String name, String callType, String isCloud, String callDuration, String perPic, String time, String tempfilepath, String isSave,String id) {
        this.name = name;
        this.callType = callType;
        this.isCloud = isCloud;
        this.callDuration = callDuration;
        this.perPic = perPic;
        this.time = time;
        this.tempfilepath = tempfilepath;
        this.isSave = isSave;
        this.id = id;

    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCallType() {
        return callType;
    }

    public void setCallType(String callType) {
        this.callType = callType;
    }

    public String getIsCloud() {
        return isCloud;
    }

    public void setIsCloud(String isCloud) {
        this.isCloud = isCloud;
    }

    public String getCallDuration() {
        return callDuration;
    }

    public void setCallDuration(String callDuration) {
        this.callDuration = callDuration;
    }

    public String getPerPic() {
        return perPic;
    }

    public void setPerPic(String perPic) {
        this.perPic = perPic;
    }

    @Override
    public boolean isSection() {
        return false;
    }
}
