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
    public void test1() {
        // 30'th leaf on the 4'th branch of tree 10
        given()
                .get("/trees/10/branches/4/leaves/30")
                .then()
                .statusCode(200)
                .body(is("10.4.30"));
    }


}
