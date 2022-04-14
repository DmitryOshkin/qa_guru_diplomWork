package yandex.oshkin.helpers;

import yandex.oshkin.config.browserstack.Browserstack;

import static io.restassured.RestAssured.given;
import static java.lang.String.format;

public class BrowserstackGetVideo {
    public static String videoUrl(String sessionId) {

        String url = format("https://api-cloud.browserstack.com/app-automate/sessions/%s.json", sessionId);

        return given()
                .auth().basic(Browserstack.config.browserstackUser(), Browserstack.config.browserstackKey())
                .log().all()
                .when()
                .get(url)
                .then()
                .log().all()
                .statusCode(200)
                .extract()
                .path("automation_session.video_url");
    }
}
