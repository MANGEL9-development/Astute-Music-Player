package com.mangel9development.astutemusicplayer.model;

import androidx.annotation.NonNull;

import com.mangel9development.astutemusicplayer.model.Aggregation.Aggregant;

import java.util.ArrayList;

public class Artist extends SongCollection implements Aggregant{
    private final String name;
    private final ArrayList<Album> albums;

    public Artist(String name){
        super();
        this.name=name;
        albums=new ArrayList<>();
    }

    @Override
    public String getName(){
        return name;
    }

    @NonNull
    @Override
    public String toString(){
        return name;
    }

    public void addAlbum(Album album){
        albums.add(album);
    }

    public ArrayList<Album> getAlbums(){
        return albums;
    }
}
