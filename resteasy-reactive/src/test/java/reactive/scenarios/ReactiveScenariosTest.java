package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.smallrye.common.annotation.Blocking;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ReactiveScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ReactiveScenariosTest.class);

    @Test
    public void testListOfSomeClass() {
        given().contentType(ContentType.TEXT)
                .body("HELLO")
                .post("/test")
                .then()
                .statusCode(200)
                .body(is("HELLO"));
    }


}
