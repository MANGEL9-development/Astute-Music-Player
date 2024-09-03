package com.mangel9development.astutemusicplayer.model;

import static com.mangel9development.astutemusicplayer.utils.methods.makeColorFromWord;

import com.mangel9development.astutemusicplayer.model.Aggregation.Aggregant;

public class Genre extends SongCollection implements Aggregant{

    public Genre(String name){
        super(name);
    }

    public String getName(){
        return name;
    }

    public String color(){
        return makeColorFromWord(name);
    }
}
