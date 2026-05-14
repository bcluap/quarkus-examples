package reactive.scenarios;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicBoolean;

import static io.restassured.RestAssured.given;
import static org.hamcrest.MatcherAssert.assertThat;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);


    @Test
    @Order(2)
    public void testIsolation() {
        AtomicBoolean failed = new AtomicBoolean(false);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                String res = given()
                        .get("/X")
                        .then()
                        .statusCode(200).extract().asString();
                if (!res.equals("2")) {
                    failed.set(true);
                }
            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
        }
        assertThat(failed.get(), org.hamcrest.Matchers.is(false));
    }


}
