package com.mangel9development.astutemusicplayer.model;


import android.util.Log;

import androidx.annotation.NonNull;

/**
 * This is the class that's responsible for managing the player. This class
 */
public class Player{
    private static Queue currentQueue=null;
    private static ShuffleModes shuffleMode=ShuffleModes.OFF;
    private static RepeatModes repeatMode=RepeatModes.OFF;

    /**
     * Make this class uninstantiable.
     */
    private Player(){}

    public static void play(@NonNull Song song,@NonNull SongCollection context){
        // TODO: implement this
        Log.d("Player","Playing "+song);
        currentQueue=new Queue(song);
    }

    public static void play(Playable media){
        // TODO: implement this
    }

    /**
     * @return the song that's currently being played
     */
    public static Song currentlyPlayingSong(){
        return currentQueue.getCurrentlyPlayingSong();
    }

    public enum ShuffleModes{
        /**
         * In this mode, the queue is automatically shuffled when it's initialized.
         */
        ON,
        /**
         * In this mode, the queue will be initialized with the same order as the base media. If the
         * queue is initialized from a song in the middle (for example if the user taps 5th song in
         * a playlist), all the previous songs will be added to the "already played" section of the
         * queue and the
         */
        OFF
    }

    public static void setShuffleMode(ShuffleModes newMode){
        shuffleMode=newMode;
        /*
            TODO: add functionality to update the UI. This also might have to be done from a view
                model.
         */
        /*
            TODO: shuffle/unshuffle the rest of the queue when this changes
         */
    }

    public static void toggleShuffleMode(){
        setShuffleMode(
                (shuffleMode==ShuffleModes.ON)? ShuffleModes.OFF : ShuffleModes.ON
        );
    }


    public enum RepeatModes{
        /**
         * This means that after the queue is done, the player will stop playing media.
         */
        OFF,
        /**
         * This means that after the queue is done, the player will restart the queue and start
         * playing it from the beginning.
         */
        REPEAT_QUEUE,
        REPEAT_SONG // TODO: decide whether this means a song will be repeated once or indefinitely
    }

    public static void setRepeatMode(RepeatModes newMode){
        repeatMode=newMode;
    }

    /**
     * Cycles through the repeat modes. The order by which the modes will change is OFF ->
     * REPEAT_QUEUE -> REPEAT_SONG (and back to OFF).
     */
    public static void cycleRepeatMode(){
        switch(repeatMode){
            case OFF:
                setRepeatMode(RepeatModes.REPEAT_QUEUE);
                break;
            case REPEAT_QUEUE:
                setRepeatMode(RepeatModes.REPEAT_SONG);
                break;
            case REPEAT_SONG:
                setRepeatMode(RepeatModes.OFF);
                break;
        }
    }
}
