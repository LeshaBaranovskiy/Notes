package com.example.notesstudying;

public class Note {
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
}
