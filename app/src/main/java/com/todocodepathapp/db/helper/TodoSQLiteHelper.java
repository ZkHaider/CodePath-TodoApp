package com.todocodepathapp.db.helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.todocodepathapp.custom.Constants;
import com.todocodepathapp.db.orm.TodoORM;

/**
 * Created by ZkHaider on 6/29/15.
 */
public class TodoSQLiteHelper extends SQLiteOpenHelper {

    private static final String TAG = TodoSQLiteHelper.class.getSimpleName();

    public TodoSQLiteHelper(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i(TAG, "Creating database [" + Constants.DATABASE_NAME + " v." + Constants.DATABASE_VERSION + "]...");
        db.execSQL(TodoORM.SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i(TAG, "Upgrading database [" + Constants.DATABASE_NAME +" v." + oldVersion +"] to [" +
                Constants.DATABASE_NAME + " v." + newVersion + "]...");
        db.execSQL(TodoORM.SQL_DROP_TABLE);
        onCreate(db);
    }

}
