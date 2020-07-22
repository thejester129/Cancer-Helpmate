package com.example.cancerhelpmate.database.wellbeing;

import androidx.room.TypeConverter;

import com.example.cancerhelpmate.database.recipes.RecipeEntry;

public class WellbeingRecipeConverter {
    @TypeConverter
    public static String recipeToString(RecipeEntry recipeEntry) {
        if(recipeEntry != null){
            return recipeEntry.getId() + "/" + recipeEntry.getName() + "/" + recipeEntry.getCalories() + "/" + recipeEntry.getProtein() + "/" + recipeEntry.getFat() + "/" +
                    recipeEntry.getSaturates() + "/" + recipeEntry.getCarbohydrates() + "/" + recipeEntry.getFibre() + "/" + recipeEntry.getPreparation_time() + "/" +
                    recipeEntry.getCooking_time() + "/" + recipeEntry.getImageLink() + "/" + recipeEntry.isDryAndSoreMouth() + "/" + recipeEntry.isFeelingSick() + "/"
                    + recipeEntry.isProblemsChewing() + "/" + recipeEntry.isLossOfOfTasteOrSmell() + "/" +recipeEntry.isHealthyEating() + "/" + recipeEntry.isVegetarian();
        }
        return "";

    }

    @TypeConverter
    public static RecipeEntry stringToRecipe(String string) {
        if(string.isEmpty()){
            return null;
        }
        RecipeEntry recipe = new RecipeEntry();
        String[] params = string.split("/");
        if(params.length == 0){
            return new RecipeEntry();
        }

        recipe.setId(Integer.parseInt(params[0]));
        recipe.setName(params[1]);
        recipe.setCalories(Double.parseDouble(params[2]));
        recipe.setProtein(Double.parseDouble(params[3]));
        recipe.setFat(Double.parseDouble(params[4]));
        recipe.setSaturates(Double.parseDouble(params[5]));
        recipe.setCarbohydrates(Double.parseDouble(params[6]));
        recipe.setFibre(Double.parseDouble(params[7]));
        recipe.setPreparation_time(Integer.parseInt(params[8]));
        recipe.setCooking_time(Integer.parseInt(params[9]));
        recipe.setImageLink(Integer.parseInt(params[10]));
        recipe.setDryAndSoreMouth(Boolean.parseBoolean(params[11]));
        recipe.setFeelingSick(Boolean.parseBoolean(params[12]));
        recipe.setProblemsChewing(Boolean.parseBoolean(params[13]));
        recipe.setLossOfOfTasteOrSmell(Boolean.parseBoolean(params[14]));
        recipe.setHealthyEating(Boolean.parseBoolean(params[15]));
        recipe.setVegetarian(Boolean.parseBoolean(params[16]));

        return recipe;

    }
}
