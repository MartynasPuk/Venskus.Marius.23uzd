package com.example.venskusmarius23uzd;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "note_list.db";
    public static final String TABLE_NAME = "notes";
    public static final String COL1 = "NAME";
    public static final String COL2 = "CATEGORY";
    public static final String COL3 = "NOTE_TEXT";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (NAME TEXT, " + " CATEGORY TEXT, " + " NOTE_TEXT TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addData(String name, String category, String note_text) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, name);
        contentValues.put(COL2, category);
        contentValues.put(COL3, note_text);

        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getListContents() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

    public int CountNotes() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        cur.moveToFirst();
        count = cur.getInt(0);

        cur.close();
        return count;
    }

    public int CountNotesFirstCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL2 + "='Svarbi info'", null);

        cur.moveToFirst();
        count = cur.getInt(0);

        cur.close();
        return count;
    }

    public int CountNotesSecondCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL2 + "='Susitikimai'", null);

        cur.moveToFirst();
        count = cur.getInt(0);

        cur.close();
        return count;
    }

    public int CountNotesThirdCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL2 + "='Pirkinių sąrašai'", null);

        cur.moveToFirst();
        count = cur.getInt(0);

        cur.close();
        return count;
    }

    public int CountNotesFourthCat() {
        SQLiteDatabase db = this.getReadableDatabase();
        int count = 0;

        Cursor cur = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME + " WHERE " + COL2 + "='Kiti užrašai'", null);

        cur.moveToFirst();
        count = cur.getInt(0);

        cur.close();
        return count;
    }

    public void deleteAll() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
        db.close();
    }

}

