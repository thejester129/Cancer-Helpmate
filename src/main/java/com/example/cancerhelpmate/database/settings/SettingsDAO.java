package com.example.cancerhelpmate.database.settings;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cancerhelpmate.database.profile.ProfileEntry;

@Dao
public interface SettingsDAO {

    @Insert
    public long addEntry(SettingsEntry settingsEntry);

    @Update
    public void updateEntry(SettingsEntry settingsEntry);

    @Delete
    public void deleteEntry(SettingsEntry settingsEntry);


    @Query("select * from settings where settings_id == 0 limit 1")
    public SettingsEntry getSettings();

    @Query("DELETE FROM settings")
    public void deleteTable();

}