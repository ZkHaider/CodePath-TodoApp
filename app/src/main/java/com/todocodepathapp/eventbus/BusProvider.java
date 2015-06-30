package com.todocodepathapp.eventbus;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by ZkHaider on 6/29/15.
 */

/***
 *  Our event bus used to manage asynchronous activity. Utilizing a singleton instance
 */
public final class BusProvider {

    private static final String TAG = BusProvider.class.getSimpleName();
    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {

    }

}
