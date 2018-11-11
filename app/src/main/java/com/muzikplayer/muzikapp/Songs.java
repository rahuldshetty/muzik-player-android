package com.muzikplayer.muzikapp;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */

class GSONObject implements Serializable {
    @SerializedName("songs")
    ArrayList<Song> songs;

    public ArrayList<Song> getSongs() {
        return songs;
    }

    public void setSongs(ArrayList<Song> songs) {
        this.songs = songs;
    }

    public GSONObject(ArrayList<Song> songs){
        this.songs=songs;
    }
    public GSONObject(){

    }

}

public class Songs extends Fragment {

    ListView songLists;
    View mView;
    SongAdapter songAdapter;
    SongManager songManager;
    ArrayList<Song> songs;
    String key = "Key";
    SharedPreferences shref;
    SharedPreferences.Editor editor;

    public Songs() {
        // Required empty public constructor

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_songs, container, false);

        shref = mView.getContext().getSharedPreferences("songdata", Context.MODE_PRIVATE);
        editor=shref.edit();
        Gson gson = new Gson();



        songLists = mView.findViewById(R.id.song_listview);
        songManager = new SongManager();
        try {
            songs = songManager.getPlayList();
            songAdapter = new SongAdapter(this.getContext(), songs);
            songLists.setAdapter(songAdapter);
        }
        catch (Exception e) {
            System.out.print("Exception:"+e);
        }




        return mView;
    }

    public class SongAdapter extends ArrayAdapter<Song>{

        private  Context context;
        private  ArrayList<Song> values;

        public SongAdapter(Context context, ArrayList<Song> objects) {
            super(context, R.layout.singlesong, objects);
            this.context = context;
            this.values = objects;
        }

        @Override
        public View getView(int position,  View convertView, ViewGroup parent) {
            LayoutInflater inflater=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            View view =inflater.inflate(R.layout.singlesong,parent,false);

            TextView title,artist,count;
            ImageView pic;

            title=view.findViewById(R.id.songname);
            artist=view.findViewById(R.id.songartist);
            count=view.findViewById(R.id.songcount);

            pic=view.findViewById(R.id.songimage);

            Song song=values.get(position);
            title.setText(song.getSongtitle());
            artist.setText(song.getSongauthor());
            count.setText(song.getSongcount());
            pic.setImageBitmap(song.getSongpic());

            return view;
        }
    }


}
