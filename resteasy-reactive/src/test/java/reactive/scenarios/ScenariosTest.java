package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.endsWith;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Test
    public void test1() {
        // 50'th cell of the 30'th leaf on the 4'th branch of tree 10
        given()
                .get("/tenants/1/wallets/2")
                .then()
                .statusCode(200)
                .body(is("1-2"));
    }
    
    @Test
    public void test2() {
        given()
                .get("/tenants/test?aaa=111&bbb=222")
                .then()
                .statusCode(200)
                .body(endsWith("/tenants/test?aaa=111&bbb=222"));
    }


}
