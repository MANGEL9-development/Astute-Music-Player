package com.mangel9development.astutemusicplayer.model.Aggregation;

import com.mangel9development.astutemusicplayer.model.Album;
import com.mangel9development.astutemusicplayer.model.Artist;

public class Albums extends Aggregator<Album>{

    public Albums(){
        super();
    }

    public Album get(String name, Artist albumArtist){
        try{
            for(Album currentAlbum:contents){
                if(currentAlbum.getName().equals(name) && currentAlbum.getAlbumArtist()==albumArtist){
                    return currentAlbum;
                }
            }
            throw new AggregantNotFoundException("No aggregant found with the name "+name);
        }
        catch(AggregantNotFoundException e){
            return new Album(name,albumArtist);
        }
    }
}
