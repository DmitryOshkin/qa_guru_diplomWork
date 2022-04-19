package yandex.oshkin.tests.commonsteps;

import io.qameta.allure.Step;
import yandex.oshkin.tests.TestBase;

import java.util.Map;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.*;

public class CommonStepsAPI extends TestBase {

    int changeAmount = 0;
    int basketCount = 0;

    @Step("Получаем cookies сессии")
    public Map<String, String> getSessionCookies() {
        return
                step("Получаем cookies сессии", () -> {
                    Map<String, String> sessionCookies =
                            given()
                                    .log().all()
                                    .when()
                                    .get("")
                                    .then()
                                    .log().cookies()
                                    .statusCode(200)
                                    .extract()
                                    .cookies();

                    return sessionCookies;
                });
    }

    Map<String, String> sessionCookies = getSessionCookies();
    String tUid = "_tuid=" + sessionCookies.get("_tuid") + ";";

    @Step("Добавляем товар в корзину")
    public CommonStepsAPI addToOrder(String productCode, int count) {
        basketCount = basketCount + count;

        given()
                .cookie(tUid)
                .formParam("amount", count)
                .log().uri()
                .log().params()
                .log().cookies()
                .when()
                .get("basket/add/product/" + productCode + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/addtoorderproduct_schema.json"))
                .body("storage.cart.list." + productCode + ".id", is(productCode))
                .body("storage.cart.list." + productCode + ".amount", is(count))
                .body("storage.cart.basketCount", is(basketCount));
        return this;
    }

    @Step("Изменяем количество товара в корзине")
    public CommonStepsAPI changeProductCountToOrder(String productCode, int count) {
        changeAmount = changeAmount + count;

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("basket/change/amount/" + productCode + "/" + changeAmount + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/addtoorderproduct_schema.json"))
                .body("storage.cart.list." + productCode + ".id", is(productCode))
                .body("storage.cart.list." + productCode + ".amount", is(changeAmount));
        return this;
    }

    @Step("Удаляем товар из корзины")
    public CommonStepsAPI delProductOrder(String productCode) {

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("basket/remove/" + productCode + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/delorderproduct_schema.json"))
                .body("storage.cart.list", not(productCode))
                .body("storage.cart.basketCount", is(0));
        return this;
    }

    @Step("Очищаем корзину")
    public CommonStepsAPI clearOrder() {

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("basket/clear/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/cleanorder_schema.json"))
                .body("storage.cart.list", is(empty()))
                .body("storage.cart.basketCount", is(0));
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public CommonStepsAPI addToCompare(String productCode, int category) {

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("compare/add/product/" + productCode + "/" + category + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/addtocompareproduct_schema.json"))
                .body("storage.compare.categoryList." + category + ".list." + productCode + ".id", is(productCode));
        return this;
    }

    @Step("Удаляем товар из списка сравнения")
    public CommonStepsAPI delProductCompare(String productCode, int category) {

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("compare/remove/product/" + productCode + "/" + category + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/delcompareproduct_schema.json"))
                .body("storage.compare.categoryList." + category + ".list.", not(productCode));
        return this;
    }

    @Step("Очищаем список сравнения")
    public CommonStepsAPI cleanProductCompare(int category) {

        given()
                .cookie(tUid)
                .log().uri()
                .log().cookies()
                .when()
                .get("compare/remove/category/" + category + "/")
                .then()
                .log().all()
                .statusCode(200)
                .body(matchesJsonSchemaInClasspath("schemas/cleancompare_schema.json"))
                .body("storage.compare.categoryList." + category + ".list.", not(category))
                .body("storage.compare.count", is(0));
        return this;
    }
}
