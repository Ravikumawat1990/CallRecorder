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


    public EntryItem(String name, String callType, String isCloud, String callDuration, String perPic) {
        this.name = name;
        this.callType = callType;
        this.isCloud = isCloud;
        this.callDuration = callDuration;
        this.perPic = perPic;
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
