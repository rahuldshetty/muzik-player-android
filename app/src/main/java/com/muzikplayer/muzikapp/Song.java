package com.muzikplayer.muzikapp;

import android.graphics.Bitmap;

public class Song {
    private String songtitle,songauthor,songcount,songpath,songalbym;
    private Bitmap songpic;

    public Song(){

    }

    public Song(String songtitle, String songauthor, String songcount, String songpath, String songalbym, Bitmap songpic) {
        this.songtitle = songtitle;
        this.songauthor = songauthor;
        this.songcount = songcount;
        this.songpath = songpath;
        this.songalbym = songalbym;
        this.songpic = songpic;
    }

    public String getSongtitle() {
        return songtitle;
    }

    public void setSongtitle(String songtitle) {
        this.songtitle = songtitle;
    }

    public String getSongauthor() {
        return songauthor;
    }

    public void setSongauthor(String songauthor) {
        this.songauthor = songauthor;
    }

    public String getSongcount() {
        return songcount;
    }

    public void setSongcount(String songcount) {
        this.songcount = songcount;
    }

    public Bitmap getSongpic() {
        return songpic;
    }

    public void setSongpic(Bitmap songpic) {
        this.songpic = songpic;
    }

    public String getSongpath() {
        return songpath;
    }

    public void setSongpath(String songpath) {
        this.songpath = songpath;
    }

    public String getSongalbym() {
        return songalbym;
    }

    public void setSongalbym(String songalbym) {
        this.songalbym = songalbym;
    }
}
