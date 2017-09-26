package com.example.lanternaverde.retrofit;

import com.squareup.otto.Bus;

/**
 * Created by Lanterna Verde on 07/08/2017.
 */

public class BusProvider {
    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}
