package yandex.oshkin.helpers;

import java.net.MalformedURLException;
import java.net.URL;

import static io.restassured.RestAssured.given;

public class VideoUrl {
    public static String browserstackGetVideoUrl(String sessionId) {

        String url = yandex.oshkin.config.browserstack.Browserstack.config.browserstackVideoAttachURL() + sessionId + ".json";
        String login = yandex.oshkin.config.browserstack.Browserstack.config.browserstackUser();
        String password = yandex.oshkin.config.browserstack.Browserstack.config.browserstackKey();

        return given()
                .auth().basic(login, password)
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }

    public static URL selenoidGetVideoUrl(String sessionId) {
        String videoUrl = yandex.oshkin.config.selenoid.Selenoid.config.videoAttachUrl() + sessionId + ".mp4";

        try {
            return new URL(videoUrl);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
