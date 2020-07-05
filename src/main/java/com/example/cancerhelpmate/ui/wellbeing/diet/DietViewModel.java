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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class DietViewModel extends AndroidViewModel {

    private RecipeDAO dao;
    private WellbeingDAO wellbeingDAO;
    public MutableLiveData<Boolean>exitRecipeBrowser = new MutableLiveData<>(false);
    public MutableLiveData<RecipeType>browsingRecipeType = new MutableLiveData<>();
    public MutableLiveData<DietFilterItem> browserFilter = new MutableLiveData<>(new DietFilterItem());


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
        browserFilter.setValue(new DietFilterItem());
    }


    public List<RecipeEntry> getFilteredRecipes(){
        List<RecipeEntry> filteredRecipes = new ArrayList<>();
        filteredRecipes.add(getRecipes().get(0));

        return filteredRecipes;
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

        return calories;
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

        return protein;
    }

    public double getRecommendedCalories(){
        //TODO recommended
        return 1800;
    }

    public double getRecommendedProtein(){
        //TODO recommended
        return 50;
    }

}
