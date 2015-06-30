package com.todocodepathapp.eventbus.events;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class DeleteTodoItemEvent {

    private long mId;

    public DeleteTodoItemEvent(long id) {
        this.mId = id;
    }

    public long getId() {
        return mId;
    }

}
