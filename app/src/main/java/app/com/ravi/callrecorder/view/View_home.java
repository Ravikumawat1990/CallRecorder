package app.com.ravi.callrecorder.view;

import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.aykuttasil.callrecord.CallRecord;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.adapter.ViewPagerAdapter;
import app.com.ravi.callrecorder.fragment.FragHome;
import app.com.ravi.callrecorder.util.MyCallRecordReceiver;
import app.com.ravi.callrecorder.util.Utils;

public class View_home extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    private static final String TAG = "View_home";
    TabLayout tabLayout;
    ViewPager viewPager;
    ViewPagerAdapter viewPagerAdapter;
    CallRecord callRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Utils.onActivityCreateSetTheme(this);
        setContentView(R.layout.activity_view_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setTitle(getString(R.string.recordings));
        setSupportActionBar(toolbar);

        //    callRecord = CallRecord.init(this);

        callRecord = CallRecord.initService(this);
        try {

           /* callRecord = new CallRecord.Builder(this)
                    .setRecordFileName("Record_" + new SimpleDateFormat("ddMMyyyyHHmmss", Locale.US).format(new Date()))
                    .setRecordDirName("CallRecorderDir")
                    .setRecordDirPath(Environment.getExternalStorageDirectory().getPath())
                    .setShowSeed(true)
                    .build();*/

            callRecord.changeReceiver(new MyCallRecordReceiver(callRecord));

            callRecord.enableSaveFile();

            callRecord = new CallRecord.Builder(this)
                    .setRecordFileName("Record_" + new SimpleDateFormat("ddMMyyyyHHmmss", Locale.US).format(new Date()))
                    .setRecordDirName("CallRecord")
                    .setRecordDirPath(Environment.getExternalStorageDirectory().getPath())
                    .setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB)
                    .setOutputFormat(MediaRecorder.OutputFormat.AMR_NB)
                    .setAudioSource(MediaRecorder.AudioSource.VOICE_COMMUNICATION)
                    .setShowSeed(true)
                    .build();

            callRecord.startCallRecordService();

        } catch (Exception e) {
            Log.i(TAG, "onCreate: ");

        }

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        // setFragment(0);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        initView();
    }

    private void initView() {
        viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(viewPagerAdapter);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.view_home, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        /*if (id == R.id.action_settings) {
            return true;
        }*/

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    public void setFragment(int i) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        Fragment fragment = null;
        switch (i) {
            case 0:
                fragment = new FragHome();
                ft.add(R.id.container, fragment).addToBackStack("FragHome");
                fm.popBackStack();
                ft.commit();

                break;


            default:
                break;
        }
    }


    @Override
    protected void onStart() {
        super.onStart();
        try {
            //  callRecord.startCallReceiver();
            callRecord.enableSaveFile();
            callRecord.changeRecordDirName("NewDirName");
        } catch (Exception e) {
            Log.i(TAG, "onStart: " + e.getMessage());

        }

    }

    @Override
    protected void onStop() {


        try {
            Log.i("CallRecord", "StopCallRecordClick");
            callRecord.disableSaveFile();
            callRecord.changeRecordFileName("NewFileName");
        } catch (Exception e) {
            Log.i(TAG, "onStop: " + e.getMessage());

        }

        super.onStop();

    }
}
