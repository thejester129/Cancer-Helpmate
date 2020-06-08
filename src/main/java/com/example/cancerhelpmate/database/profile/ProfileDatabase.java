package com.example.cancerhelpmate.database.profile;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {ProfileEntry.class}, version = 1, exportSchema = false)
public abstract class ProfileDatabase extends RoomDatabase {

    public abstract ProfileDAO getDAO();

    private static ProfileDatabase INSTANCE;

    public static ProfileDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ProfileDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ProfileDatabase.class, "profileDB")
                            .allowMainThreadQueries()
                            .build();
                    if(INSTANCE.getDAO().getProfile() == null){
                        populateDatabase();
                    }
                }
            }
        }
        return INSTANCE;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteTable();
        //new PopulateDbAsync(INSTANCE.getDAO()).execute(new ProfileEntry(0));
    }

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback() {
                @Override
                public void onCreate(@NonNull SupportSQLiteDatabase db) {
                    super.onOpen(db);
                    populateDatabase();
                    //new PopulateDbAsync(INSTANCE.getDAO()).execute(new ProfileEntry(0));
                }
            };

    public static void populateDatabase(){
        if (INSTANCE.getDAO().getProfile() == null) {
            INSTANCE.getDAO().addEntry(new ProfileEntry(0));
        }
    }
//    private static class PopulateDbAsync extends AsyncTask<ProfileEntry, Void, Void> {
//
//        private ProfileDAO mAsyncTaskDao;
//
//        PopulateDbAsync(ProfileDAO dao) {
//            mAsyncTaskDao = dao;
//        }
//
//        @Override
//        protected Void doInBackground(final ProfileEntry... params) {
//            if (mAsyncTaskDao.getProfile() == null) {
//                mAsyncTaskDao.addEntry(params[0]);
//            }
//            return null;
//        }
//    }
}
