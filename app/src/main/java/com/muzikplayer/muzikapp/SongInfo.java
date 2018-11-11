package com.muzikplayer.muzikapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class SongInfo extends AppCompatActivity {


    SongHelper songHelper;

    ImageView playBtn,fwdBtn,prevBtn,cover;
    TextView title,artist,startTime,endTime;
    SeekBar progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_info);

        songHelper=new SongHelper();


        Intent i=getIntent();
        String path=i.getExtras().getString("SONG_PATH");
        String count=i.getExtras().getString("SONG_COUNT");
        String name=i.getExtras().getString("SONG_NAME");

        playBtn=findViewById(R.id.songinfo_playbtn);
        fwdBtn=findViewById(R.id.songinfo_fwdbtn);
        prevBtn=findViewById(R.id.songinfo_prevbtn);
        cover=findViewById(R.id.songinfo_img);


        title=findViewById(R.id.songinfo_title);
        artist=findViewById(R.id.songinfo_title);
        startTime=findViewById(R.id.songinfo_start);
        endTime=findViewById(R.id.songinfo_end);

        progress=findViewById(R.id.seekBar);

        Song song=songHelper.getSongDetails(path,name);
        song.setSongcount(count);

        title.setText(name);
        artist.setText(song.getSongauthor());
        cover.setImageBitmap(song.getSongpic());



    }
}
