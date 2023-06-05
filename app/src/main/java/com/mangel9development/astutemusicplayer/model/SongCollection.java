package com.mangel9development.astutemusicplayer.model;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public abstract class SongCollection implements Iterable<Song>, Collection<Song>, Playable{
    protected ArrayList<Song> list;

    public SongCollection(){
        list=new ArrayList<>();
    }

    @NonNull
    @Override
    public Song[] toArray(){
        Song[] array=new Song[list.size()];
        for(int i=0;i<list.size();i++){
            array[i]=list.get(i);
        }
        return array;
    }

    @NonNull
    @Override
    public <T> T[] toArray(@NonNull T[] ts){
        return list.toArray(ts);
        //FIXME this might need fixing
    }

    @Override
    public boolean add(Song song){
        return list.add(song);
    }

    @Override
    public boolean remove(Object o){
        return list.remove(o);
    }

    @Override
    public boolean containsAll(@NonNull Collection<?> collection){
        return list.containsAll(collection);
    }

    @Override
    public boolean addAll(@NonNull Collection<? extends Song> collection){
        return list.addAll(collection);
    }

    @Override
    public boolean removeAll(@NonNull Collection<?> collection){
        return list.removeAll(collection);
    }

    @Override
    public boolean retainAll(@NonNull Collection<?> collection){
        return list.retainAll(collection);
    }

    @Override
    public void clear(){
        list.clear();
    }

    public int size(){
        return list.size();
    }

    @Override
    public boolean isEmpty(){
        return list.isEmpty();
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
        int i=0;
        for(Song s:this){
            if(s==song){
                return i;
            }
            i++;
        }
        return -1;
    }

    /**
     * Returns true if a song is in this SongCollection, or false otherwise
     * @param song the song to look for
     * @return true if a song is in this SongCollection, or false otherwise
     */
    public boolean contains(Song song){
        return (indexOf(song) >= 0);
    }

    /**
     * Returns the song at the specified index in this SongCollection
     * @param index the index at which to find a song
     * @return the song at the specified index
     */
    public Song get(int index){
        return list.get(index);
    }

    protected ArrayList<Song> subList(int start, int end){
        ArrayList<Song> list=new ArrayList<>();
        Iterator<Song> iterator=iterator(start,end);
        for(Song currentSong=iterator.next();iterator.hasNext();currentSong=iterator.next()){
            list.add(currentSong);
        }
        return list;
    }

    @NonNull
    @Override
    public Iterator<Song> iterator(){
        return new Iteration();
    }

    @NonNull
    public Iterator<Song> iterator(int start,int end){
        return new Iteration(start,end);
    }

    private class Iteration implements Iterator<Song>{
        private int currentIndex;
        private final int end;

        private Iteration(){
            currentIndex=0;
            end=list.size();
        }

        private Iteration(int start,int end){
            int length=list.size();
            if(start<0){
                throw new IllegalArgumentException("start cannot be negative");
            }
            else if(start>=length){
                throw new IndexOutOfBoundsException("start cannot exceed length of SongCollection");
            }
            currentIndex=start;
            this.end=end;
        }

        @Override
        public boolean hasNext(){
            return (currentIndex!=end);
        }

        @Override
        public Song next(){
            return list.get(currentIndex++);
        }
    }


}
