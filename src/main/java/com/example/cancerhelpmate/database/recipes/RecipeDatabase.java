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
        recipeEntries.add(new RecipeEntry("Caribbean-style tuna spread", 270,31.9,14,2.2,4.2,2.2,15,0,R.drawable.carribean,false,false,false,true,false,false, DietFilterRecipeType.STARTERS));

        recipeEntries.add(new RecipeEntry("Tuna and Veg Spaghetti", 548,39,17,9,64,3,2,15,R.drawable.tune_and_veg_spaghetti,true,false,true,false,false,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Spring onion, garlic and prawn risotto", 363,13.8,5.5,2,64,1.4,10,35,R.drawable.spring_onion_risotto,true,false,true,true,true,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Chicken Curry", 737,46.3,47.5,10.4,32.5,3.4,10,60,R.drawable.chicken_curry,false,false,false,false,false,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Speedy Moroccan meatballs", 388,18,25,9,24,6,5,15,R.drawable.meatballs,false,false,false,true,false,false, DietFilterRecipeType.MAIN));
        recipeEntries.add(new RecipeEntry("Mixed bean Mexican chilli", 231,10.1,8.6,2,29.8,9.7,10,30,R.drawable.mexican_chilli,false,false,true,true,false,true, DietFilterRecipeType.MAIN));

        recipeEntries.add(new RecipeEntry("Summer pudding", 429,11.6,2.4,0.4,93,10.7,15,30,R.drawable.summer_tart,false,true,true,true,false,true, DietFilterRecipeType.DESSERTS));
        recipeEntries.add(new RecipeEntry("Microwave banana pudding", 474,7,26,15,57,1,10,10,R.drawable.banana_pudding,true,false,true,true,false,true, DietFilterRecipeType.DESSERTS));
        recipeEntries.add(new RecipeEntry("Coconut and cardamom rice pudding", 89,1.2,7.5,6.2,4.1,1,5,90,R.drawable.rice_pudding,true,false,true,false,true,true, DietFilterRecipeType.DESSERTS));

        recipeEntries.add(new RecipeEntry("Banana, honey and hazelnut smoothie", 220,8,10,1,24,2,10,1,R.drawable.banana_smoothie,true,true,true,true,false,true, DietFilterRecipeType.DRINKS));
        recipeEntries.add(new RecipeEntry("Macmillan coco kahona", 63,0.8,0.2,0.1,15.5,2.1,10,1,R.drawable.coco_cahona,true,false,true,false,false,true, DietFilterRecipeType.DRINKS));
        recipeEntries.add(new RecipeEntry("Fruit Smoothie", 352,3.5,23,13,34,1,5,1,R.drawable.fruit_smoothie,true,false,true,true,false,true, DietFilterRecipeType.DRINKS));

        return recipeEntries;
    }

}
