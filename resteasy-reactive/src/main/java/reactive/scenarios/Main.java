package reactive.scenarios;

import io.quarkus.runtime.Quarkus;

/**
 *
 * @author paul
 */
public class Main {

    public static void main(String... args) {
        Quarkus.run(QuarkusEntryPoint.class, args);
    }

}
