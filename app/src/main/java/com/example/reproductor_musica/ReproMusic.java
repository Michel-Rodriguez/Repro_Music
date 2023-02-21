package com.example.reproductor_musica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;

public class ReproMusic extends AppCompatActivity implements MediaController.MediaPlayerControl{


    MediaController mc;
    MediaPlayer mp;
    Handler h;
    TextView tSong;
    ImageView imgP;

    int[] songs = {R.raw.queen_bohemian_rhapsody, R.raw.the_rolling_stones_paint_it_black,
            R.raw.oasis_wonderwall, R.raw.system_of_a_down_toxicity_}; //array de canciones

    int[] imags = {R.drawable.queen, R.drawable.rolling_stones_logo,
            R.drawable.oasis_logo, R.drawable.system_ofad_logo};






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repro_music);
        Bundle operadores = getIntent().getExtras();
        String op1 = operadores.getString("tituloCanc");
        int op2 = operadores.getInt("numCancion");

        tSong = findViewById(R.id.txtSong);
        tSong.setText(op1);

        imgP = findViewById(R.id.imageView3);
        imgP.setImageResource(imags[op2]);











        mc= new MediaController(this);
        mc.setMediaPlayer(this);
        mc.setAnchorView(findViewById(R.id.ConstraitLayout));
        mp = MediaPlayer.create(this, songs[op2]);
        mp.start();


        h=new Handler();
        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                h.post(new Runnable() {
                    @Override
                    public void run() {
                        mc.show(0);
                    }
                });
            }
        });

    }

    @Override
    public void start() {
        if(!mp.isPlaying())
            mp.start();
    }

    @Override
    public void pause() {
        if(mp.isPlaying())
            mp.pause();
    }

    @Override
    public int getDuration() {
        return mp.getDuration();
    }

    @Override
    public int getCurrentPosition() {
        return mp.getCurrentPosition();
    }

    @Override
    public void seekTo(int i) {
        mp.seekTo(i);
    }

    @Override
    public boolean isPlaying() {
        return mp.isPlaying();
    }

    @Override
    public int getBufferPercentage() {
        return 0;
    }

    @Override
    public boolean canPause() {
        return true;
    }

    @Override
    public boolean canSeekBackward() {
        return true;
    }

    @Override
    public boolean canSeekForward() {
        return true;
    }

    @Override
    public int getAudioSessionId() {
        return mp.getAudioSessionId();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(event.getAction()==MotionEvent.ACTION_DOWN)
            if(!mc.isShowing())
                mc.show(0);
            else
                mc.hide();
        return false;
    }



}