package VideoChallenge;

import java.util.HashMap;
import java.util.Map;

public class QuickFix {

    public void addForgottenVideo(Course course) {
        // TODO(1):  Create a new video called "The Beginning Bits"
        Video video = new Video("The Beginning Bits");

        // TODO(2):  Add the newly created video to the course videos as the second video.
        course.getVideos().add(1, video);
    }

    public void fixVideoTitle(Course course, String oldTitle, String newTitle) {
        Video video = videosByTitle(course).get(oldTitle);
        video.setTitle(newTitle);
    }

    public Map<String, Video> videosByTitle(Course course) {
        Map<String, Video> byTitle = new HashMap<>();
        for (Video video : course.getVideos()) {
            byTitle.put(video.getTitle(), video);
        }
        return byTitle;
    }
}