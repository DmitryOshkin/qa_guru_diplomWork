package yandex.oshkin.tests.api;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import yandex.oshkin.tests.TestBase;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class APITests extends TestBase {

    public Map<String, String> getSessionCookies() {
        return step("Получаем cookies сессии", () -> {
            Map<String, String> sessionCookies =
                    given()
                            .log().all()
                            .when()
                            .get("")
                            .then()
                            .log().all()
                            .statusCode(200)
                            .extract()
                            .cookies();

            return sessionCookies;
        });
    }

    @Test
    @DisplayName("Добавление товара в корзину через api")
    public void addToOrderProduct() {

        Map<String, String> sessionCookies = getSessionCookies();

        String tUid = "_tuid=" + sessionCookies.get("_tuid") + ";";
        int amount = 4;

        given()
                .cookie(tUid)
                .formParam("amount", amount)
                .log().uri()
                .log().params()
                .log().cookies()
                .when()
                .get("basket/add/product/1442048/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/addtoorderproduct_schema.json"));
    }
}