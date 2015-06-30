package com.todocodepathapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.todocodepathapp.R;
import com.todocodepathapp.custom.ui.MaterialColorHelper;
import com.todocodepathapp.models.TodoItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoItemAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = TodoItemAdapter.class.getSimpleName();

    private Context mContext;
    private List<TodoItem> mTodoItems;

    public TodoItemAdapter(Context context, List<TodoItem> todoItems) {
        mContext = context;
        mTodoItems = new ArrayList<>(todoItems);
    }

    public void setTodoItems(List<TodoItem> todoItems) {
        mTodoItems = new ArrayList<>(todoItems);
    }

    public List<TodoItem> getTodoItems() {
        return mTodoItems;
    }

    public TodoItem getTodo(int position) {
        return mTodoItems.get(position);
    }

    private int getRandomNumber(int[] array) {
        return array[(new Random()).nextInt(array.length)];
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.todo_item, viewGroup, false);
        mContext = v.getContext();
        return new TodoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {

        TodoViewHolder todoViewHolder = (TodoViewHolder) viewHolder;

        TodoItem todoItem = mTodoItems.get(position);
        // Set UI
        int[] materialColors = MaterialColorHelper.generateMaterialColors(mContext);
        int color = getRandomNumber(materialColors);
        todoViewHolder.mTodoItemLayout
                .setBackgroundColor(color);

        todoViewHolder.mTitle.setText(todoItem.getTitle());
        todoViewHolder.mBody.setText(todoItem.getBody());

        todoViewHolder.mDate.setText(todoItem.getCreatedAtTimeAgo());
        if (todoItem.getCompletionDate() != null)
            todoViewHolder.mCompletionDate.setText(todoItem.getCompletionTime());
    }

    public TodoItem removeItem(int position) {
        final TodoItem todoItem = mTodoItems.remove(position);
        notifyItemRemoved(position);
        return todoItem;
    }

    public void addItem(int position, TodoItem item) {
        mTodoItems.add(position, item);
        notifyItemInserted(position);
    }

    public void moveItem(int fromPosition, int toPosition) {
        final TodoItem item = mTodoItems.remove(fromPosition);
        mTodoItems.add(toPosition, item);
        notifyItemMoved(fromPosition, toPosition);
    }

    public void animateTo(List<TodoItem> items) {
        applyAndAnimateRemovals(items);
        applyAndAnimateAdditions(items);
        applyAndAnimateMovedItems(items);
    }

    private void applyAndAnimateRemovals(List<TodoItem> newItems) {
        for (int i = mTodoItems.size() - 1; i >= 0; i--) {
            final TodoItem model = mTodoItems.get(i);
            if (!newItems.contains(model)) {
                removeItem(i);
            }
        }
    }

    private void applyAndAnimateAdditions(List<TodoItem> newItems) {
        for (int i = 0, count = newItems.size(); i < count; i++) {
            final TodoItem model = newItems.get(i);
            if (!mTodoItems.contains(model)) {
                addItem(i, model);
            }
        }
    }

    private void applyAndAnimateMovedItems(List<TodoItem> newItems) {
        for (int toPosition = newItems.size() - 1; toPosition >= 0; toPosition--) {
            final TodoItem model = newItems.get(toPosition);
            final int fromPosition = mTodoItems.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }

    @Override
    public int getItemCount() {
        return mTodoItems.size();
    }

    public class TodoViewHolder extends RecyclerView.ViewHolder {

        private RelativeLayout mTodoItemLayout;
        private TextView mTitle;
        private TextView mBody;
        private TextView mDate;
        private TextView mCompletionDate;

        public TodoViewHolder(View itemView) {
            super(itemView);
            initViews(itemView);
        }

        private void initViews(View v) {
            mTodoItemLayout = (RelativeLayout) v.findViewById(R.id.todoItemLayout);
            mTitle = (TextView) v.findViewById(R.id.todoTitle);
            mBody = (TextView) v.findViewById(R.id.todoBody);
            mDate = (TextView) v.findViewById(R.id.todoDate);
            mCompletionDate = (TextView) v.findViewById(R.id.todoCompletionDate);
        }

    }

}
