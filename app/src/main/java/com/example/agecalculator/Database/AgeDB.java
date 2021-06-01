package com.example.agecalculator.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AgeDB extends SQLiteOpenHelper {


    public AgeDB(@Nullable Context context) {
        super(context, "Age_Database", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE AgeTable(id INTEGER PRIMARY KEY AUTOINCREMENT, year TEXT, month TEXT, day TEXT, title TEXT, dob TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS AgeTable");
        onCreate(db);
    }

    public Boolean saveData(int day, int mon, int yr, String title, String date){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put("day", day);
        cv.put("month", mon);
        cv.put("year", yr);
        cv.put("title", title);
        cv.put("dob", date);

        long res = db.insert("AgeTable", null, cv);
        if (res == -1){
            return false;
        } else {
            return true;
        }
    }

    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM AgeTable", null);

        return cursor;
    }
}
