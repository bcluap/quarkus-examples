package test;


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
                .get("/get1")
                .then()
                .statusCode(200)
                .extract().asString().equals("3");
    }

    @Test
    public void test2() {
        given()
                .get("/get2")
                .then()
                .statusCode(200)
                .extract().asString().equals("3");
        

    }
}
