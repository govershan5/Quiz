package com.example.quiz1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {
Button logactbtn,register;
ImageView image;
VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
image=findViewById(R.id.image);
logactbtn=findViewById(R.id.logactbtn);
register=findViewById(R.id.register);
video=findViewById(R.id.video);
image.setImageResource(R.drawable.pic);
        String vp="android.resource://"+getPackageName()+"/"+R.raw.video;
Uri uri= Uri.parse(vp);
video.setVideoURI(uri);
video.setMediaController(null);
video.setOnPreparedListener(mp->mp.setLooping(true));
video.start();

logactbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }
});
register.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent i=new Intent(MainActivity.this, register.class);
        startActivity(i);
    }
});

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