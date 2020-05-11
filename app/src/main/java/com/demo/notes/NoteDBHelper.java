/*
package com.demo.notes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NoteDBHelper extends SQLiteOpenHelper {
    public static final String DB_NAME = "notes.db";
    public static final int DB_VERSION = 2;

    public NoteDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NoteContract.NoteEntry.CREATE_COMMAND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NoteContract.NoteEntry.DROP_COMMAND);
        onCreate(sqLiteDatabase);
    }
}
*/
