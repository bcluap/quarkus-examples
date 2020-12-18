package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import static org.hamcrest.CoreMatchers.is;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ReactiveScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ReactiveScenariosTest.class);

    @Test
    public void testSubResorceBinding() {
        given()
                .get("sub-resource/abc")
                .then()
                .statusCode(200)
                .body(is("abc"));
    }

    @Test
    public void testListOfSomeClass() {
        given().contentType(ContentType.JSON)
                .body("[]")
                .post("/listofsomeclass")
                .then()
                .statusCode(200)
                .body(is("OK"));
    }

    @Test
    public void testBigDecimalPathParam() {
        given().get("/bigdecimal/1.1")
                .then()
                .statusCode(200)
                .body(is("1.1"));
    }

    @Test
    public void testPostingStringArray() {
        given().contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .body("[\"a\",\"b\"]")
                .post("/stringarray")
                .then()
                .statusCode(200)
                .body(is("[\"a\",\"b\"]"));
    }

    @Test
    public void testFilterAbortingWithByteArray() {
        given()
                .header("prematchresponse", "yes")
                .get("/dummypath")
                .then()
                .statusCode(200).contentType(ContentType.BINARY);
    }

}
