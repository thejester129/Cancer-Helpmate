package com.example.cancerhelpmate.database.recipes;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.wellbeing.WellbeingRecipeConverter;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietFilterRecipeType;

import java.util.ArrayList;
import java.util.List;


@Database(entities = {RecipeEntry.class}, version = 1, exportSchema = false)
@TypeConverters({RecipeFilterTypeConverter.class})
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

        recipeEntries.add(new RecipeEntry("Sweet Potato Crab Cakes", 379,11.2,17.4,2.2,45.8,4,10,35,R.drawable.sweet_potato_crab_cakes,false,true,false,true,false,false, DietFilterRecipeType.STARTERS));
        recipeEntries.add(new RecipeEntry("Minestrone Soup", 391,15.1,10.5,3,61.4,11.9,10,35,R.drawable.minestrone_soup,false,false,true,false,true,true, DietFilterRecipeType.STARTERS));
        recipeEntries.add(new RecipeEntry("Sardine Bruschetta", 221,14.2,9.3,2.2,21.3,2,10,6,R.drawable.sardine_bruschetta,false,true,false,true,true,false, DietFilterRecipeType.STARTERS));

        recipeEntries.add(new RecipeEntry("Tuna and Veg Spaghetti", 548,39,17,9,64,3,2,15,R.drawable.tune_and_veg_spaghetti,true,false,true,false,false,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Spring onion, garlic and prawn risotto", 363,13.8,5.5,2,64,1.4,10,35,R.drawable.spring_onion_risotto,true,false,true,true,true,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Chicken Curry", 737,46.3,47.5,10.4,32.5,3.4,10,60,R.drawable.chicken_curry,false,false,false,false,false,false, DietFilterRecipeType.MAIN));

        return recipeEntries;
    }

}
