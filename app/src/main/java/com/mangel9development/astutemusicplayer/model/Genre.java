package com.mangel9development.astutemusicplayer.model;

import static com.mangel9development.astutemusicplayer.utils.methods.makeColorFromWord;

import com.mangel9development.astutemusicplayer.model.Aggregation.Aggregant;

public class Genre extends SongCollection implements Aggregant{
    private final String name;

    public Genre(String name){
        super();
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String color(){
        return makeColorFromWord(name);
    }
}
