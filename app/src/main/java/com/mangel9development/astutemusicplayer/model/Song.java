package com.mangel9development.astutemusicplayer.model;

//import android.support.v4.media.MediaBrowserCompat;

// TODO: find the class that this class should extend

import androidx.annotation.NonNull;

import java.util.Objects;

public class Song /*extends MediaBrowserCompat.MediaItem*/ implements Playable{
    private final String title;
    private final Artist artist;
//    private final Artist albumArtist;
    private final Album album;
    private final Genre genre;
//    private final int trackNumber;

    public Song(String title, Artist artist, Album album, Genre genre){
        super();
        // TODO make artist a String. the Album artist (property of album) should still be an Artist
        this.title=title;
        this.artist=artist;
        this.album=album;
        this.genre=genre;

        Library.addSong(this); // this might not be necessary as most, if not all, methods that
            // create a new Song would already have added it to the Library
    }

    public Song(String title, String artist, String album, String genre){
        super();
        this.title=title;

        this.artist=Library.artists.get(artist);
        this.album=Library.albums.get(album,this.artist);
        this.genre=Library.genres.get(genre);

        this.artist.add(this);
        this.album.add(this);
        this.genre.add(this);
    }

    /**
     * @return the song's title
     */
    public String getTitle(){
        return title;
    }

    /**
     * @return the song's artist
     */
    public Artist getArtist(){
        return artist;
    }

    /**
     * @return the song's album
     */
    public Album getAlbum(){
        return album;
    }

    /**
     * @return the song's genre
     */
    public Genre getGenre(){
        return genre;
    }

    public boolean equals(@NonNull Song song){
        return (
            this.title.equals(song.title) &&
            this.artist.equals(song.artist) &&
            this.album.equals(song.album)
        );
        //TODO: later on, this can be changed to return true if the files are the same
    }

    @NonNull
    @Override
    public String toString(){
        return "\""+title+"\" by "+artist;
    }

//    @NonNull
//    @Override
//    public String getPlayerName(){
//        return title;
//    }
}
