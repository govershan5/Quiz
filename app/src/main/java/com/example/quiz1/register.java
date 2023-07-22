package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.VideoView;

public class register extends AppCompatActivity {
VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        video=findViewById(R.id.video);
        String vp="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri= Uri.parse(vp);
        video.setVideoURI(uri);
        video.setMediaController(null);
        video.setOnPreparedListener(mp->mp.setLooping(true));
        video.start();

    }

    @Override
    public void onBackPressed() {
        if (video != null && video.isPlaying()) {
            video.pause();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (video != null && !video.isPlaying()) {
            video.start();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (video != null && video.isPlaying()) {
            video.pause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (video != null) {
            video.stopPlayback();
        }
    }
}