package com.example.gift.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class giftdbhelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Shelter.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "GIFT";
    private static final String ID = "GIFTID";
    private static final String COLUMN_NAME = "STATUS";

    @Override
    public void onCreate(SQLiteDatabase db) {
        String SQL_CREATE_GIFT_TABLE = "CREATE TABLE " + TABLE_NAME + "(" + COLUMN_NAME + " INTEGER," + ID + " INTEGER);";
        db.execSQL(SQL_CREATE_GIFT_TABLE);
    }

    public giftdbhelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    public void addHandler() {
        ContentValues values = new ContentValues();
        values.put(ID, 0);
        values.put(COLUMN_NAME, 0);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void updateHandler() {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(ID, 1);
        args.put(COLUMN_NAME, 1);
        db.update(TABLE_NAME, args, ID + "=" + 0, null);
    }

    public boolean loadhander() {
        String query = "Select * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToNext();
        int a = cursor.getInt(0);
        db.close();
        if (a == 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
}