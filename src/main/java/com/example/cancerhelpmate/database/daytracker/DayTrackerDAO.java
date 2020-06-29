package com.example.cancerhelpmate.database.daytracker;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cancerhelpmate.database.journal.JournalEntry;

import java.util.List;

@Dao
public interface DayTrackerDAO {

    @Insert
    public long addEntry(DayTrackerEntry dayTrackerEntry);

    @Update
    public void updateEntry(DayTrackerEntry dayTrackerEntry);

    @Delete
    public void deleteEntry(DayTrackerEntry dayTrackerEntry);

    @Query("select * from day_trackers where day_tracker_date == :date")
    public DayTrackerEntry getDayTracker(String date);

    @Query("select * from day_trackers")
    public List<DayTrackerEntry> getDayTrackers();

    @Query("select * from day_trackers")
    public LiveData<List<DayTrackerEntry>> getLiveDayTrackers();

    @Query("DELETE FROM day_trackers")
    public void deleteTable();
}
