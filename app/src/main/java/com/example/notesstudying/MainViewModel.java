package com.example.notesstudying;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private static NotesDatabase database;

    private LiveData<List<Note>> notes;

    public MainViewModel(@NonNull Application application) {
        super(application);
        database = NotesDatabase.getInstance(getApplication());

        notes = database.NotesDao().getAllNotes();
    }

    public LiveData<List<Note>> getNotes() {
        return notes;
    }

    public void insertNote(Note note) {
        new InsertTask().execute(note);
    }

    public void deleteNote(Note note) {
        new DeleteTask().execute(note);
    }

    private static class InsertTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0) {
                database.NotesDao().insertNotes(notes[0]);
            }
            return null;
        }
    }
    private static class DeleteTask extends AsyncTask<Note, Void, Void> {
        @Override
        protected Void doInBackground(Note... notes) {
            if (notes != null && notes.length > 0) {
                database.NotesDao().deleteNote(notes[0]);
            }
            return null;
        }
    }
}
