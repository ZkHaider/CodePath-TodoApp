package com.todocodepathapp.eventbus.events;

import com.todocodepathapp.models.TodoItem;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class UpdateTodoItemEvent {

    private TodoItem mTodoItem;

    public UpdateTodoItemEvent(TodoItem todoItem) {
        this.mTodoItem = todoItem;
    }

    public TodoItem getTodoItem() {
        return mTodoItem;
    }

}
