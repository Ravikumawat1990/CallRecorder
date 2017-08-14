package app.com.ravi.callrecorder.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.util.Utils;

public class ViewContactHistory extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_view_contact_history);
    }
}
