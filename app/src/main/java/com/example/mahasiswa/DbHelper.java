package com.example.mahasiswa;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

import androidx.annotation.Nullable;


public class DbHelper extends SQLiteOpenHelper{

    public static final String DATABASE_NAME = "Mahasiswa.db";
    private static final int DATABASE_VERSION = 1;
    private static  final String TABLE_STUDENTS = "students";
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "nama";

    private static final String CREATE_TABLE_STUDENT = "CREATE TABLE " + TABLE_STUDENTS + "(" + KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT,"
            +KEY_NAME + "TEXT);";
    public DbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        Log.d("table",CREATE_TABLE_STUDENT);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_STUDENT);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS'" + TABLE_STUDENTS +"'");
        onCreate(db);
    }

    public long addStudentDetail (String student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, student);
        long insert = db.insert(TABLE_STUDENTS,null, values);
        return insert;
    }

    public ArrayList<String> getAllStudentsList(){
        ArrayList<String> studentsArrayList = new ArrayList<String>();
        String name="";
        String selectQuerry = "SELECT * FROM " + TABLE_STUDENTS;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor c = db.rawQuery(selectQuerry, null);
        if (c.moveToFirst()){
            do {
                name =c.getString(c.getColumnIndex(KEY_NAME));
                studentsArrayList.add(name);
            }while (c.moveToNext());
            Log.d("array",studentsArrayList.toString());
        }
        return studentsArrayList;
    }
}
