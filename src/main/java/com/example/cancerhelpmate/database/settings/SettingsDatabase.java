package com.example.cancerhelpmate.database.settings;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileEntry;

@Database(entities = {SettingsEntry.class}, version = 1, exportSchema = false)
public abstract class SettingsDatabase extends RoomDatabase {

    public abstract SettingsDAO getDAO();

    private static SettingsDatabase INSTANCE;

    public static SettingsDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (com.example.cancerhelpmate.database.profile.ProfileDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SettingsDatabase.class, "settingsDB")
                            .allowMainThreadQueries()
                            .addCallback(sRoomDatabaseCallback)
                            .build();
                }
            }
        }
        return INSTANCE;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteTable();
        new PopulateDbAsync(INSTANCE.getDAO()).execute(new SettingsEntry(0));
    }

    private static Callback sRoomDatabaseCallback =
            new Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE.getDAO()).execute(new SettingsEntry(0));
                }
            };

    private static class PopulateDbAsync extends AsyncTask<SettingsEntry, Void, Void> {

        private SettingsDAO mAsyncTaskDao;

        PopulateDbAsync(SettingsDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final SettingsEntry... params) {
            if (mAsyncTaskDao.getSettings() == null) {
                mAsyncTaskDao.addEntry(params[0]);
            }
            return null;
        }
    }
}