package app.com.ravi.callrecorder.view;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.util.CM;
import app.com.ravi.callrecorder.util.Utils;

public class ViewNotes extends AppCompatActivity {

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CM.finishActivity(ViewNotes.this);

            }
        });
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CM.finishActivity(this);
    }
}
