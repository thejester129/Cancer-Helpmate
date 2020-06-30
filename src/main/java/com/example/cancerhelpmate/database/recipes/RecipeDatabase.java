package com.example.cancerhelpmate.database.recipes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.cancerhelpmate.R;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {RecipeEntry.class}, version = 1, exportSchema = false)
public abstract class RecipeDatabase extends RoomDatabase {

    public abstract RecipeDAO getDAO();

    private static RecipeDatabase INSTANCE;

    public static RecipeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (RecipeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            RecipeDatabase.class, "recipeDB")
                            .allowMainThreadQueries()
                            .build();
                    populateDatabase();
                }
            }
        }
        return INSTANCE;
    }

    public static void resetDatabase() {
        INSTANCE.getDAO().deleteTable();
        populateDatabase();
    }

    public static void populateDatabase(){
        if (INSTANCE.getDAO().getAllItems().size() == 0) {
            List<RecipeEntry>recipeEntries = getRecipeEntries();
            for(RecipeEntry recipe : recipeEntries){
                INSTANCE.getDAO().addEntry(recipe);
            }
        }
    }

    private static List<RecipeEntry> getRecipeEntries(){
        List<RecipeEntry>recipeEntries = new ArrayList<>();

        recipeEntries.add(new RecipeEntry("Blueberry Porridge",320,21.5,19.2,11.6,27.6,3.1,5,3, R.drawable.blueberryporridge,null,null));
        recipeEntries.add(new RecipeEntry("Protein Granola",R.drawable.granola));
        recipeEntries.add(new RecipeEntry("American Pancakes", R.drawable.pancakes));
        recipeEntries.add(new RecipeEntry("Egg Cheese Crumpets", R.drawable.egg_cheese_crumpets));
        recipeEntries.add(new RecipeEntry("Veggie Breakfast Bakes",R.drawable.veggie_breakfast_bakes));
        recipeEntries.add(new RecipeEntry("French Toast",R.drawable.french_toast));

        return recipeEntries;
    }

}
