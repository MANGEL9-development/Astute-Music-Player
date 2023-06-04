package com.mangel9development.astutemusicplayer.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.NoSuchElementException;

public class Queue extends SongCollection{
    /**
     * This is the media that "made" this queue. For example, if the user plays
     */
    private Playable baseMedia;

    /**
     * a list of songs that have already been played
     */
    private final LinkedList<Song> alreadyPlayed;

    /**
     * the song that's currently being played
     */
    private Song currentlyPlayingSong;

    /**
     * This is where songs are added
     */
    private final LinkedList<Song> upNext;

    /**
     * This holds the songs after the Up Next part of the queue. This is the only part of the queue
     * that gets shuffled
     */
    private final LinkedList<Song> restOfQueue;

    /**
     * Constructs an empty Queue
     */
    public Queue(){
        super();
        alreadyPlayed=new LinkedList<>();
        currentlyPlayingSong=null;
        upNext=new LinkedList<>();
        restOfQueue=new LinkedList<>();
        baseMedia=null;
    }

    /**
     * Constructs a queue with one song
     * @param song the only song in the Queue. This queue will be initialized with this Song as the
     *             currently playing song
     */
    public Queue(Song song){
        this();
        currentlyPlayingSong=song;
        baseMedia=song;
    }

    /**
     * Constructs a Queue with all the songs from a {@link SongCollection}
     * @param songs the SongCollection to be played
     * @param shuffle set this to true if the player is on shuffle, or false otherwise
     */
    public Queue(SongCollection songs,boolean shuffle){
        this();
        baseMedia=songs;
        /*boolean onFirstSong=true;
        for(Song song:songs){
            if(onFirstSong){
                currentlyPlayingSong=song;
                onFirstSong=false;
            }
            else{
                restOfQueue.add(song);
            }
        }*/
        Iterator<Song> iterator=songs.iterator();
        if(iterator.hasNext()){ // make sure songs isn't empty
            currentlyPlayingSong=iterator.next();
            while(iterator.hasNext()){
                restOfQueue.add(iterator.next());
            }
            if(shuffle){
                shuffle();
            }
        }
    }

    /**
     * Constructs a queue that starts at a specified position in a SongCollection
     * @param songs a SongCollection to be played
     * @param startingIndex the index at which to start playing
     * @param shuffle set this to true if the player is on shuffle, or false otherwise. If the
     *               player is on shuffle, the song at {@code startingIndex} will be set as the
     *               currently playing song, but all other songs in the SongCollection will be
     *               shuffled into the rest of the queue.
     * @throws IllegalArgumentException if {@code startingIndex} is less than 0
     * @throws IndexOutOfBoundsException if {@code startingIndex} is greater than
     */
    public Queue(SongCollection songs, int startingIndex, boolean shuffle){
        this();
        baseMedia=songs;
        if(startingIndex<0){
            throw new IllegalArgumentException("startingIndex cannot be negative");
        }
        else if(startingIndex>=songs.size()){
            throw new IndexOutOfBoundsException("startingIndex cannot exceed length of SongCollection");
        }
        if(shuffle){
            Song[] songsArray=songs.toArray();
            for(int i=0;i<songsArray.length;i++){
                if(i==startingIndex){
                    currentlyPlayingSong=songsArray[i];
                }
                else{
                    restOfQueue.add(songsArray[i]);
                }
            }
            shuffle();
        }
        else{
            /*final int length=songs.size();
            Iterator<Song> iterator=songs.iterator();
            for(int i=0;i<length && iterator.hasNext();i++){
                if(i<startingIndex){
                    alreadyPlayed.add(iterator.next());
                }
                else if(i==startingIndex){
                    currentlyPlayingSong=iterator.next();
                }
                else{
                    restOfQueue.add(iterator.next());
                }
            }*/
            alreadyPlayed.addAll(songs.subList(0,startingIndex));
            currentlyPlayingSong=songs.get(startingIndex);
            restOfQueue.addAll(songs.subList((startingIndex+1),songs.size()));
        }
    }

    public Song getCurrentlyPlayingSong(){
        return currentlyPlayingSong;
    }

    @NonNull
    @Override
    public Song[] toArray(){
        return (Song[])fullList().toArray();
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts){
        return fullList().toArray(ts);
        //FIXME this might need fixing (along with SongCollection.java)
    }

    @Override
    public boolean add(Song song){
        return addToQueue(song);
    }

    @Override
    public boolean remove(Object o){
        return (o instanceof Song && remove((Song)o));
    }

    public boolean remove(Song song){
        if(currentlyPlayingSong==song){
            return removeCurrentlyPlayingSong();
        }
        return (upNext.remove(song)
             || alreadyPlayed.remove(song)
             || restOfQueue.remove(song));
    }

