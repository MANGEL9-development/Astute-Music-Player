package com.mangel9development.astutemusicplayer.model;

import java.util.LinkedList;

/**
 * Smart playlists allow the user to create playlists that automatically add songs based on a set of
 * rules. The rules in this class are based on those in <a href="http://smarterplaylists.playlistmachinery.com/">
 *     smarterplaylists.playlistmachinery.com/</a>
 */
public class AstutePlaylist extends Playlist{
    public final LinkedList<Rule> rules; /* in the future, this can be changed to be a set of rules
        instead of filters. Rules can include things like "add from source", "sort", etc.*/

    public AstutePlaylist(String name){
        super(name);
        rules=new LinkedList<>();
    }

    /**
     * @return the name of this AstutePlaylist
     */
    @Override
    public String getName(){
        return super.getName();
    }

    /**
     * Sets the name of this AstutePlaylist
     * @param name the new name of this AstutePlaylist
     */
    @Override
    public void setName(String name){
        super.setName(name);
    }

    public void updatePlaylist(){
        // TODO write this
        for(Rule rule:rules){
            rule.affect(this);
        }
    }

    private interface Rule{
        void affect(SongCollection songs);
    }

    private interface Filter extends Rule{
        boolean passesFilter(Song song);
    }

    private static abstract class RangeFilter implements Filter{
        protected double min;
        protected double max;

        protected RangeFilter(double min, double max){
            this.min=min;
            this.max=max;
        }

        protected void setMin(double newMin){
            min=newMin;
        }
        protected void setMax(double newMax){
            max=newMax;
        }
    }

    private interface Source extends Rule{
        //SongCollection source;
    }
}
