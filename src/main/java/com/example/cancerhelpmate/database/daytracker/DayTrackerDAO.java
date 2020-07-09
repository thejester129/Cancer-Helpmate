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
    public long addEntry(DayTrackerEntry entry);

    @Update
    public void updateEntry(DayTrackerEntry entry);

    @Delete
    public void deleteEntry(DayTrackerEntry entry);

    @Insert
    public long addEntry(DayTrackerWeeklyEntry entry);

    @Update
    public void updateEntry(DayTrackerWeeklyEntry entry);

    @Delete
    public void deleteEntry(DayTrackerWeeklyEntry entry);

    @Query("select * from day_trackers where day_tracker_date == :date")
    public DayTrackerEntry getDayTracker(String date);

    @Query("select * from day_trackers where day_tracker_date == :date")
    public LiveData<DayTrackerEntry> getLiveDayTracker(String date);

    @Query("select * from day_trackers")
    public List<DayTrackerEntry> getDayTrackers();

    @Query("select * from day_trackers")
    public LiveData<List<DayTrackerEntry>> getLiveDayTrackers();

    @Query("select * from day_trackers_weekly")
    public List<DayTrackerWeeklyEntry> getWeeklyDayTrackers();

    @Query("select * from day_trackers_weekly where day_tracker_weekly_date == :date")
    public DayTrackerWeeklyEntry getWeeklyDayTracker(String date);

    @Query("select * from day_trackers_weekly")
    public LiveData<List<DayTrackerWeeklyEntry>> getLiveWeeklyDayTrackers();

    @Query("DELETE FROM day_trackers")
    public void deleteDayTrackersTable();

    @Query("DELETE FROM day_trackers_weekly")
    public void deleteDayTrackersWeeklyTable();
}
