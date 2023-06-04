package com.mangel9development.astutemusicplayer.model.Aggregation;


import java.util.ArrayList;

public class Aggregator<Type extends Aggregant>{
    final ArrayList<Type> contents;

    public Aggregator(){
        contents=new ArrayList<>();
    }

    public Type add(Type element){
        contents.add(element);
        return element;
    }

    protected Type find(String name) throws AggregantNotFoundException{
        for(Type element:contents){
            if(element.getName().equals(name)){
                return element;
            }
        }
        throw new AggregantNotFoundException("No aggregant found with the name "+name);
    }
}
