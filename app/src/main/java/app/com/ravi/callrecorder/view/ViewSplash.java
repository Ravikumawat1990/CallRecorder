package app.com.ravi.callrecorder.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.util.CM;

public class ViewSplash extends AppCompatActivity {
    SharedPreferences prefs = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_view_splash);
        prefs = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        int secondsDelayed = 1;
        new Handler().postDelayed(new Runnable() {
            public void run() {

                if (prefs.getBoolean("firstrun", true)) {
                    prefs.edit().putBoolean("firstrun", false).commit();
                    startActivity(new Intent(ViewSplash.this, View_Theme.class));
                    finish();
                } else {
                    startActivity(new Intent(ViewSplash.this, View_home.class));
                    finish();


                }
            }
        }, secondsDelayed * 3000);
    }
}
