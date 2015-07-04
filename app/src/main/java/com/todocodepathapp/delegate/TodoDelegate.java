package com.todocodepathapp.delegate;

import android.content.Context;
import android.util.Log;

import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.todocodepathapp.api.CatService;
import com.todocodepathapp.api.models.Response;
import com.todocodepathapp.db.orm.TodoORM;
import com.todocodepathapp.eventbus.events.DeleteTodoItemEvent;
import com.todocodepathapp.eventbus.events.LoadCatImages;
import com.todocodepathapp.eventbus.events.LoadedCatImages;
import com.todocodepathapp.eventbus.events.SendUpdatedTodoEvent;
import com.todocodepathapp.eventbus.events.GetTodoItemsEvent;
import com.todocodepathapp.eventbus.events.InsertTodoItemEvent;
import com.todocodepathapp.eventbus.events.NewTodoItemEvent;
import com.todocodepathapp.eventbus.events.TodoItemsEvent;
import com.todocodepathapp.eventbus.events.UpdateTodoItemEvent;
import com.todocodepathapp.models.TodoItem;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoDelegate {

    private static final String TAG = TodoDelegate.class.getSimpleName();
    private static final String FORMAT = "xml";
    private static final int PER_PAGE = 20;

    private Context mContext;
    private Bus mBus;
    private CatService sCatService;

    public TodoDelegate(Context context, Bus bus) {
        this.mContext = context;
        this.mBus = bus;
        this.sCatService = CatService.getClient();
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

    @Subscribe
    public void onLoadCatImages(LoadCatImages loadCatImages) {
        Callback<Response> callback = new Callback<Response>() {
            @Override
            public void success(Response response, retrofit.client.Response response2) {
                Log.d(TAG, "onLoadCatImages success");
                mBus.post(new LoadedCatImages(response.getData().getImages()));
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d(TAG, error.getMessage());
                Log.d(TAG, error.getResponse().toString());
            }
        };
        sCatService.getCatImages(FORMAT, PER_PAGE, callback);
    }

}
