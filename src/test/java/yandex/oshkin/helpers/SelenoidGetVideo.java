package yandex.oshkin.helpers;

import yandex.oshkin.config.selenoid.Selenoid;

import java.net.MalformedURLException;
import java.net.URL;

public class SelenoidGetVideo {
    public static URL selenoidVideoUrl(String sessionId) {
        String videoUrl = Selenoid.config.videoAttachUrl() + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
