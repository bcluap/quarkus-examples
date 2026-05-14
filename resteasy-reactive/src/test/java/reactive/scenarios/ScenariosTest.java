package reactive.scenarios;

import io.quarkus.test.junit.QuarkusTest;
import static io.restassured.RestAssured.given;

import io.smallrye.common.annotation.RunOnVirtualThread;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);



    @Test
    @Order(2)
    public void testIsolation() {
        for (int i=0; i<10; i++) {
            new Thread(() -> {
                given()
                        .get("/X")
                        .then()
                        .statusCode(200)
                        .body(org.hamcrest.Matchers.is("2"));
            }).start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
    }


}
