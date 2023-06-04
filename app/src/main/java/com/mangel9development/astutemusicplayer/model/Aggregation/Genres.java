package com.mangel9development.astutemusicplayer.model.Aggregation;

import com.mangel9development.astutemusicplayer.model.Genre;

public class Genres extends Aggregator<Genre>{

    public Genres(){
        super();
    }

    public Genre get(String name){
        try{
            return super.find(name);
        }
        catch(AggregantNotFoundException e){
            return new Genre(name);
        }
    }
}
