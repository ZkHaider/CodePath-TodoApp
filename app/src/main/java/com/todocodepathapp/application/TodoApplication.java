package com.todocodepathapp.application;

import android.app.ActivityManager;
import android.app.Application;
import android.widget.Toast;

import com.squareup.otto.Bus;
import com.todocodepathapp.delegate.TodoDelegate;
import com.todocodepathapp.eventbus.BusProvider;

import java.util.List;

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
