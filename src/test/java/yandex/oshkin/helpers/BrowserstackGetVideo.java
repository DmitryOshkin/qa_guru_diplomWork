package yandex.oshkin.helpers;

import yandex.oshkin.config.browserstack.Browserstack;

import static io.restassured.RestAssured.given;

public class BrowserstackGetVideo {
    public static String browserstackVideoUrl(String sessionId) {

        String url = Browserstack.config.browserstackVideoAttachURL() + sessionId + ".json";
        String login = Browserstack.config.browserstackUser();
        String password = Browserstack.config.browserstackKey();

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
}
