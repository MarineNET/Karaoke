package com.viktorkhon.model;

import java.util.*;

/**
 * Created by Viktor Khon on 8/19/2017.
 */
public class SongBook {
    private List<Song> mSongs;

    public SongBook() {
        mSongs = new ArrayList<Song>();
    }

    public void addSong(Song song) {
        mSongs.add(song);
    }

    public int getSongCount() {
        return mSongs.size();
    }

    //FIX ME - This should be cached
    // Create a Map list of Artist - Song
    private Map<String, List<Song>> byArtist() {
        Map<String, List<Song>> byArtist = new HashMap<>();
        // for each of the songs that is currently in the list
        for (Song song : mSongs) {
            // See if the song has already been defined, and if it has get a List back
            // Check each song by artist name, and if present then get that Song object as a List<Song>
            List<Song> artistSong = byArtist.get(song.getmArtist());
            // if song has not been defined, create a new one
            if (artistSong == null) {
                artistSong = new ArrayList<>();
                // Associates the specified value with the specified key in this map.
                // Returns the previous value associated with this key or null
                byArtist.put(song.getmArtist(), artistSong);
            }
            // We now definitely has an artistSong, either it was there or we created a brand new one, so we can add
            // a Song object to the List
            artistSong.add(song);
        }
        return byArtist;
    }

    // Get an Artist name, which is a Key in byArtist() Map.
    public Set<String> getArtists () {
        return byArtist().keySet();
    }

    public List<Song> getSongsForArtist(String artistName) {
        return byArtist().get(artistName);
    }


}
