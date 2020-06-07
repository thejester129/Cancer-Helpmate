package com.example.cancerhelpmate.database.settings;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "settings")
public class SettingsEntry {


    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "settings_id")
    private int id;


    @Ignore
    public SettingsEntry() {

    }

    public SettingsEntry(@NotNull int id) {
        this.id = id;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
