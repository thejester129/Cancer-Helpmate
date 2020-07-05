package com.example.cancerhelpmate.database.wellbeing;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


@Database(entities = {WellbeingEntry.class}, version = 1, exportSchema = false)
@TypeConverters({WellbeingRecipeConverter.class})
public abstract class WellbeingDatabase extends RoomDatabase {

    public abstract WellbeingDAO getDAO();

    private static WellbeingDatabase INSTANCE;

    public static WellbeingDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (WellbeingDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            WellbeingDatabase.class, "wellbeingDB")
                            .allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteTable();
    }

}
