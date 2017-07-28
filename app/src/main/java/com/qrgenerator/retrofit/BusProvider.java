package com.qrgenerator.retrofit;

/**
 * Created by inmkhan021 on 7/24/2017.
 */

import com.squareup.otto.Bus;

public class BusProvider {

    private static final Bus BUS = new Bus();

    public static Bus getInstance(){
        return BUS;
    }

    public BusProvider(){}
}

