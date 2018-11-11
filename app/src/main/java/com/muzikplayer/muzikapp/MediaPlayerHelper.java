package com.muzikplayer.muzikapp;

import android.media.MediaPlayer;
import android.widget.Toast;

public class MediaPlayerHelper {
    static MediaPlayer mp;
    Song song;

    void setSong(Song song){
        this.song=song;
    }

    Song getSong(){
        return song;
    }

    MediaPlayerHelper(){
        mp=new MediaPlayer();
    }

    boolean isPlaying(){
        return mp.isPlaying();
    }

    void setDataSource(String path)
    {
        try{
            mp.setDataSource(path);
            mp.prepare();
            System.out.println("SONG SET");
        }
        catch(Exception e)
        {
            System.out.println("Error" + e);
        }
    }

    void playSong(){
        if(mp!=null) {
            try {
                mp.start();
                System.out.println("Song Playing");
            } catch (Exception e) {
                System.out.println("Error" + e);
            }
        }
    }

    void pauseSong(){
        try{
            mp.pause();
            System.out.println("Song Paused");
        }
        catch(Exception e)
        {

        }
    }

    void stopSong(){
        try{
            mp.stop();
            mp.reset();
        }
        catch(Exception e)
        {
            System.out.println("Error" + e);
        }
    }


}
