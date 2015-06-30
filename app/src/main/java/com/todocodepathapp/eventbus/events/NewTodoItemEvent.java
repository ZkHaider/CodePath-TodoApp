package com.todocodepathapp.eventbus.events;

import com.todocodepathapp.models.TodoItem;

import java.util.List;

/**
 * Created by ZkHaider on 6/30/15.
 */
public class NewTodoItemEvent {

    private List<TodoItem> mTodoItems;

    public NewTodoItemEvent(List<TodoItem> todoItems) {
        this.mTodoItems = todoItems;
    }

    public List<TodoItem> getTodoItems() {
        return mTodoItems;
    }

}
