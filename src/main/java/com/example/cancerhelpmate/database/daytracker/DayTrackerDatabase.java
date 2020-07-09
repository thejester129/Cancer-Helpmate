package com.example.cancerhelpmate.database.daytracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {DayTrackerEntry.class, DayTrackerWeeklyEntry.class}, version = 1, exportSchema = false)
@TypeConverters({DayTrackerEmotionConverter.class})
public abstract class DayTrackerDatabase extends RoomDatabase {

    public abstract DayTrackerDAO getDAO();

    private static DayTrackerDatabase INSTANCE;

    public static DayTrackerDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (DayTrackerDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            DayTrackerDatabase.class, "daytrackerDB")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteDayTrackersTable();
        INSTANCE.getDAO().deleteDayTrackersWeeklyTable();
    }

}