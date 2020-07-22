package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.recipes.RecipeDAO;
import com.example.cancerhelpmate.database.recipes.RecipeDatabase;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDAO;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDatabase;
import com.example.cancerhelpmate.database.wellbeing.WellbeingEntry;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs.DayTrackerGraphSettings;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietBrowserFilterSettings;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietFilterRecipeType;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DietViewModel extends AndroidViewModel {

    private RecipeDAO dao;
    private WellbeingDAO wellbeingDAO;
    public MutableLiveData<Boolean>exitRecipeBrowser = new MutableLiveData<>(false);
    public MutableLiveData<DietRecipeType>browsingRecipeType = new MutableLiveData<>();
    public MutableLiveData<DietBrowserFilterSettings>browserFilterSettings = new MutableLiveData<>(new DietBrowserFilterSettings());


    public DietViewModel(@NonNull Application application) {
        super(application);
        dao = RecipeDatabase.getDatabase(application).getDAO();
        wellbeingDAO = WellbeingDatabase.getDatabase(application).getDAO();
    }

    public List<RecipeEntry> getRecipes(){
        return  dao.getAllItems();
    }

    public LiveData<List<RecipeEntry>> getLiveRecipes(){
        return  dao.getAllLiveItems();
    }

    public void addRecipe(RecipeEntry recipeEntry){
        dao.addEntry(recipeEntry);
    }

    public void setBrowsingRecipe(RecipeEntry recipe){
        String todayString = DateManager.getTodayAsString();
        switch (Objects.requireNonNull(browsingRecipeType.getValue())){
            case BREAKFAST:
                wellbeingDAO.setBreakfastRecipe(recipe,todayString);
                break;
            case LUNCH:
                wellbeingDAO.setLunchRecipe(recipe,todayString);
                break;
            case DINNER:
                wellbeingDAO.setDinnerRecipe(recipe,todayString);
                break;
            case SUPPER:
                wellbeingDAO.setSupperRecipe(recipe,todayString);
                break;
            case EXTRA:
                wellbeingDAO.setExtraRecipe(recipe,todayString);
                break;
        }
    }

    public void resetBrowserFilter(){
        browserFilterSettings.setValue(new DietBrowserFilterSettings());
    }

    public List<RecipeEntry> getFilteredRecipes(){

        List<RecipeEntry>recipes = new ArrayList<>();

        switch (browserFilterSettings.getValue().getDietFilterRecipeType()){
            case ANY:
                recipes = getRecipes();
                break;
            case MAIN:
                recipes = getMainRecipes();
                break;
            case DRINKS:
                recipes = getDrinkRecipes();
                break;
            case DESSERTS:
                recipes = getDessertRecipes();
                break;
            case STARTERS:
                recipes = getStarterRecipes();
                break;
        }

        List<RecipeEntry> filteredRecipes = new ArrayList<>(recipes);

        for(RecipeEntry recipe : recipes){
            if(browserFilterSettings.getValue().isDryAndSoreMouth() && !recipe.isDryAndSoreMouth()){
                filteredRecipes.remove(recipe);
            }
            if(browserFilterSettings.getValue().isProblemsChewing() && !recipe.isProblemsChewing()){
                filteredRecipes.remove(recipe);
            }
            if(browserFilterSettings.getValue().isLossOfOfTasteOrSmell() && !recipe.isLossOfOfTasteOrSmell()){
                filteredRecipes.remove(recipe);
            }
            if(browserFilterSettings.getValue().isFeelingSick() && !recipe.isFeelingSick()){
                filteredRecipes.remove(recipe);
            }
            if(browserFilterSettings.getValue().isHealthyEating() && !recipe.isHealthyEating()){
                filteredRecipes.remove(recipe);
            }
            if(browserFilterSettings.getValue().isVegetarian() && !recipe.isVegetarian()){
                filteredRecipes.remove(recipe);
            }
        }

        return filteredRecipes;
    }

    private List<RecipeEntry> getMainRecipes(){
        List<RecipeEntry>recipes = new ArrayList<>();

        for(RecipeEntry recipe : getRecipes()){
            if(recipe.getDietFilterRecipeType() == DietFilterRecipeType.MAIN){
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private List<RecipeEntry> getDrinkRecipes(){
        List<RecipeEntry>recipes = new ArrayList<>();

        for(RecipeEntry recipe : getRecipes()){
            if(recipe.getDietFilterRecipeType() == DietFilterRecipeType.DRINKS){
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private List<RecipeEntry> getDessertRecipes(){
        List<RecipeEntry>recipes = new ArrayList<>();

        for(RecipeEntry recipe : getRecipes()){
            if(recipe.getDietFilterRecipeType() == DietFilterRecipeType.DESSERTS){
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    private List<RecipeEntry> getStarterRecipes(){
        List<RecipeEntry>recipes = new ArrayList<>();

        for(RecipeEntry recipe : getRecipes()){
            if(recipe.getDietFilterRecipeType() == DietFilterRecipeType.STARTERS){
                recipes.add(recipe);
            }
        }
        return recipes;
    }

    public RecipeEntry getTodaysBreakfastRecipe(){
        return wellbeingDAO.getItem(DateManager.getTodayAsString()).getBreakfast_recipe();
    }

    public RecipeEntry getTodaysLunchRecipe(){
        return wellbeingDAO.getItem(DateManager.getTodayAsString()).getLunch_recipe();
    }

    public RecipeEntry getTodaysDinnerRecipe(){
        return wellbeingDAO.getItem(DateManager.getTodayAsString()).getDinner_recipe();
    }

    public RecipeEntry getTodaysSupperRecipe(){
        return wellbeingDAO.getItem(DateManager.getTodayAsString()).getSupper_recipe();
    }

    public RecipeEntry getTodaysExtraRecipe(){
        return wellbeingDAO.getItem(DateManager.getTodayAsString()).getExtra_recipe();
    }

    public LiveData<WellbeingEntry> getTodaysLiveWellbeingEntry(){
        return wellbeingDAO.getLiveItem(DateManager.getTodayAsString());
    }

    public LiveData<RecipeEntry> getTodaysLiveBreakfastRecipe(){
        return wellbeingDAO.getLiveBreakfastRecipe(DateManager.getTodayAsString());
    }

    public LiveData<RecipeEntry> getTodaysLiveLunchRecipe(){
        return wellbeingDAO.getLiveLunchRecipe(DateManager.getTodayAsString());
    }

    public LiveData<RecipeEntry> getTodaysLiveDinnerRecipe(){
        return wellbeingDAO.getLiveDinnerRecipe(DateManager.getTodayAsString());
    }

    public LiveData<RecipeEntry> getTodaysLiveSupperRecipe(){
        return wellbeingDAO.getLiveSupperRecipe(DateManager.getTodayAsString());
    }

    public LiveData<RecipeEntry> getTodaysLiveExtraRecipe(){
        return wellbeingDAO.getLiveExtraRecipe(DateManager.getTodayAsString());
    }

    public void setTodaysBreakfastRecipe(RecipeEntry recipe){
        wellbeingDAO.setBreakfastRecipe(recipe, DateManager.getTodayAsString());
    }

    public boolean getDayStatisticsVisible(){
        return (getTodaysBreakfastRecipe() != null) || (getTodaysLunchRecipe() != null) || (getTodaysBreakfastRecipe() != null) || (getTodaysDinnerRecipe() != null)
                || (getTodaysSupperRecipe() != null) || (getTodaysExtraRecipe() != null);
    }

    public double getTodaysCalories(){
        double calories = 0;
        if(getTodaysBreakfastRecipe()!=null){
            calories += getTodaysBreakfastRecipe().getCalories();
        }
        if(getTodaysLunchRecipe()!=null){
            calories += getTodaysLunchRecipe().getCalories();
        }
        if(getTodaysDinnerRecipe()!=null){
            calories += getTodaysDinnerRecipe().getCalories();
        }
        if(getTodaysSupperRecipe()!=null){
            calories += getTodaysSupperRecipe().getCalories();
        }
        if(getTodaysExtraRecipe()!=null){
            calories += getTodaysExtraRecipe().getCalories();
        }

        return round(calories);
    }

    public double getTodaysProtein(){
        double protein = 0;
        if(getTodaysBreakfastRecipe()!=null){
            protein += getTodaysBreakfastRecipe().getProtein();
        }
        if(getTodaysLunchRecipe()!=null){
            protein += getTodaysLunchRecipe().getProtein();
        }
        if(getTodaysDinnerRecipe()!=null){
            protein += getTodaysDinnerRecipe().getProtein();
        }
        if(getTodaysSupperRecipe()!=null){
            protein += getTodaysSupperRecipe().getProtein();
        }
        if(getTodaysExtraRecipe()!=null){
            protein += getTodaysExtraRecipe().getProtein();
        }

        return round(protein);
    }

    public double getTodaysFat(){
        double total = 0;
        if(getTodaysBreakfastRecipe()!=null){
            total += getTodaysBreakfastRecipe().getFat();
        }
        if(getTodaysLunchRecipe()!=null){
            total += getTodaysLunchRecipe().getFat();
        }
        if(getTodaysDinnerRecipe()!=null){
            total += getTodaysDinnerRecipe().getFat();
        }
        if(getTodaysSupperRecipe()!=null){
            total += getTodaysSupperRecipe().getFat();
        }
        if(getTodaysExtraRecipe()!=null){
            total += getTodaysExtraRecipe().getFat();
        }

        return round(total);
    }
    public double getTodaysCarbohydrates(){
        double total = 0;
        if(getTodaysBreakfastRecipe()!=null){
            total += getTodaysBreakfastRecipe().getCarbohydrates();
        }
        if(getTodaysLunchRecipe()!=null){
            total += getTodaysLunchRecipe().getCarbohydrates();
        }
        if(getTodaysDinnerRecipe()!=null){
            total += getTodaysDinnerRecipe().getCarbohydrates();
        }
        if(getTodaysSupperRecipe()!=null){
            total += getTodaysSupperRecipe().getCarbohydrates();
        }
        if(getTodaysExtraRecipe()!=null){
            total += getTodaysExtraRecipe().getCarbohydrates();
        }

        return round(total);
    }

    public double getTodaysFibre(){
        double total = 0;
        if(getTodaysBreakfastRecipe()!=null){
            total += getTodaysBreakfastRecipe().getFibre();
        }
        if(getTodaysLunchRecipe()!=null){
            total += getTodaysLunchRecipe().getFibre();
        }
        if(getTodaysDinnerRecipe()!=null){
            total += getTodaysDinnerRecipe().getFibre();
        }
        if(getTodaysSupperRecipe()!=null){
            total += getTodaysSupperRecipe().getFibre();
        }
        if(getTodaysExtraRecipe()!=null){
            total += getTodaysExtraRecipe().getFibre();
        }

        return round(total);
    }

    public static double round(double value) {
        int places = 1;

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public double getRecommendedCalories(){
        //TODO recommended
        return 1800;
    }

    public double getRecommendedProtein(){
        //TODO recommended
        return 50;
    }

    public double getRecommendedFat(){
        //TODO recommended
        return 70;
    }

    public double getRecommendedCarbohydrates(){
        //TODO recommended
        return 260;
    }

    public double getRecommendedFibre(){
        //TODO recommended
        return 25;
    }

    public void setFilterCategory(DietFilterRecipeType type){
        DietBrowserFilterSettings settings = browserFilterSettings.getValue();
        settings.setDietFilterRecipeType(type);
        browserFilterSettings.setValue(settings);
    }

}
