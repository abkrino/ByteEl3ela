package com.example.byteel3ela.pojo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "notification.db";
    public static final String TABLE_NAME = "notification";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "TITLE";
    public static final String COL_3 = "BODY";
    public static final String COL_4 = "TIME";


    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ,TITLE TEXT,BODY TEXT ,TIME TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(" DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public boolean insertData(String title, String body, String time) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, title);
        contentValues.put(COL_3, body);
        contentValues.put(COL_4, time);
        long result = db.insert(TABLE_NAME, null, contentValues);
        db.close();
        //to check whether Data is Inserted in Database

        return result != -1;
    }

    public Cursor getAllData() {
        SQLiteDatabase dp = this.getWritableDatabase();
        Cursor res = dp.rawQuery("Select * from " + TABLE_NAME, null);
        return res;
    }
}

