package com.viktorkhon.model;

import java.util.ArrayList;
import java.util.List;

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
}
