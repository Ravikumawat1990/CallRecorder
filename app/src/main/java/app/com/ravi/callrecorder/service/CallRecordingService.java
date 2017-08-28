package app.com.ravi.callrecorder.service;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaRecorder;
import android.os.Environment;
import android.os.IBinder;
import android.telephony.TelephonyManager;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import app.com.ravi.callrecorder.database.NotiModel;
import app.com.ravi.callrecorder.database.tbl_notification;

/**
 * Created by NetSupport on 23-08-2017.
 */

public class CallRecordingService extends Service {

    boolean recording = false;
    int i = 0;
    String fname;
    MediaRecorder recorder;
    boolean isIncoming = false;
    String callStatus = "";
    static boolean flag = false;
    static long start_time, end_time;
    long total_time;
    int min = 1;
    int max = 100000000;
    BroadcastReceiver CallRecorder = new BroadcastReceiver() {
        @Override
        public void onReceive(Context arg0, Intent intent) {

            // TODO Auto-generated method stub
            String state = intent.getStringExtra(TelephonyManager.EXTRA_STATE);
            i++;
            if (TelephonyManager.EXTRA_STATE_OFFHOOK.equals(state)) {
                Toast.makeText(getApplicationContext(), state, Toast.LENGTH_LONG).show();

                Toast.makeText(arg0, "Start CALLED " + recording + fname, Toast.LENGTH_LONG).show();
                if (isIncoming) {
                    callStatus = "true";
                } else {
                    callStatus = "false";
                }

                startRecording();


            }
            if (TelephonyManager.EXTRA_STATE_IDLE.equals(state) && recording == true) {
                Toast.makeText(getApplicationContext(), state, Toast.LENGTH_LONG).show();

                Toast.makeText(arg0, "STOP CALLED :" + recording, Toast.LENGTH_LONG).show();

                stopRecording();
            }

            if (TelephonyManager.EXTRA_STATE_RINGING.equals(state)) {

                isIncoming = true;
                fname = intent.getStringExtra(TelephonyManager.EXTRA_INCOMING_NUMBER);
                // Toast.makeText(getApplicationContext(), "isIncoming true", Toast.LENGTH_LONG).show();

                Toast.makeText(getApplicationContext(), state + " : " + fname, Toast.LENGTH_LONG).show();
            }
        }
    };
    BroadcastReceiver OutGoingNumDetector = new BroadcastReceiver() {

        @Override
        public void onReceive(Context context, Intent intent) {
            // TODO Auto-generated method stub
            fname = intent.getStringExtra(Intent.EXTRA_PHONE_NUMBER);
            Toast.makeText(getApplicationContext(), "isIncoming false", Toast.LENGTH_LONG).show();
            isIncoming = false;
        }
    };

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        Toast.makeText(getApplicationContext(), "Service Created", Toast.LENGTH_LONG).show();
        IntentFilter RecFilter = new IntentFilter();
        RecFilter.addAction("android.intent.action.PHONE_STATE");
        registerReceiver(CallRecorder, RecFilter);
        IntentFilter OutGoingNumFilter = new IntentFilter();
        OutGoingNumFilter.addAction("android.intent.action.NEW_OUTGOING_CALL");
        registerReceiver(OutGoingNumDetector, OutGoingNumFilter);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        return super.onStartCommand(intent, flags, startId);

    }

    @Override
    public IBinder onBind(Intent arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        super.onDestroy();
        //   unregisterReceiver(CallRecorder);
        // unregisterReceiver(OutGoingNumDetector);
        Toast.makeText(getApplicationContext(), "Destroyed", Toast.LENGTH_SHORT).show();
    }

    public void startRecording() {
        if (recording == false) {
            Toast.makeText(getApplicationContext(), "Recorder_Sarted" + fname, Toast.LENGTH_LONG).show();
            recorder = new MediaRecorder();

            start_time = System.currentTimeMillis();
            if (recorder == null) {
                recorder = new MediaRecorder();
            }

            try {
                recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
                recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
                recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

            } catch (Exception e) {
                e.getMessage();
            }

            String file = Environment.getExternalStorageDirectory().toString();
            String filepath = file + "/CallRecords";
            File dir = new File(filepath);
            dir.mkdirs();
            Random r = new Random();
            int i1 = r.nextInt(max - min + 1) + min;
            filepath += "/" + fname + "" + String.valueOf(i1) + "" + ".3gp";
            recorder.setOutputFile(filepath);


            String date = new SimpleDateFormat("EEE, dd MMM yyyy").format(new Date());
            DateFormat df = new SimpleDateFormat("hh:mm a"); //format time
            String time = df.format(Calendar.getInstance().getTime());

            ArrayList<NotiModel> notiModels = new ArrayList<>();
            NotiModel notiModel = new NotiModel();
            notiModel.setName(fname);
            notiModel.setNumber(filepath);
            notiModel.setDatetime(date);
            notiModel.setCallType(callStatus);
            notiModel.setCallDuration(String.valueOf(total_time));
            notiModel.setTime(time);
            notiModel.setTempFile(filepath);
            notiModel.setIsSave("false");
            notiModels.add(notiModel);

            tbl_notification.Insert(notiModels);
            total_time = 0;

            try {
                recorder.prepare();
            } catch (IllegalStateException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            recorder.start();
            recording = true;
        }
    }

    public void stopRecording() {
        if (recording == true) {
            Toast.makeText(getApplicationContext(), "Recorder_Relesed from " + recording, Toast.LENGTH_LONG).show();
            end_time = System.currentTimeMillis();
            //Total time talked =
            total_time = end_time - start_time;
            recorder.stop();
            recorder.reset();
            recorder.release();
            recording = false;
            broadcastIntent();
        }
    }

    public void broadcastIntent() {
        Intent intent = new Intent();
        intent.setAction("com.exampled.beta.CUSTOM_INTENT");
        sendBroadcast(intent);
        Toast.makeText(getApplicationContext(), "BroadCaste", Toast.LENGTH_LONG).show();

    }
}