package app.com.ravi.callrecorder.util;

import android.content.Context;

import java.util.Date;

import app.com.ravi.callrecorder.callrecord.CallRecord;
import app.com.ravi.callrecorder.callrecord.receiver.CallRecordReceiver;


/**
 * Created by aykutasil on 26.12.2016.
 */

public class MyCallRecordReceiver extends CallRecordReceiver {

    public MyCallRecordReceiver(CallRecord callRecord) {
        super(callRecord);
    }

    @Override
    protected void onOutgoingCallStarted(Context ctx, String number, Date start) {
        //callRecord.disableSaveFile();
        super.onOutgoingCallStarted(ctx, number, start);
    }
}
