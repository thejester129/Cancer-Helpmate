package com.example.cancerhelpmate.database.journal;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cancerhelpmate.common.DateManager;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "journals")
public class JournalEntry {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "journal_id")
    private int id;
    @ColumnInfo(name = "journal_title")
    private String title;
    @ColumnInfo(name = "journal_body")
    private String body;
    @ColumnInfo(name = "journal_date")
    private String date;


    public JournalEntry() {
        this.date = DateManager.getTodayAsString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
