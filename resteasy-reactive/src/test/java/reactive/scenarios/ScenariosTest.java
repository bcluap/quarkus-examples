package reactive.scenarios;

import static io.restassured.RestAssured.given;

import io.quarkus.test.junit.QuarkusTest;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@QuarkusTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ScenariosTest {

    private static final Logger log = LoggerFactory.getLogger(ScenariosTest.class);

    @Inject
    EntityManager em;

    @Test
    @Transactional
    @Order(1000)
    public void init() {
        em.createNativeQuery("""
                             CREATE TABLE IF NOT EXISTS `demo_table` (
                               `name` varchar(200) NOT NULL,
                               PRIMARY KEY (`name`)
                             ) ENGINE=InnoDB;""").executeUpdate();
    }

    @Test
    @Order(1100)
    public void testWorks() throws InterruptedException {
        CountDownLatch l = new CountDownLatch(400);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        given()
                                .get("/test/works")
                                .then()
                                .statusCode(200)
                                .extract().asString();
                    } finally {
                        l.countDown();
                    }
                }
            });
            t.start();
        }
        l.await(30, TimeUnit.SECONDS);
    }

    @Test
    @Order(1200)
    public void testFails() throws InterruptedException {
        CountDownLatch l = new CountDownLatch(400);
        for (int i = 0; i < 20; i++) {
            Thread t = new Thread(() -> {
                for (int j = 0; j < 20; j++) {
                    try {
                        given()
                                .get("/test/fails")
                                .then()
                                .statusCode(200)
                                .extract().asString();
                    } finally {
                        l.countDown();
                    }
                }
            });
            t.start();
        }
        l.await(30, TimeUnit.SECONDS);
    }

}
