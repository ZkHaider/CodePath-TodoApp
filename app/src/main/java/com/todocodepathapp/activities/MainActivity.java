package com.todocodepathapp.activities;

import android.content.DialogInterface;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;
import com.todocodepathapp.R;
import com.todocodepathapp.eventbus.BusProvider;
import com.todocodepathapp.eventbus.events.GetTodoItemsEvent;
import com.todocodepathapp.eventbus.events.InsertTodoItemEvent;
import com.todocodepathapp.eventbus.events.NewTodoItemEvent;
import com.todocodepathapp.eventbus.events.RemoveFragment;
import com.todocodepathapp.eventbus.events.SendUpdatedTodoEvent;
import com.todocodepathapp.eventbus.events.TodoItemsEvent;
import com.todocodepathapp.fragments.EmptyContentFragment;
import com.todocodepathapp.fragments.TodoListFragment;
import com.todocodepathapp.models.TodoItem;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private Bus mBus = BusProvider.getInstance();
    private Resources mResources;
    private CoordinatorLayout mCoordinatorLayout;
    private FloatingActionsMenu mFloatingActionMenu;
    private FloatingActionButton mCreateFabFAB;

    private EmptyContentFragment mEmptyContentFragment;
    private TodoListFragment mTodoListFragment;

    /***
     *  LifeCycle Methods
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        initFABListener();
    }

    @Override
    public void onResume() {
        super.onResume();
        mBus.register(this);
        mBus.post(new GetTodoItemsEvent());
    }

    @Override
    public void onPause() {
        super.onPause();
        mBus.unregister(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /***
     * Initialization Methods
     */

    private void initViews() {
        mResources = getResources();
        mCoordinatorLayout = (CoordinatorLayout) findViewById(R.id.coordinatorLayout);
        mFloatingActionMenu = (FloatingActionsMenu) findViewById(R.id.todoFAB);
        mCreateFabFAB = (FloatingActionButton) findViewById(R.id.newTodoFAB);
    }

    private void initFABListener() {
        mCreateFabFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newTodoDialog();
            }
        });
    }

    private void initTodoListFragment(List<TodoItem> todoItems) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        if (todoItems != null) {
            replaceMainContent(todoItems, ft);
        }
    }

    /***
     *  Otto Subscription Methods
     */

    @Subscribe
    public void onTodoItemsEvent(TodoItemsEvent todoItemsEvent) {
        List<TodoItem> todoItems = todoItemsEvent.getTodoItems();
        initTodoListFragment(todoItems);
    }

    @Subscribe
    public void onNewTodoItemEvent(NewTodoItemEvent newTodoItemEvent) {
        mTodoListFragment = TodoListFragment.getTodoListFragment();
        if (mTodoListFragment.getTodoItemAdapter() == null) {
            initTodoListFragment(newTodoItemEvent.getTodoItems());
        } else {
            if (mTodoListFragment.getTodoItemAdapter().getItemCount() > 0)
                mTodoListFragment.addTodoItems(newTodoItemEvent.getTodoItems());
            else
                initTodoListFragment(newTodoItemEvent.getTodoItems());
        }

    }

    @Subscribe
    public void onSendUpdatedTodoEvent(SendUpdatedTodoEvent sendUpdatedTodoEvent) {
        if (TodoListFragment.getTodoListFragment() != null)
            TodoListFragment.getTodoListFragment().setUpdatedTodos(sendUpdatedTodoEvent.getTodoItems());
    }

    @Subscribe
    public void onRemoveFragment(RemoveFragment removeFragment) {
        replaceWithEmptyContentFragment(getSupportFragmentManager().beginTransaction());
    }

    /***
     *  Helper Methods
     */

    public void newTodoDialog() {
        LayoutInflater layoutInflater = MainActivity.this.getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_new_todo, null);
        final EditText titleEditText = (EditText) v.findViewById(R.id.titleEditText);
        final EditText bodyEditText = (EditText) v.findViewById(R.id.bodyEditText);

        new AlertDialog.Builder(this)
                .setTitle(mResources.getString(R.string.todo_add_newItem))
                .setView(v)
                .setPositiveButton(mResources.getString(R.string.todo_add),
                        new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        insertTodoItem(titleEditText, bodyEditText);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
        mFloatingActionMenu.collapse();
    }

    private void insertTodoItem(EditText titleEditText, EditText bodyEditText) {
        if (!titleEditText.getText().toString().isEmpty() &&
                !bodyEditText.getText().toString().isEmpty()) {
            TodoItem todoItem = getTodoItem(titleEditText, bodyEditText);
            mBus.post(new InsertTodoItemEvent(todoItem));
        } else {
            Snackbar.make(mCoordinatorLayout,
                    mResources.getString(R.string.todo_empty),
                    Snackbar.LENGTH_SHORT)
                    .show();
        }
    }

    private TodoItem getTodoItem(EditText titleEditText, EditText bodyEditText) {
        TodoItem todoItem = new TodoItem();
        todoItem.setTitle(titleEditText.getText().toString().trim());
        todoItem.setBody(bodyEditText.getText().toString().trim());
        todoItem.setCreatedAt(new Date());
        return todoItem;
    }

    private void replaceMainContent(List<TodoItem> todoItems, FragmentTransaction ft) {
        if (!todoItems.isEmpty()) {
            replaceWithTodoListFragment(todoItems, ft);
        } else {
            replaceWithEmptyContentFragment(ft);
        }
    }

    private void replaceWithTodoListFragment(List<TodoItem> todoItems, FragmentTransaction ft) {
        mTodoListFragment = TodoListFragment.getTodoListFragment();
        mTodoListFragment.setTodoItems(todoItems);
        ft.replace(R.id.todoContent, mTodoListFragment);
        ft.commit();
    }

    private void replaceWithEmptyContentFragment(FragmentTransaction ft) {
        mEmptyContentFragment = EmptyContentFragment.getInstance();
        ft.replace(R.id.todoContent, mEmptyContentFragment);
        ft.commit();
    }

}
