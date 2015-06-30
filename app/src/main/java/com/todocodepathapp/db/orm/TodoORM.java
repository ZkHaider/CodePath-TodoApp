package com.todocodepathapp.db.orm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.todocodepathapp.db.helper.TodoSQLiteHelper;
import com.todocodepathapp.models.TodoItem;
import com.todocodepathapp.utils.DateUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoORM {

    /***
     *  ORM Constants
     */
    private static final String TAG = TodoORM.class.getSimpleName();
    private static final String TABLE_NAME = "todo";
    private static final String COMMA_SEP = ", ";
    private static final String COLUMN_ID_TYPE = "INTEGER PRIMARY KEY AUTOINCREMENT";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_TITLE_TYPE = "TEXT";
    private static final String COLUMN_TITLE = "title";
    private static final String COLUMN_BODY_TYPE = "TEXT";
    private static final String COLUMN_BODY = "body";
    private static final String COLUMN_CREATE_DATE_TYPE = "TEXT";
    private static final String COLUMN_CREATE_DATE = "created_at";
    private static final String COLUMN_COMPLETION_DATE_TYPE = "TEXT";
    private static final String COLUMN_COMPLETION_DATE = "completion_date";
    private static final String ORDER = "ORDER BY";
    private static final String DESC_ORDER = "DESC";
    private static final String[] COLUMNS =
            { COLUMN_ID, COLUMN_TITLE, COLUMN_BODY, COLUMN_CREATE_DATE, COLUMN_COMPLETION_DATE };

    public static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME + " ( " +
                    COLUMN_ID + " " + COLUMN_ID_TYPE + COMMA_SEP +
                    COLUMN_TITLE + " " + COLUMN_TITLE_TYPE + COMMA_SEP +
                    COLUMN_BODY + " " + COLUMN_BODY_TYPE + COMMA_SEP +
                    COLUMN_CREATE_DATE + " " + COLUMN_CREATE_DATE_TYPE + COMMA_SEP +
                    COLUMN_COMPLETION_DATE + " " + COLUMN_COMPLETION_DATE_TYPE +
            " ) ";

    public static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public static void insertTodo(Context context, TodoItem todoItem) {
        TodoSQLiteHelper todoSQLiteHelper = new TodoSQLiteHelper(context);
        SQLiteDatabase db = todoSQLiteHelper.getWritableDatabase();

        ContentValues values = todoToContentValues(todoItem);
        long todoId = db.insert(TodoORM.TABLE_NAME, "null", values);
        Log.d(TAG, "Inserted new Todo Item with Id: " + todoId);
        db.close();
    }

    public static List<TodoItem> getTodoItems(Context context) {
        TodoSQLiteHelper todoSQLiteHelper = new TodoSQLiteHelper(context);
        SQLiteDatabase db = todoSQLiteHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM "
                + TABLE_NAME + " "
                + ORDER + " "
                + "ID" + " "
                + DESC_ORDER, null);
        Log.d(TAG, "Loaded " + cursor.getCount() + " TodoItems...");

        List<TodoItem> todoItems = new ArrayList<>();
        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                TodoItem todoItem = cursorToTodo(cursor);
                todoItems.add(todoItem);
                cursor.moveToNext();
            }
            Log.d(TAG, "TodoItems loaded successfully");
        }

        db.close();
        return todoItems;
    }

    private static TodoItem cursorToTodo(Cursor cursor) {
        TodoItem todoItem = new TodoItem();
        todoItem.setId(cursor.getLong(cursor.getColumnIndex(COLUMN_ID)));
        todoItem.setTitle(cursor.getString(cursor.getColumnIndex(COLUMN_TITLE)));
        todoItem.setBody(cursor.getString(cursor.getColumnIndex(COLUMN_BODY)));

        String creationDate = cursor.getString(cursor.getColumnIndex(COLUMN_CREATE_DATE));
        String completionDate = cursor.getString(cursor.getColumnIndex(COLUMN_COMPLETION_DATE));
        todoItem.setCreatedAt(DateUtils.parseDate(creationDate));
        todoItem.setCompletionDate(DateUtils.parseDate(completionDate));

        return todoItem;
    }

    public static List<TodoItem> updateTodoItem(Context context, TodoItem todoItem) {
        TodoSQLiteHelper todoSQLiteHelper = new TodoSQLiteHelper(context);
        SQLiteDatabase db = todoSQLiteHelper.getWritableDatabase();

        ContentValues values = todoToContentValues(todoItem);

        db.update(TABLE_NAME,                                    // table
                values,                                          // column/value
                COLUMN_ID + " = ?",                              // selections
                new String[]{String.valueOf(todoItem.getId())}); // selection args

        return getTodoItems(context);
    }

    public static void deleteTodo(Context context, long todoId) {
        TodoSQLiteHelper todoSQLiteHelper = new TodoSQLiteHelper(context);
        SQLiteDatabase db = todoSQLiteHelper.getWritableDatabase();

        db.delete(TABLE_NAME,                                    // table name
                COLUMN_ID + " = ?",                              // selections
                new String[]{String.valueOf(todoId)});           // selections args

        db.close();
        Log.d(TAG, "Deleting TodoItem");
    }

    private static ContentValues todoToContentValues(TodoItem todoItem) {
        ContentValues values = new ContentValues();
        values.put(TodoORM.COLUMN_TITLE, todoItem.getTitle());
        values.put(TodoORM.COLUMN_BODY, todoItem.getBody());

        String creationDateString = DateUtils.dateToString(todoItem.getCreatedAt());
        String completionDateString = null;
        if (todoItem.getCompletionDate() != null)
            completionDateString = DateUtils.dateToString(todoItem.getCompletionDate());

        values.put(TodoORM.COLUMN_CREATE_DATE, creationDateString);
        values.put(TodoORM.COLUMN_COMPLETION_DATE, completionDateString);
        return values;
    }


}
