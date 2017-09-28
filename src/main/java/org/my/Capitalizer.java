package org.my;

import co.paralleluniverse.fibers.Suspendable;

public class Capitalizer {

    @Suspendable
    public String capitalize(String s) {
        return s != null ? s.toUpperCase() : null;
    }

}
