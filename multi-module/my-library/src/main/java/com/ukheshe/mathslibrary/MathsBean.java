/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ukheshe.mathslibrary;

import io.quarkus.arc.Unremovable;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@Unremovable
public class MathsBean {

    public long add(long a, long b) {
        return a + b;
    }

}
