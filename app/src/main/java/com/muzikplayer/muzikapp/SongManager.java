package com.muzikplayer.muzikapp;

import android.graphics.BitmapFactory;
import android.media.MediaMetadataRetriever;
import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;

public class SongManager {



    private ArrayList<Song> al=new ArrayList<Song>();

    public SongManager(){

    }

    public ArrayList<Song> getPlayList() throws IOException {
        al.clear();
        SongHelper songHelper=new SongHelper();

        String dirs[]=songHelper.getStorageDirectories();
        ArrayList<String> paths=new ArrayList<String>();



        int i=0;
        for(String dir:dirs)
        {
            paths.add(dir+"/Music/");
        }
        i=0;

        for(File f:new File("/storage/").listFiles() )
        {
            paths.add(f.getAbsolutePath()+"/Music/");
        }




        int j = 0;
        for(String path:paths) {
            File dir=new File(path);
            if (dir.exists() && dir.listFiles()!=null) {
                for (File file : dir.listFiles()) {
                    if (file.getName().contains(".mp3")||file.getName().toLowerCase().contains(".mp3")||file.getName().toLowerCase().contains(".wav")||file.getName().toLowerCase().contains(".3gp")) {
                        Song song = songHelper.getSongDetails(file.getAbsolutePath(),file.getName());
                        song.setSongcount(j + "");
                        al.add(song);
                        j += 1;
                    }

                }
            }
        }
        return al;
    }


}

class FileExtensionFilter implements FilenameFilter {
    public boolean accept(File dir, String name) {
        return (name.endsWith(".mp3" )  || name.endsWith(".3gp" ) || name.endsWith(".wav")) ;
    }
}

class SongHelper{
    private static final Pattern DIR_SEPORATOR = Pattern.compile("/");
    MediaMetadataRetriever metaRetriver ;
    SongHelper(){
        metaRetriver= new MediaMetadataRetriever();
    }

    Song getSongDetails(String path,String name){
        Song song=new Song();
        metaRetriver.setDataSource(path);
        if(metaRetriver.getEmbeddedPicture()!=null) {
            BitmapFactory.Options options = new BitmapFactory.Options();
            options.inSampleSize = 8;
            byte[] art = metaRetriver.getEmbeddedPicture();
            song.setSongpic(BitmapFactory.decodeByteArray(art, 0, art.length,options));
        }
        else{

            song.setSongpic(null);
        }

        song.setSongauthor(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST));
        song.setSongpath(path);
        if(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE)==null)
            song.setSongtitle(name);
        else
            song.setSongtitle(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_TITLE));
        song.setSongalbym(metaRetriver.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ALBUM));
        return song;
    }

    public  String[] getStorageDirectories()
    {
        // Final set of paths
        final Set<String> rv = new HashSet<String>();
        // Primary physical SD-CARD (not emulated)
        final String rawExternalStorage = System.getenv("EXTERNAL_STORAGE");
        // All Secondary SD-CARDs (all exclude primary) separated by ":"
        final String rawSecondaryStoragesStr = System.getenv("SECONDARY_STORAGE");
        // Primary emulated SD-CARD
        final String rawEmulatedStorageTarget = System.getenv("EMULATED_STORAGE_TARGET");
        if(TextUtils.isEmpty(rawEmulatedStorageTarget))
        {
            // Device has physical external storage; use plain paths.
            if(TextUtils.isEmpty(rawExternalStorage))
            {
                // EXTERNAL_STORAGE undefined; falling back to default.
                rv.add("/storage/sdcard0");
            }
            else
            {
                rv.add(rawExternalStorage);
            }
        }
        else
        {
            // Device has emulated storage; external storage paths should have
            // userId burned into them.
            final String rawUserId;
            if(Build.VERSION.SDK_INT < Build.VERSION_CODES.JELLY_BEAN_MR1)
            {
                rawUserId = "";
            }
            else
            {
                final String path = Environment.getExternalStorageDirectory().getAbsolutePath();
                final String[] folders = DIR_SEPORATOR.split(path);
                final String lastFolder = folders[folders.length - 1];
                boolean isDigit = false;
                try
                {
                    Integer.valueOf(lastFolder);
                    isDigit = true;
                }
                catch(NumberFormatException ignored)
                {
                }
                rawUserId = isDigit ? lastFolder : "";
            }
            // /storage/emulated/0[1,2,...]
            if(TextUtils.isEmpty(rawUserId))
            {
                rv.add(rawEmulatedStorageTarget);
            }
            else
            {
                rv.add(rawEmulatedStorageTarget + File.separator + rawUserId);
            }
        }
        // Add all secondary storages
        if(!TextUtils.isEmpty(rawSecondaryStoragesStr))
        {
            // All Secondary SD-CARDs splited into array
            final String[] rawSecondaryStorages = rawSecondaryStoragesStr.split(File.pathSeparator);
            Collections.addAll(rv, rawSecondaryStorages);
        }
        return rv.toArray(new String[rv.size()]);
    }

}
