package com.example.cancerhelpmate.database.checklist;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietFilterRecipeType;

import java.util.ArrayList;
import java.util.List;


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
                    populateDatabase();
                }
            }
        }
        return INSTANCE;
    }

    public static void populateDatabase(){
        if (INSTANCE.getDAO().getAllItems().size() == 0) {
            List<ChecklistEntry> items = getChecklistItems();
            for(ChecklistEntry item : items){
                INSTANCE.getDAO().addEntry(item);
            }
        }
    }

    private static List<ChecklistEntry> getChecklistItems(){
        List<ChecklistEntry>items = new ArrayList<>();
        items.add(new ChecklistEntry("Complete today's Day Tracker"));
        items.add(new ChecklistEntry("Enter a meal in Diet section"));

        return items;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteTable();
        populateDatabase();
    }

}
