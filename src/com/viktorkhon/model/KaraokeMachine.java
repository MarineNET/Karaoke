package com.viktorkhon.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class KaraokeMachine {

    private SongBook mSongBook;
    private BufferedReader mReader;
    private Map<String, String> mMenu;

    public KaraokeMachine(SongBook songBook) {
        mSongBook = songBook;
        // InputStreamReader reads bytes and decodes them into a specified cahrset
        // For efficiency purposes we also wrap it into a BufferReader
        mReader = new BufferedReader(new InputStreamReader(System.in));
        // Create a UI menu using Map<Key, Value> interface
        mMenu = new HashMap<String, String>();
        mMenu.put("add", "Add song");
        mMenu.put("quit", "Exit");
    }

    private String promptAction() throws IOException {
        System.out.printf("There are %d songs available. Your options are: %n", mSongBook.getSongCount());
        // Go through the loop and extract Set<Map.Entry<K,V>>, then print individually a Key - Value pair
        for (Map.Entry<String, String> options : mMenu.entrySet()) {
            System.out.printf("%s - %s %n", options.getKey(), options.getValue());
        }
        System.out.print("What do you want to do: ");
        // Ask a user for an input based on the Menu choices
        String choice = mReader.readLine();
        // Trim and make all lower cases the answer and return it
        return choice.trim().toLowerCase();
    }

    public void run() {
        String choice = "";
        do{
            try {
                choice = promptAction();
                switch (choice) {
                    case "add":
                        Song song = promptNewSong();
                        mSongBook.addSong(song);
                        System.out.printf("%s added! %n%n", song);
                        break;
                    case "quit":
                        System.out.println("Thanks for playing");
                        break;
                    default:
                        System.out.printf("Unknown choice: '%s'. Try again %n%n", choice);
                }
            } catch (IOException e) {
                System.out.println("Problem with input");
                e.printStackTrace();
            }
        }
        // As long as 'choice' is not equal to "quit" - run the loop
        while (!choice.equals("quit"));
    }

    private Song promptNewSong() throws IOException {
        System.out.print("Enter artist's name: ");
        String artist = mReader.readLine();
        System.out.print("Enter the title: ");
        String title = mReader.readLine();
        System.out.print("Enter video URL: ");
        String videoUrl = mReader.readLine();
        return new Song(artist, title, videoUrl);
    }

}
