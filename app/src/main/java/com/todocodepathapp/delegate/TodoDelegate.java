package com.todocodepathapp.delegate;

import android.content.Context;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.todocodepathapp.db.orm.TodoORM;
import com.todocodepathapp.eventbus.events.DeleteTodoItemEvent;
import com.todocodepathapp.eventbus.events.SendUpdatedTodoEvent;
import com.todocodepathapp.eventbus.events.GetTodoItemsEvent;
import com.todocodepathapp.eventbus.events.InsertTodoItemEvent;
import com.todocodepathapp.eventbus.events.NewTodoItemEvent;
import com.todocodepathapp.eventbus.events.TodoItemsEvent;
import com.todocodepathapp.eventbus.events.UpdateTodoItemEvent;
import com.todocodepathapp.models.TodoItem;

import java.util.List;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoDelegate {

    private Context mContext;
    private Bus mBus;

    public TodoDelegate(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
    }

    @Subscribe
    public void onGetTodoItemsEvent(GetTodoItemsEvent getTodoItemsEvent) {
        List<TodoItem> todoItems = TodoORM.getTodoItems(mContext);
        mBus.post(new TodoItemsEvent(todoItems));
    }

    @Subscribe
    public void onInsertTodoItemEvent(InsertTodoItemEvent insertTodoItemEvent) {
        TodoItem todoItem = insertTodoItemEvent.getTodoItem();
        TodoORM.insertTodo(mContext, todoItem);
        mBus.post(new NewTodoItemEvent(TodoORM.getTodoItems(mContext)));
    }

    @Subscribe
    public void onUpdateTodoItemEvent(UpdateTodoItemEvent updateTodoItemEvent) {
        TodoItem todoItem = updateTodoItemEvent.getTodoItem();
        List<TodoItem> updatedTodos = TodoORM.updateTodoItem(mContext, todoItem);
        mBus.post(new SendUpdatedTodoEvent(updatedTodos));
    }

    @Subscribe
    public void onDeleteTodoItemEvent(DeleteTodoItemEvent deleteTodoItemEvent) {
        TodoORM.deleteTodo(mContext, deleteTodoItemEvent.getId());
    }

}