    private boolean removeCurrentlyPlayingSong(){
        if(currentlyPlayingSong==null){
            return false;
        }
        skip();
        alreadyPlayed.removeLast();
        return true;
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection){
        return fullList().containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Song> collection){
        return restOfQueue.addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection){
        boolean bool;
        bool=alreadyPlayed.removeAll(collection);
        bool=(bool && upNext.removeAll(collection));
        bool=(bool && restOfQueue.removeAll(collection));
        if(collection.contains(currentlyPlayingSong)){
            bool=(bool && removeCurrentlyPlayingSong());
        }
        return bool;

    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection){
        boolean bool;
        bool=alreadyPlayed.retainAll(collection);
        bool=(bool && upNext.retainAll(collection));
        bool=(bool && restOfQueue.retainAll(collection));
        if(!collection.contains(currentlyPlayingSong)){
            bool=(bool && removeCurrentlyPlayingSong());
        }
        return bool;
    }

    @Override
    public void clear(){
        alreadyPlayed.clear();
        currentlyPlayingSong=null;
        upNext.clear();
        restOfQueue.clear();
    }

    @Override
    public boolean isEmpty(){
        return (currentlyPlayingSong==null
             && upNext.isEmpty()
             && alreadyPlayed.isEmpty()
             && restOfQueue.isEmpty());
    }

    @Override
    public boolean contains(Object o){
        return list.contains(o);
    }

    /**
     * Finds a song in this SongCollection and returns the index at which the song was found. If the
     * song isn't found, then -1 is returned.
     * @param song the song to look for
     * @return the index where a song is, or -1 if it wasn't found
     */
    public int indexOf(Song song){
        int index=0;
        for(Song currentSong:this){
            if(currentSong==song){
                return index;
            }
            index++;
        }
        return -1;
    }

    /**
     * Returns the song at the specified index in this SongCollection
     * @param index the index at which to find a song
     * @return the song at the specified index
     * @throws IllegalArgumentException if {@code index} is negative
     */
    public Song get(int index){
        if(index<0){
            throw new IllegalArgumentException("Expected non-negative index, found "+index);
        }
        int size=size();
      /*if(index>=size){
            throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+size);
            //this was moved to the end because that is the only condition in which the method would
            //complete the for loop without returning a song and there's no need to have the same
            //throw statement two different places
        }*/
        int currentIndex=0;
        for(Song song:this){
            if(currentIndex==index){
                return song;
            }
            currentIndex++;
        }
        throw new IndexOutOfBoundsException("Index "+index+" out of bounds for length "+size);
    }

    protected ArrayList<Song> subList(int start, int end){
        if(start<0){
            throw new IllegalArgumentException("Cannot start from a negative index ("+start+")");
        }
        if(end<start){
            throw new IllegalArgumentException("Cannot end at a number ("+end+") less than the start");
        }
        final int size=size();
        if(start>=size){
            throw new IndexOutOfBoundsException("Starting index ("+start+") cannot exceed length of queue ("+size+")");
        }
        if(end>=size){
            throw new IndexOutOfBoundsException("Ending index ("+end+") cannot exceed length of queue ("+size+")");
        }

        //the rest of this method could probably be rewritten to take less time
        //maybe by skipping lists
        int index=0;
        ArrayList<Song> list=new ArrayList<>();
        for(Song song:this){
            if(start<=index){
                if(index<end){
                    list.add(song);
                }
            }
            index++;
        }
        return list;
    }



    private LinkedList<Song> fullList(){
        LinkedList<Song> list=new LinkedList<>(alreadyPlayed);
        if(currentlyPlayingSong!=null){
            list.add(currentlyPlayingSong);
        }
        list.addAll(upNext);
        list.addAll(restOfQueue);

        return list;
    }

    private void skip(){
        alreadyPlayed.add(currentlyPlayingSong);
        currentlyPlayingSong=null;

        LinkedList<Song> nextSection=(upNext.isEmpty()?restOfQueue:upNext);
        try{
            currentlyPlayingSong=nextSection.pop();
        }
        catch(NoSuchElementException e){
            // do nothing
        }
    }

    public void playNext(Song song){
        upNext.add(0,song);
    }

    public void addToUpNext(Song song){
        upNext.add(song);
    }

    /**
     * Appends a song to the Up Next section of the queue
     * @param songs the SongCollection whose songs will be appended to the Up Next section
     */
    public void addToUpNext(SongCollection songs){
        for(Song song:songs){
            addToUpNext(song);
        }
    }

    /**
     * Appends a song to the queue.
     * @param song the song to be added to the queue
     * @return {@code true} if this collection changed as a result of the call
     */
    public boolean addToQueue(Song song){
        return restOfQueue.add(song);

    }

