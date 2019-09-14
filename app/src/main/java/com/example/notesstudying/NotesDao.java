package com.example.notesstudying;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface NotesDao {
    @Query("SELECT * FROM  notes")
    LiveData<List<Note>> getAllNotes();

    @Insert
    void insertNotes(Note note);

    @Delete
    void deleteNote(Note note);

    @Query("DELETE FROM notes")
    void deleteAll();
}
