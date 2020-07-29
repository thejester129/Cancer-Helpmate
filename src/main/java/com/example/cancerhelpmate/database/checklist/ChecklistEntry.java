package com.example.cancerhelpmate.database.checklist;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "checklist")
public class ChecklistEntry {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "checklist_item_id")
    private int id;
    @ColumnInfo(name = "checklist_item_title")
    private String title;
    @ColumnInfo(name = "checklist_item_body")
    private String body;
    @ColumnInfo(name = "checklist_item_checked")
    private boolean checked;

    @Ignore
    public ChecklistEntry() {

    }

    public ChecklistEntry(String title) {
        this.title = title;
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

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}

