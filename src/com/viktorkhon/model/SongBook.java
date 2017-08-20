package com.viktorkhon.model;

import java.io.*;
import java.util.*;

/**
 * Created by Viktor Khon on 8/19/2017.
 */
public class SongBook {
    private List<Song> mSongs;

    public SongBook() {
        mSongs = new ArrayList<Song>();
    }

    // Method to save each song to a file
    public void exportTo(String fileName) {
        try (
                // FileOutputStream - A file output stream is an output stream for writing data to a File or to a FileDescriptor
                FileOutputStream fos = new FileOutputStream(fileName);
                // PrintWriter - Prints formatted representations of objects to a text-output stream
                PrintWriter writer = new PrintWriter(fos);
                ) {
            for (Song song : mSongs) {
                writer.printf("%s|%s|%s%n",
                        song.getmArtist(),
                        song.getmTitle(),
                        song.getmVideoUrl());
            }
        }  catch (IOException e) {
            System.out.printf("problem saving %s %n", fileName);
            e.printStackTrace();
        }
    }

    public void importFrom(String fileName) {
        try (
                FileInputStream fis = new FileInputStream(fileName);
                BufferedReader reader = new BufferedReader(new InputStreamReader(fis));
                ) {
            String line;
            // Note the extra slash before 'line' to encapsulate the expression and then use it to get a boolean
            while ((line = reader.readLine()) != null) {
                // Split the string into an array of 3 Strings by separating the "\"
                String [] args = line.split("\\|");
                // Create a new Song object by getting an appropriate value from each array index
                addSong(new Song(args[0], args[1], args[2]));
            }
        }  catch (IOException e) {
            System.out.printf("problem loading %s %n", fileName);
            e.printStackTrace();
        }
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
        Map<String, List<Song>> byArtist = new TreeMap<>();
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
        List<Song> songs = byArtist().get(artistName);
        // Use Anonymous Class to sort the List
        songs.sort(new Comparator<Song>() {
            @Override
            public int compare(Song song1, Song song2) {
                if (song1.equals(song2)) {
                    return 0;
                }
                return song1.getmTitle().compareTo(song2.getmTitle());
            }
        });
        return songs;
    }
}
