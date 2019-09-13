package com.example.notesstudying;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.sql.SQLData;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final ArrayList<Note> notes = new ArrayList<>();
    private RecyclerView recyclerView;
    private NotesAdapter notesAdapter;
    private NotesDBHelper notesDBHelper;
    private SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycleViewNotes);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }

        //Получаем объект типа SQLiteDatabase, в который можем заносить данные
        notesDBHelper = new NotesDBHelper(this);
        database = notesDBHelper.getWritableDatabase();

        getData();

        notesAdapter = new NotesAdapter(notes);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(notesAdapter);

        // Устанавливаем слушатели событий
        notesAdapter.setOnNoteClickListener(new NotesAdapter.OnNoteClickListener() {
            @Override
            public void onNoteClick(int position) {
                Toast.makeText(MainActivity.this, "Номер позиции: " + position, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLongClick(int position) {
                removeElement(position);
            }
        });
        // Удаление свайпом
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                removeElement(viewHolder.getAdapterPosition());
            }
        });
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

    // Удаление элемента
    private void removeElement(int position) {
        int id = notes.get(position).getId();
        String where = NotesContract.NotesEntry._ID + " = ?";
        String[] whereArgs = new String[] {Integer.toString(id)};
        database.delete(NotesContract.NotesEntry.TABLE_NAME, where, whereArgs);
        notes.remove(position);
        notesAdapter.notifyDataSetChanged();
        getData();
    }

    // Переходим в активность создания заметки
    public void onClickAddNote(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

    private void getData() {
        notes.clear();
        // С помощью Cursor и метода moveToNext() можем обойти все строки таблицы(изначальное значение Cursor -1, при первой интерации он перейдет на строку с индексом 0
        Cursor cursor = database.query(NotesContract.NotesEntry.TABLE_NAME, null, null, null, null, null, NotesContract.NotesEntry.COLUMN_PRIORITY);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry._ID));
            String title = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_TITLE));
            String description = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DESCRIPTION));
            String dayOfWeek = cursor.getString(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_DAY_OF_WEEK));
            int priority = cursor.getInt(cursor.getColumnIndex(NotesContract.NotesEntry.COLUMN_PRIORITY));

            Note note = new Note(id, title, description, dayOfWeek, priority);
            notes.add(note);
        }
        cursor.close();
    }
}










