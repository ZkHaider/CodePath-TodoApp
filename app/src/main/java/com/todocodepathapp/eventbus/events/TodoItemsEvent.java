package com.todocodepathapp.eventbus.events;

import com.todocodepathapp.models.TodoItem;

import java.util.List;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoItemsEvent {

    private List<TodoItem> mTodoItems;

    public TodoItemsEvent(List<TodoItem> todoItems) {
        this.mTodoItems = todoItems;
    }

    public List<TodoItem> getTodoItems() {
        return mTodoItems;
    }

}
