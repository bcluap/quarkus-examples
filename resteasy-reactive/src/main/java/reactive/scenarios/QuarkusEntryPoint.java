/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import io.quarkus.runtime.Quarkus;
import io.quarkus.runtime.QuarkusApplication;
import io.quarkus.runtime.Startup;
import io.quarkus.runtime.annotations.QuarkusMain;
import javax.inject.Singleton;

/**
 *
 * @author paul
 */
@QuarkusMain
@Singleton
@Startup(0)
public class QuarkusEntryPoint implements QuarkusApplication {

    private static boolean logged = false;


    @Override
    public int run(String... args) throws Exception {
        Quarkus.waitForExit();
        return 0;
    }

}
