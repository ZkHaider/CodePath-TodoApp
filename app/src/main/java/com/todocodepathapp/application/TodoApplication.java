package com.todocodepathapp.application;

import android.app.Application;

import com.squareup.otto.Bus;
import com.todocodepathapp.delegate.TodoDelegate;
import com.todocodepathapp.eventbus.BusProvider;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoApplication extends Application {

    private Bus mBus = BusProvider.getInstance();
    private TodoDelegate mTodoDelegate;

    @Override
    public void onCreate() {
        super.onCreate();
        mTodoDelegate = new TodoDelegate(this, mBus);
        mBus.register(mTodoDelegate);
        mBus.register(this);
    }
}
