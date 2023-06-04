package com.mangel9development.astutemusicplayer.model;

import androidx.annotation.NonNull;

import com.mangel9development.astutemusicplayer.model.Aggregation.*;
import com.mangel9development.astutemusicplayer.utils.methods;

import java.util.ArrayList;
import java.util.Iterator;

public class Library implements Iterable<Song>{ // TODO make this iterable (this will iterate through the ArrayList songs)
    public static final Albums albums=new Albums();
    public static final Artists artists=new Artists();
    public static final Genres genres=new Genres();
    public static final Playlists playlists=new Playlists();
    public static final ArrayList<Song> songs=new ArrayList<>();

    public static LibraryStatus currentLibraryStatus=LibraryStatus.UNLOADED;

    /**
     * Adds a song to the library. Each song is added in alphabetical order by title
     * @param song the song to be added
     */
    public static void addSong(Song song){
        try{
            binarySearch(song,0,songs.size());
        }
        catch(SongNotInLibraryException e){
            songs.add(e.index,song);
        }
        catch(IndexOutOfBoundsException e){
            songs.add(song);
        }
    }

    private static Song binarySearch(Song song,int start,int end) throws SongNotInLibraryException{
        if(start==end){
            if(songs.get(start)==song){
                return song;
            }
            throw new SongNotInLibraryException(song.getTitle()+" by "+song.getArtist()+" not found in Library",start);
        }

        int midIndex=methods.safeMid(start,end);
        Song midSong=songs.get(midIndex);

        if(midSong==song){
            return song;
        }

        if(midSong.getTitle().compareTo(song.getTitle())>0){ //midSong is before song
            return binarySearch(song,midIndex+1,end);
        }
        else if(midSong.getTitle().compareTo(song.getTitle())<0){ //midSong is after song
            return binarySearch(song,start,midIndex);
        }
        else{
            return pingPongSearch(song,midIndex);
        }
    }

    /**
     * Searches for and returns the song that is the passed song.<br />
     * Ping Pong Search is a procedure where the method starts from a specified index and searches
     * the elements in a ping pong form. For example if the method is passed the number 15 as a
     * starting index, the method will look at the elements at these indices (in this order): 15,
     * 14, 16, 13, 17, 12,... until the element is found.
     * @param song the song to search for
     * @param startingIndex the index to start from
     * @return the song that is the passed song
     * @throws SongNotInLibraryException if the passed song is not in the Library
     */
    private static Song pingPongSearch(Song song,int startingIndex) throws SongNotInLibraryException{
        int distance=0;

        while(songs.get(startingIndex+distance)!=song){
            int newIndex=startingIndex+distance;
            if(newIndex<0){
                continue;
            }
            if(!song.getTitle().equals(songs.get(newIndex).getTitle())){
                throw new SongNotInLibraryException(song.getTitle()+" by "+song.getArtist()+" not found in Library",newIndex);
            }
            distance=(distance<0)?
                    -distance // make it positive
                    :
                    -distance-1; // make it negative then subtract 1
        }

        return song;
    }

    @NonNull
    @Override
    public Iterator<Song> iterator(){
        return songs.iterator();
    }

    public static void loadLibrary(){
        currentLibraryStatus=LibraryStatus.LOADING;
        fakeLoadSongs();
        // TODO: get songs in library and put them in [songs]

        // The following code happens at the end
        currentLibraryStatus=LibraryStatus.LOADED;
    }

    /**
     * This is for testing purposes only and should not be used in the fully functional app. This
     * method uses a fake list of songs and adds them to the library as if they were loaded from the
     * user's device.
     */
    private static void fakeLoadSongs(){
        Song[] exampleSongs=new Song[]{
            new Song(
                "Ella Baila Sola",
                "Eslabon Armado",
                "DESVELADO",
                "Latin"
            ),
            new Song(
                "Let Your Hair Down",
                "MAGIC!",
                "Don't Kill the Magic",
                "Latin"
            ),
            new Song(
                "Starts with Goodbye",
                "Carrie Underwood",
                "Some Hearts",
                "Country"
            ),
            new Song(
                "Often",
                "Kygo, The Weeknd",
                "Often (Kygo Remix)",
                "Tropical"
            ),
            new Song(
                "Bitter",
                "Evie Irie",
                "5 Weeks in LA",
                "Pop"
            ),
                // TODO: make other songs
            /*new Song(
                "Let Your Hair Down",
                "MAGIC!",
                "Don't Kill the Magic",
                "Latin"
            ),
            new Song(
                "Let Your Hair Down",
                "MAGIC!",
                "Don't Kill the Magic",
                "Latin"
            )*/
        };

        for(Song song:exampleSongs){
            addSong(song);
        }
    }

    private static class SongNotInLibraryException extends Exception{
        public final int index;

        public SongNotInLibraryException(String message, int index){
            super(message);
            this.index=index;
        }
    }

    public enum LibraryStatus{
        /**
         * This is the initial state of the library before the app is first run
         */
        UNLOADED,
        /**
         * This means that the library is in the process of loading media.
         */
        LOADING,
        /**
         * This means that the library has finished loading media.
         */
        LOADED,
        /**
         * This means that the library is refreshing and maybe adding/removing media.
         */
        RELOADING
    }
}
