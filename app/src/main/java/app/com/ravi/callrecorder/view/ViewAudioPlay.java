package app.com.ravi.callrecorder.view;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import app.com.ravi.callrecorder.R;
import app.com.ravi.callrecorder.util.CM;

public class ViewAudioPlay extends AppCompatActivity implements View.OnClickListener {

    SeekBar seek_bar;
    Button play_button, pause_button;
    MediaPlayer player;
    TextView text_shown;
    Handler seekHandler = new Handler();
    String fileName = "";
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_audio_play);
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);
        fileName = getIntent().getStringExtra("file");
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CM.finishActivity(ViewAudioPlay.this);

            }
        });
        getInit();
        seekUpdation();
    }

    public void getInit() {
        seek_bar = (SeekBar) findViewById(R.id.seek_bar);
        play_button = (Button) findViewById(R.id.play_button);
        pause_button = (Button) findViewById(R.id.pause_button);
        text_shown = (TextView) findViewById(R.id.text_shown);
        play_button.setOnClickListener(this);
        pause_button.setOnClickListener(this);
        player = MediaPlayer.create(this, Uri.parse(fileName));
        seek_bar.setMax(player.getDuration());
    }

    Runnable run = new Runnable() {
        @Override
        public void run() {
            seekUpdation();
        }
    };

    public void seekUpdation() {
        seek_bar.setProgress(player.getCurrentPosition());
        seekHandler.postDelayed(run, 1000);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.play_button:
                text_shown.setText("Playing...");
                player.start();
                break;
            case R.id.pause_button:
                player.pause();
                text_shown.setText("Paused...");
        }

    }
}