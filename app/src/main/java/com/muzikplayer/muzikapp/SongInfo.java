package com.muzikplayer.muzikapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        artist=findViewById(R.id.songinfo_artist);
        startTime=findViewById(R.id.songinfo_start);
        endTime=findViewById(R.id.songinfo_end);

        progress=findViewById(R.id.seekBar);

        Song song=songHelper.getSongDetails(path,name);
        song.setSongcount(count);

        title.setText(song.getSongtitle());
        artist.setText(song.getSongauthor());
        cover.setImageBitmap(song.getSongpic());



        final Song temp = song;

        if(MainActivity.mediaPlayerHelper.isPlaying() && count.equals(MainActivity.mediaPlayerHelper.getSong().getSongcount()) )
        {
            System.out.println("SAME LINE");
            playBtn.setImageResource(R.drawable.pauseicon8);
        }


        playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!MainActivity.mediaPlayerHelper.isPlaying())
                {
                    if(MainActivity.mediaPlayerHelper.getSong()!=null)
                        if(!MainActivity.mediaPlayerHelper.getSong().getSongcount().equals(temp.getSongcount()) )
                        {
                            MainActivity.mediaPlayerHelper.stopSong();
                        }
                    //play the song
                    MainActivity.mediaPlayerHelper.setSong(temp);
                    MainActivity.mediaPlayerHelper.setDataSource(temp.getSongpath());
                    MainActivity.mediaPlayerHelper.playSong();

                    playBtn.setImageResource(R.drawable.pauseicon8);

                }
                else{
                    MainActivity.mediaPlayerHelper.pauseSong();
                    playBtn.setImageResource(R.drawable.icons8play);
                }

            }
        });



    }
}
