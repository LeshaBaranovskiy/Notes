package com.example.notesstudying;


import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "notes")
public class Note {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String description;
    private String dayOfTheWeek;
    private int priority;

    public Note(int id, String title, String description, String dayOfTheWeek, int priority) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.dayOfTheWeek = dayOfTheWeek;
        this.priority = priority;
    }

    @Ignore
    public Note(String title, String description, String dayOfTheWeek, int priority) {
        this.title = title;
        this.description = description;
        this.dayOfTheWeek = dayOfTheWeek;
        this.priority = priority;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDayOfTheWeek() {
        return dayOfTheWeek;
    }

    public int getPriority() {
        return priority;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDayOfTheWeek(String dayOfTheWeek) {
        this.dayOfTheWeek = dayOfTheWeek;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }
}
