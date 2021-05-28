package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Test
    public void test1() {
        given()
                .contentType(ContentType.TEXT)
                .body("Hello")
                .post("/test/xxx")
                .then()
                .statusCode(201)
                .extract().asString().equals("OK");

    }

}
