package yandex.oshkin.tests.commonsteps;

import io.qameta.allure.Step;
import org.assertj.core.api.Assertions;
import yandex.oshkin.models.Basket.clearBasket.Basket;

import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static yandex.oshkin.spec.Specs.request;
import static yandex.oshkin.spec.Specs.responseSpec200;

public class CommonStepsAPI extends Basket {

    @Step("Получаем cookies сессии")
    public static Map<String, String> getSessionCookies() {

        Map<String, String> sessionCookies =
                given()
                        .log().all()
                        .when()
                        .get("/")
                        .then()
                        .log().cookies()
                        .statusCode(200)
                        .extract()
                        .cookies();
        return sessionCookies;
    }

    @Step("Добавляем товар в корзину")
    public CommonStepsAPI addToOrder(String productCode, int count) {
//        int basketCount = 0;
//        basketCount = basketCount + count;

        given()
                .spec(request)
                .formParam("amount", count)
                .log().params()
                .when()
                .get("/basket/add/product/" + productCode + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/addtoorderproduct_schema.json"))
                .body("storage.cart.list." + productCode + ".id", is(productCode))
                .body("storage.cart.list." + productCode + ".amount", is(count));
        //  .body("storage.cart.basketCount", is(basketCount));
        return this;
    }

    @Step("Изменяем количество товара в корзине")
    public CommonStepsAPI changeProductCountToOrder(String productCode, int count) {
        int changeAmount = 0;
        changeAmount = changeAmount + count;

        given()
                .spec(request)
                .when()
                .get("/basket/change/amount/" + productCode + "/" + changeAmount + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/addtoorderproduct_schema.json"))
                .body("storage.cart.list." + productCode + ".id", is(productCode))
                .body("storage.cart.list." + productCode + ".amount", is(changeAmount));
        return this;
    }

    @Step("Удаляем товар из корзины")
    public CommonStepsAPI delProductOrder(String productCode) {
        given()
                .spec(request)
                .when()
                .get("/basket/remove/" + productCode + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/delorderproduct_schema.json"))
                .body("storage.cart.list", not(productCode))
                .body("storage.cart.basketCount", is(0));
        return this;
    }

    @Step("Очищаем корзину")
    public CommonStepsAPI clearOrder() {
        Basket basketClean = null;
        basketClean =
                given()
                        .spec(request)
                        .when()
                        .get("/basket/clear/")
                        .then()
                        .spec(responseSpec200)
                        .body(matchesJsonSchemaInClasspath("schemas/cleanorder_schema.json"))
                        .extract().body().as(Basket.class);

        Assertions.assertThat(basketClean.getStorage().getCart().getList().isEmpty());
        Assertions.assertThat(basketClean.getStorage().getCart().getBasketCount()).isEqualTo(0);
        return this;
    }

    @Step("Добавляем товар в список сравнения")
    public CommonStepsAPI addToCompare(String productCode, int category) {
        given()
                .spec(request)
                .when()
                .get("/compare/add/product/" + productCode + "/" + category + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/addtocompareproduct_schema.json"))
                .body("storage.compare.categoryList." + category + ".list." + productCode + ".id", is(productCode));
        return this;
    }

    @Step("Удаляем товар из списка сравнения")
    public CommonStepsAPI delProductCompare(String productCode, int category) {
        given()
                .spec(request)
                .when()
                .get("/compare/remove/product/" + productCode + "/" + category + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/delcompareproduct_schema.json"))
                .body("storage.compare.categoryList." + category + ".list.", not(productCode));
        return this;
    }

    @Step("Очищаем список сравнения")
    public CommonStepsAPI cleanProductCompare(int category) {
        given()
                .spec(request)
                .when()
                .get("/compare/remove/category/" + category + "/")
                .then()
                .spec(responseSpec200)
                .body(matchesJsonSchemaInClasspath("schemas/cleancompare_schema.json"))
                .body("storage.compare.categoryList." + category + ".list.", not(category))
                .body("storage.compare.count", is(0));
        return this;
    }
}
