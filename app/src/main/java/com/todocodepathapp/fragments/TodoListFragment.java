package com.todocodepathapp.fragments;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.todocodepathapp.R;
import com.todocodepathapp.adapters.TodoItemAdapter;
import com.todocodepathapp.eventbus.BusProvider;
import com.todocodepathapp.eventbus.events.DeleteTodoItemEvent;
import com.todocodepathapp.eventbus.events.RemoveFragment;
import com.todocodepathapp.eventbus.events.UpdateTodoItemEvent;
import com.todocodepathapp.models.TodoItem;

import java.util.List;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoListFragment extends Fragment {

    private static final String TAG = TodoListFragment.class.getSimpleName();

    public static TodoListFragment mTodoListFragment;

    private RecyclerView mTodoListRecyclerView;
    private TodoItemAdapter mTodoItemAdapter;
    private List<TodoItem> mTodoItems;

    public static TodoListFragment getTodoListFragment() {
        if (mTodoListFragment == null) {
            mTodoListFragment = new TodoListFragment();
        }
        return mTodoListFragment;
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        this.mTodoItems = todoItems;
    }

    public void addTodoItems(List<TodoItem> newTodoItems) {
        mTodoItemAdapter.animateTo(newTodoItems);
    }

    public void setUpdatedTodos(List<TodoItem> updatedTodos) {
        mTodoItemAdapter.animateTo(updatedTodos);
    }

    public List<TodoItem> getTodoItems() {
        return mTodoItems;
    }

    public TodoItemAdapter getTodoItemAdapter() {
        return mTodoItemAdapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_todo_list, null);

        initFragmentViews(root);

        ItemTouchHelper.Callback callback = initItemTouchHelper();
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mTodoListRecyclerView);

        initItemTouchListener();

        return root;
    }

    private ItemTouchHelper.Callback initItemTouchHelper() {
        return new
                    ItemTouchHelper.SimpleCallback(ItemTouchHelper.RIGHT, ItemTouchHelper.LEFT |
                                                   ItemTouchHelper.RIGHT) {
                @Override
                public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                    return false;
                }

                @Override
                public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                    int adapterPosition = viewHolder.getAdapterPosition();
                    removeDialog(adapterPosition, mTodoItemAdapter.getTodo(viewHolder.getAdapterPosition()));
                }
            };
    }

    private void initFragmentViews(View root) {
        mTodoListRecyclerView = (RecyclerView) root.findViewById(R.id.todoRecyclerView);
        mTodoListRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());

        mTodoItemAdapter = new TodoItemAdapter(getActivity(), mTodoItems);
        mTodoItemAdapter.setTodoItems(mTodoItems);
        mTodoItemAdapter.animateTo(mTodoItems);

        mTodoListRecyclerView.setLayoutManager(layoutManager);
        mTodoListRecyclerView.setAdapter(mTodoItemAdapter);
    }

    private void removeDialog(final int adapterPosition, final TodoItem todoItem) {
        new AlertDialog.Builder(getActivity())
                .setTitle(getActivity().getResources().getString(R.string.todo_remove))
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        BusProvider.getInstance().post(new DeleteTodoItemEvent(todoItem.getId()));
                        removeTodoItem(adapterPosition);
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                        mTodoItemAdapter.notifyDataSetChanged();
                    }
                })
                .show();
    }

    private void removeTodoItem(int adapterPosition) {
        mTodoItemAdapter.removeItem(adapterPosition);
        mTodoItemAdapter.notifyDataSetChanged();
        if (mTodoItemAdapter.getItemCount() == 0)
            BusProvider.getInstance().post(new RemoveFragment());
    }

    private void initItemTouchListener() {
        final GestureDetector mGestureDetector = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {

                    @Override
                    public boolean onSingleTapUp(MotionEvent e) {
                        return true;
                    }

                });

        mTodoListRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                View child = mTodoListRecyclerView.findChildViewUnder(e.getX(), e.getY());

                if (child != null && mGestureDetector.onTouchEvent(e)) {
                    int position = rv.getChildAdapterPosition(child);
                    TodoItem todoItem = mTodoItemAdapter.getTodo(position);
                    editDialogFragment(todoItem);
                    return true;
                }
                return false;
            }

            @Override
            public void onTouchEvent(RecyclerView rv, MotionEvent e) {

            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

            }
        });

    }

    private void editDialogFragment(final TodoItem todoItem) {

        LayoutInflater layoutInflater = getActivity().getLayoutInflater();
        View v = layoutInflater.inflate(R.layout.dialog_new_todo, null);
        final EditText titleEditText = (EditText) v.findViewById(R.id.titleEditText);
        final EditText bodyEditText = (EditText) v.findViewById(R.id.bodyEditText);
        titleEditText.setText(todoItem.getTitle());
        bodyEditText.setText(todoItem.getBody());

        new AlertDialog.Builder(getActivity())
                .setTitle(getActivity().getResources().getString(R.string.todo_edit))
                .setView(v)
                .setPositiveButton(getResources().getString(R.string.todo_update), new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (!titleEditText.getText().toString().isEmpty() &&
                                !bodyEditText.getText().toString().isEmpty()) {
                            TodoItem newTodo = getTodoItem(titleEditText, bodyEditText, todoItem);
                            BusProvider.getInstance().post(new UpdateTodoItemEvent(newTodo));
                        }
                    }
                })
                .setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .show();
    }

    private TodoItem getTodoItem(EditText titleEditText, EditText bodyEditText, TodoItem todoItem) {
        String title = titleEditText.getText().toString().trim();
        String body = bodyEditText.getText().toString().trim();

        TodoItem newTodo = new TodoItem();
        newTodo.setId(todoItem.getId());
        newTodo.setTitle(title);
        newTodo.setBody(body);
        newTodo.setCreatedAt(todoItem.getCreatedAt());
        return newTodo;
    }

}
