package com.mangel9development.astutemusicplayer.model.Aggregation;

import com.mangel9development.astutemusicplayer.model.Playlist;

public class Playlists extends Aggregator<Playlist>{

    public Playlists(){
        super();
    }

    public Playlist get(String name){
        try{
            return super.find(name);
        }
        catch(AggregantNotFoundException e){
            return new Playlist(name);
        }
    }
}
