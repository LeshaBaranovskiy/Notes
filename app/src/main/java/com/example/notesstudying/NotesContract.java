package com.example.notesstudying;

import android.provider.BaseColumns;


// Этот класс ничего не делает, в нем хранятся разные данные про базу данных

public class NotesContract {

    //класс для табоицы notes
    public static final class NotesEntry implements BaseColumns{
        // Название таблицы и её столбцов
        public static final String TABLE_NAME = "notes";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_DESCRIPTION = "description";
        public static final String COLUMN_DAY_OF_WEEK = "day_of_week";
        public static final String COLUMN_PRIORITY = "priority";

        // Типы данных в таблице
        public static final String TYPE_TEXT = "TEXT";
        public static final String TYPE_INTEGER = "INTEGER";

        // Управление таблицей
        public static final String CREATE_COMMAND = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME +
                " ( " + _ID + " " + TYPE_INTEGER + " PRIMARY KEY AUTOINCREMENT," +
                COLUMN_TITLE + " " + TYPE_TEXT + ", " +
                COLUMN_DESCRIPTION + " " + TYPE_TEXT + ", " +
                COLUMN_DAY_OF_WEEK + " " + TYPE_TEXT + ", " +
                COLUMN_PRIORITY + " " + TYPE_INTEGER + ")";
        public static final String DROP_COMMAND = "DROP TABLE IF EXISTS " + TABLE_NAME;
    }
}
