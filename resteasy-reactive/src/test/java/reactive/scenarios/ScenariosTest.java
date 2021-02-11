package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Test
    public void testDt() {
        
        given()
                .get("/dt")
                .then()
                .statusCode(200)
                .body(is("{\"dt\":\"aaa\"}"));
    }


}
