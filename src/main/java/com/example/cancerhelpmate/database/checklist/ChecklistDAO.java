package com.example.cancerhelpmate.database.checklist;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cancerhelpmate.database.journal.JournalEntry;

import java.util.List;

@Dao
public interface ChecklistDAO {

    @Insert
    public long addEntry(ChecklistEntry entry);

    @Update
    public void updateEntry(ChecklistEntry entry);

    @Delete
    public void deleteEntry(ChecklistEntry entry);

    @Query("select * from checklist where checklist_item_title =:title")
    public ChecklistEntry getItem(String title);

    @Query("select * from checklist")
    public List<ChecklistEntry> getAllItems();

    @Query("select * from checklist")
    public LiveData<List<ChecklistEntry>> getAllLiveItems();

    @Query("select count(*) from checklist")
    public LiveData<Integer> getLiveCount();

    @Query("DELETE FROM checklist")
    public void deleteTable();
}
