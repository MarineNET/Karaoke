import com.viktorkhon.model.KaraokeMachine;
import com.viktorkhon.model.Song;
import com.viktorkhon.model.SongBook;

public class Karaoke {

    public static void main(String[] args) {
        SongBook songBook = new SongBook();
        songBook.importFrom("songs.txt");
        KaraokeMachine karaoke = new KaraokeMachine(songBook);
        karaoke.run();
        System.out.println("Saving book...");
        songBook.exportTo("songs.txt");
    }
}
