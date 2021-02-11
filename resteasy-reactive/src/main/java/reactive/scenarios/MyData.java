/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package reactive.scenarios;

import java.time.ZonedDateTime;

/**
 *
 * @author paul
 */
public class MyData {
    
    private ZonedDateTime dt = ZonedDateTime.now();

    public ZonedDateTime getDt() {
        return dt;
    }

    public void setDt(ZonedDateTime dt) {
        this.dt = dt;
    }
    
}
