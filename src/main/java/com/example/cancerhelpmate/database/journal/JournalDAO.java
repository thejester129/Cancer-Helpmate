package com.example.cancerhelpmate.database.journal;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cancerhelpmate.database.profile.ProfileEntry;

import java.util.List;

@Dao
public interface JournalDAO {

    @Insert
    public long addEntry(JournalEntry journalEntry);

    @Update
    public void updateEntry(JournalEntry journalEntry);

    @Delete
    public void deleteEntry(JournalEntry journalEntry);

    @Query("select * from journals")
    public List<JournalEntry> getJournals();

    @Query("select * from journals")
    public LiveData<List<JournalEntry>> getLiveJournals();

    @Query("DELETE FROM journals")
    public void deleteTable();
}
