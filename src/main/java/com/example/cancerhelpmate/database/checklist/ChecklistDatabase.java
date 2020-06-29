package com.example.cancerhelpmate.database.checklist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ChecklistEntry.class}, version = 1, exportSchema = false)
public abstract class ChecklistDatabase extends RoomDatabase {

    public abstract ChecklistDAO getDAO();

    private static ChecklistDatabase INSTANCE;

    public static ChecklistDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ChecklistDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ChecklistDatabase.class, "checklistDB")
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
