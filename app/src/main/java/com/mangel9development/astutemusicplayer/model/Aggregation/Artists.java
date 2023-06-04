package com.mangel9development.astutemusicplayer.model.Aggregation;

import com.mangel9development.astutemusicplayer.model.Artist;

public class Artists extends Aggregator<Artist>{

    public Artists(){
        super();
    }

    public Artist get(String name){
        try{
            return super.find(name);
        }
        catch(AggregantNotFoundException e){
            return new Artist(name);
        }
    }
}