    /**
     * Appends a SongCollection to the queue. The songs  will be added in the same order they are in
     * the SongCollection.
     * @param songs the songs to be added to the queue
     */
    public void addToQueue(SongCollection songs){
        restOfQueue.addAll(songs);
    }

    /**
     * Places a {@link Song} in a random position in this queue
     * @param song the song to be thrown in
     * @return the index at which the song was added
     */
    public int throwIn(Song song){
        int randomIndex=(int)(Math.random()*restOfQueue.size());
        restOfQueue.add(randomIndex,song);
        return randomIndex;
    }

    /**
     * Places a {@link SongCollection} in a random position in this queue
     * @param songs the song to be thrown in
     * @return the index at which the SongCollection was added
     */
    public int throwIn(SongCollection songs){
        int randomIndex=(int)(Math.random()*restOfQueue.size());
        restOfQueue.addAll(randomIndex,songs);
        return randomIndex;
    }

    /**
     * Adds all the songs from a {@link SongCollection} to the queue, but each song is added to a
     *      random  position in the queue
     * @param songs a collection of songs to be mixed into the queue
     */
    public void mixIn(SongCollection songs){
        for(Song song:songs){
            throwIn(song);
        }
    }

    /**
     * Shuffles the rest of the queue (this is the part of the queue after the currently playing
     * song and the songs in the Up Next part of the queue).
     */
    public void shuffle(){
        Collections.shuffle(restOfQueue);
    }

    @Override
    public boolean contains(Song song){
        return (currentlyPlayingSong==song
             || upNext.contains(song)
             || alreadyPlayed.contains(song)
             || restOfQueue.contains(song));
        /* The boolean expressions are in that order to make this method take as little time as
           possible. The list that are likely to be shorter searched through first.
         */
    }

    /**
     * Calculates and returns the amount of songs in the queue. This is the sum of the amount of
     * songs that have already been played, the currently playing song, the songs in Up Next, and
     * the rest of the queue
     * @return the amount of songs in the queue
     */
    @Override
    public int size(){
        return alreadyPlayed.size()
                +((currentlyPlayingSong==null)?0:1)
                +upNext.size()
                +restOfQueue.size();
    }

    @NonNull
    @Override
    public Iterator<Song> iterator(){
        return new QueueIteration();
    }


    private class QueueIteration implements Iterator<Song>{
        private static final byte ALREADY_PLAYED=0,
                                  CURRENTLY_PLAYING_SONG=1,
                                  UP_NEXT=2,
                                  REST_OF_QUEUE=3;
        private byte currentSection;
        private int fullIndex;
        private int sectionIndex;
        private final int fullSize;
        private final int[] sizes;

        private QueueIteration(){
            byte hasCurrentLyPlaying=(byte)((currentlyPlayingSong==null)?0:1); // make this a field if need be
            sizes=new int[]{alreadyPlayed.size(),
                    hasCurrentLyPlaying,
                            upNext.size(),
                            restOfQueue.size()};
            fullSize=sizes[ALREADY_PLAYED]
                    +sizes[CURRENTLY_PLAYING_SONG]
                    +sizes[UP_NEXT]
                    +sizes[REST_OF_QUEUE];

            currentSection=0;
            fullIndex=-1;
            sectionIndex=0;
        }

        @Override
        public boolean hasNext(){
            return (fullIndex < (fullSize-1));
        }

        @Override
        public Song next(){
            fullIndex++;
            try{
                switch(currentSection){
                    case ALREADY_PLAYED:
                        if(sectionIndex==sizes[ALREADY_PLAYED]){
                            currentSection=CURRENTLY_PLAYING_SONG;
                            sectionIndex=0;
                            fullIndex--;
                            return next();
                        }
                        return alreadyPlayed.get(sectionIndex++);
                    case CURRENTLY_PLAYING_SONG:
                        if(sectionIndex==sizes[CURRENTLY_PLAYING_SONG]){
                            currentSection=UP_NEXT;
                            sectionIndex=0;
                            fullIndex--;
                            return next();
                        }
                        sectionIndex++;
                        return currentlyPlayingSong;
                    case UP_NEXT:
                        if(sectionIndex==upNext.size()){
                            currentSection=REST_OF_QUEUE;
                            sectionIndex=0;
                            fullIndex--;
                            return next();
                        }
                        return upNext.get(sectionIndex++);
                    case REST_OF_QUEUE:
                        if(sectionIndex==restOfQueue.size()){
                            throw new RuntimeException(); // end of queue has already been reached
                        }
                        return restOfQueue.get(sectionIndex++);
                    default:
                        throw new RuntimeException();
                        /* I don't know what would cause this, but there's definitely a problem if
                           currentSection is something else
                         */
                }
            }
            catch(IndexOutOfBoundsException e){
                throw new RuntimeException(e);
            }
            catch(RuntimeException e){
                throw e;
            }
        }
    }


}
