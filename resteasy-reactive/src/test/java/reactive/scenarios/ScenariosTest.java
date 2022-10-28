package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Test
    public void test1() {
        String test = "https://aaa.bbb";
            given()
                    .get("/test/" + test)
                    .then()
                    .statusCode(200)
                    .extract().asString().equals(test);

    }

}
