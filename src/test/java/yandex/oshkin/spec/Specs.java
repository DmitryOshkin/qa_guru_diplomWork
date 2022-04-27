package yandex.oshkin.spec;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import yandex.oshkin.tests.commonsteps.CommonStepsAPI;

import java.util.Map;

import static io.restassured.RestAssured.with;

public class Specs {

    static Map<String, String> sessionCookies = CommonStepsAPI.getSessionCookies();
    static String tUid = "_tuid=" + sessionCookies.get("_tuid") + ";";

    public static RequestSpecification request = with()
            .cookie(tUid)
            .header("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/100.0.4896.127 Safari/537.36" )
            .log().uri()
            .log().cookies();

    public static ResponseSpecification responseSpec200 = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(LogDetail.ALL)
            .build();
}
