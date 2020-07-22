package com.example.cancerhelpmate.database.recipes;

import androidx.room.TypeConverter;

import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietFilterRecipeType;

public class RecipeFilterTypeConverter {
    @TypeConverter
    public static String filterToString(DietFilterRecipeType recipeType) {
        switch (recipeType){
            case ANY:
                return "ANY";
            case MAIN:
                return "MAIN";
            case DRINKS:
                return "DRINKS";
            case DESSERTS:
                return "DESSERTS";
            case STARTERS:
                return "STARTERS";
        }
        return "";

    }

    @TypeConverter
    public static DietFilterRecipeType stringToFilter(String string) {
        switch (string){
            case "ANY":
                return DietFilterRecipeType.ANY;
            case "MAIN":
                return DietFilterRecipeType.MAIN;
            case "DRINKS":
                return DietFilterRecipeType.DRINKS;
            case "DESSERTS":
                return DietFilterRecipeType.DESSERTS;
            case "STARTERS":
                return DietFilterRecipeType.STARTERS;
        }
        return DietFilterRecipeType.ANY;
    }
}
