package com.example.cancerhelpmate.database.wellbeing;

import androidx.room.TypeConverter;

import com.example.cancerhelpmate.database.recipes.RecipeEntry;

public class WellbeingRecipeConverter {
    @TypeConverter
    public static String recipeToString(RecipeEntry recipeEntry) {
        if(recipeEntry != null){
            return recipeEntry.getId() + "/" + recipeEntry.getName() + "/" + recipeEntry.getCalories() + "/" + recipeEntry.getProtein() + "/" + recipeEntry.getFat() + "/" +
                    recipeEntry.getSaturates() + "/" + recipeEntry.getCarbohydrates() + "/" + recipeEntry.getFibre() + "/" + recipeEntry.getInstructions() + "/" + recipeEntry.getPreparation_time() + "/" +
                    recipeEntry.getCooking_time() + "/" + recipeEntry.getImageLink() + "/" + recipeEntry.getVideoLink();
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
        System.out.println("print 1" + string);
        if(params.length == 0){
            return new RecipeEntry();
        }
        System.out.println("print2" + params[0]);

        recipe.setId(Integer.parseInt(params[0]));
        recipe.setName(params[1]);
        recipe.setCalories(Double.parseDouble(params[2]));
        recipe.setProtein(Double.parseDouble(params[3]));
        recipe.setFat(Double.parseDouble(params[4]));
        recipe.setSaturates(Double.parseDouble(params[5]));
        recipe.setCarbohydrates(Double.parseDouble(params[6]));
        recipe.setFibre(Double.parseDouble(params[7]));
        recipe.setInstructions(params[8]);
        recipe.setPreparation_time(Integer.parseInt(params[9]));
        recipe.setCooking_time(Integer.parseInt(params[10]));
        recipe.setImageLink(Integer.parseInt(params[11]));
        recipe.setVideoLink(params[12]);

        return recipe;

    }
}
