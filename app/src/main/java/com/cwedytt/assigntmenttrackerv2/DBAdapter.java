package com.cwedytt.assigntmenttrackerv2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBAdapter extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "AssignmentTracker.db";
    static final String DATABASE_TABLE = "assignments";
    private static final int DATABASE_VERSION = 2;

    static final String KEY_ROWID = "_id";
    static final String KEY_ASSIGNMENT_NAME = "assignmentName";
    static final String KEY_COURSE_CODE = "courseCode";
    static final String KEY_COURSE_NAME = "courseName";
    static final String KEY_DUE_DATE = "dueDate";
    static final String KEY_DUE_TIME = "dueTime";
    static final String KEY_IS_COMPLETE = "isComplete";
    static final String KEY_COMPLETED_DATE = "completedDate";
    static final String KEY_COMPLETED_TIME = "completedTime";

    static final String DATABASE_CREATE =
            "create table assignments (" +
                    "_id integer primary key autoincrement, "
                    + "assignmentName text not null, " +
                    " courseCode text not null, " +
                    " courseName text, " +
                    " dueDate text not null, " +
                    " dueTime text, " +
                    " isComplete integer, " +
                    " completedDate text, " +
                    " completedTime text); ";

    public DBAdapter(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
        onCreate(db);
    }

    //CRUD methods
    void addAssignment(String assignmentName,
                       String courseCode,
                       String courseName,
                       String dueDate,
                       String dueTime,
                       int isComplete,
                       String completedDate,
                       String completedTime){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues initialValues = new ContentValues();

        initialValues.put(KEY_ASSIGNMENT_NAME, assignmentName);
        initialValues.put(KEY_COURSE_CODE, courseCode);
        initialValues.put(KEY_COURSE_NAME, courseName);
        initialValues.put(KEY_DUE_DATE, dueDate);
        initialValues.put(KEY_DUE_TIME, dueTime);
        initialValues.put(KEY_IS_COMPLETE, isComplete);
        initialValues.put(KEY_COMPLETED_DATE, completedDate);
        initialValues.put(KEY_COMPLETED_TIME, completedTime);

        long result = db.insert(DATABASE_TABLE,null, initialValues);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    Cursor getAllAssignments(){
        String query = "SELECT * FROM " + DATABASE_TABLE;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
}
