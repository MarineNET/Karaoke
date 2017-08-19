import com.viktorkhon.model.KaraokeMachine;
import com.viktorkhon.model.Song;
import com.viktorkhon.model.SongBook;

public class Karaoke {

    public static void main(String[] args) {
        KaraokeMachine karaoke = new KaraokeMachine(new SongBook());
        karaoke.run();
    }
}
