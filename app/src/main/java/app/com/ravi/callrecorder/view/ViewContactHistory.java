package app.com.ravi.callrecorder.view;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Collections;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.adapter.CustomListAdapter;
import app.com.ravi.callrecorder.adapter.callAdapter;
import app.com.ravi.callrecorder.database.NotiModel;
import app.com.ravi.callrecorder.database.tbl_notification;
import app.com.ravi.callrecorder.model.EntryItem;
import app.com.ravi.callrecorder.model.Item;
import app.com.ravi.callrecorder.model.Pojocallhist;
import app.com.ravi.callrecorder.model.SectionItem;
import app.com.ravi.callrecorder.util.CM;
import app.com.ravi.callrecorder.util.Utils;

public class ViewContactHistory extends AppCompatActivity {

    Toolbar toolbar;
    ArrayList<NotiModel> models;
    ArrayList<Item> items = new ArrayList<Item>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_view_contact_history);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CM.finishActivity(ViewContactHistory.this);

            }
        });


        String number = getIntent().getStringExtra("number");
        models = tbl_notification.getSortDateNumber(number);


        ListView listView = (ListView) findViewById(R.id.recycleview);

        if (models != null) {
            Collections.reverse(models);
            for (int i = 0; i < models.size(); i++) {

                items.add(new SectionItem("", models.get(i).getDatetime()));
                ArrayList<NotiModel> notiModels = tbl_notification.getAllDataNumber(number, models.get(i).getDatetime());
                for (int j = 0; j < notiModels.size(); j++) {
                    items.add(new EntryItem(notiModels.get(j).getName(), notiModels.get(j).getCallType(), notiModels.get(j).getIsCloud(), notiModels.get(j).getCallDuration(), notiModels.get(j).getPerPic(), notiModels.get(j).getTime(), notiModels.get(j).getTempFile(), notiModels.get(j).getIsSave(), String.valueOf(notiModels.get(j).getId())));
                }
            }
        }


        callAdapter mAdapter = new callAdapter(items, this);


        listView.setAdapter(mAdapter);
        listView.invalidate();


        //CustomListAdapter customListAdapter = new CustomListAdapter(this, pojocallhists);
        //listView.setAdapter(customListAdapter);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        CM.finishActivity(this);
    }
}
