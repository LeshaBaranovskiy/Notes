package com.example.notesstudying;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


// Помогает работать с БД
public class NotesDBHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "notes.db"; //название БД
    private static final int DB_VERSION = 3;

    public NotesDBHelper(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // Создание таблицы
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(NotesContract.NotesEntry.CREATE_COMMAND);
    }

    // Обновление таблицы
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(NotesContract.NotesEntry.DROP_COMMAND);
        onCreate(sqLiteDatabase);
    }
}
