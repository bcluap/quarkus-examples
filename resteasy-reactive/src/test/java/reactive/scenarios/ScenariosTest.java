package reactive.scenarios;


import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Test
    public void test1() {
        given()
                .get("/doesnotwork")
                .then()
                .statusCode(200);
        given()
                .get("/doeswork")
                .then()
                .statusCode(200);
    }

}
