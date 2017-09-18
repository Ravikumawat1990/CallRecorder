package app.com.ravi.callrecorder.view;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.database.tbl_notification;
import app.com.ravi.callrecorder.util.CM;
import app.com.ravi.callrecorder.util.Utils;

public class ViewNotes extends AppCompatActivity {

    Toolbar toolbar;
    EditText edit_title, edt_msg;
    String id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notes);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);

        edit_title = (EditText) findViewById(R.id.edt_title);
        edt_msg = (EditText) findViewById(R.id.edt_msg);
        id = getIntent().getStringExtra("id");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tbl_notification.updateTbl(edit_title.getText().toString(), edt_msg.getText().toString(), id);
                CM.finishActivity(ViewNotes.this);


            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        tbl_notification.updateTbl(edit_title.getText().toString(), edt_msg.getText().toString(), id);
        CM.finishActivity(this);
    }
}
