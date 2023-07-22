package com.example.quiz1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.SurfaceTexture;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Surface;
import android.view.TextureView;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class Login extends AppCompatActivity implements TextureView.SurfaceTextureListener{
    Button logactbtn;
    EditText logemail,logpass;
    ProgressBar pbar;
    ConstraintLayout layout2;
    FloatingActionButton mail;
    private MediaPlayer mediaPlayer;
    private TextureView textureView;
    VideoView video;
    @SuppressLint({"MissingInflatedId", "ResourceAsColor"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);



        logactbtn=findViewById(R.id.logactbtn);
//        mail=findViewById(R.id.mail);
//        mail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i=new Intent(Intent.ACTION_SEND);
////                intent.setData(Uri.parse("mailto:"));
//                i.setType("text/plain");
//                i.putExtra(Intent.EXTRA_EMAIL,new String[]{"snistresults@gmail.com"});
//                startActivity(Intent.createChooser(i,"Send Email"));
//            }
//        });
        logemail=findViewById(R.id.logemail);
        pbar=findViewById(R.id.pbar);
        video=findViewById(R.id.video);
        logpass=findViewById(R.id.logpass);
        String vp="android.resource://"+getPackageName()+"/"+R.raw.video;
        Uri uri= Uri.parse(vp);
        video.setVideoURI(uri);
        video.setMediaController(null);
        video.setOnPreparedListener(mp -> mp.setLooping(true));
        video.start();
        logactbtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String email=logemail.getText().toString();
                String pass=logpass.getText().toString();
                if(email.length()==0 ||pass.length()==0){
                    Toast.makeText(Login.this, "Please enter The details", Toast.LENGTH_SHORT).show();
                }
                else{

                    log(email,pass);
                }
            }
        });



    }

    private void log(String logemail, String logpass) {



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

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surface, int width, int height) {
        Surface surfaceTexture = new Surface(surface);

        try {
            mediaPlayer = new MediaPlayer();
            mediaPlayer.setDataSource(String.valueOf(Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.video))); // Replace with your video's URI or path
            mediaPlayer.setSurface(surfaceTexture);
            mediaPlayer.prepareAsync();
            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surface, int width, int height) {

    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surface) {
        mediaPlayer.release();
        return true;    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surface) {

    }
}


