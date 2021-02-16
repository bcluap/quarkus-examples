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
        given()
                .get("/sr/1/2/3")
                .then()
                .statusCode(200)
                .body(is("1.2.3"));
    }

    @Test
    public void test2() {
        given()
                .get("/sr/1/l2/2")
                .then()
                .statusCode(200)
                .body(is("1.2"));
    }

    @Test
    public void test3() {
        given()
                .get("/sr/1/2/l3/3")
                .then()
                .statusCode(200)
                .body(is("1.2.3"));

    }

}
