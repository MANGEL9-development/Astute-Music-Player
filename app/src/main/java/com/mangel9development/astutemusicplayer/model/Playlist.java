package com.mangel9development.astutemusicplayer.model;

import com.mangel9development.astutemusicplayer.model.Aggregation.Aggregant;

public class Playlist extends SongCollection implements Aggregant{
    private String name;

    public Playlist(String name){
        super();
        this.name=name;
    }

    @Override
    public String getName(){
        return name;
    }

    public void setName(String name){
        this.name=name;
    }

    /**
     * Adds a song to the playlist
     * @param song the song to be added to this Playlist
     * @return true if the song was added, or false if the song is already in the playlist
     */
    @Override
    public boolean add(Song song){
        if(contains(song)){
            return false;
        }
        super.add(song);
        return true;
    }
}
