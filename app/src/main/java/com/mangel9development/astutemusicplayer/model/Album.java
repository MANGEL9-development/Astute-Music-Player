package com.mangel9development.astutemusicplayer.model;

import com.mangel9development.astutemusicplayer.model.Aggregation.Aggregant;

public class Album extends SongCollection implements Aggregant{
    private final String name;
    private final Artist albumArtist;
    private final int year;
    private static final int DEFAULT_YEAR=-1; // this is assigned to an album that doesn't have a specified year

    public Album(String name, Artist albumArtist){
        // new Album(name,albumArtist,DEFAULT_YEAR);
            // this will cause an error saying that the fields might not have been initialized
        super();
        this.name=name;
        this.albumArtist=albumArtist;
        this.year=DEFAULT_YEAR;

        this.albumArtist.addAlbum(this);
    }

    /**
     * Constructs an Album with the specified name, album artist, and year.
     * @param name the name of the album
     * @param albumArtist the artist who owns the album
     * @param year the year in which the album was released
     */
    public Album(String name, Artist albumArtist, int year){
        super();
        this.name=name;
        this.albumArtist=albumArtist;
        this.year=year;

        this.albumArtist.addAlbum(this);
    }

    /**
     * @return the name of the album
     */
    @Override
    public String getName(){
        return name;
    }

    /**
     * @return the artist who owns the album
     */
    public Artist getAlbumArtist(){
        return albumArtist;
    }

    /**
     * @return the year in which the album was released
     */
    public int getYear(){
        return year;
    }
}
