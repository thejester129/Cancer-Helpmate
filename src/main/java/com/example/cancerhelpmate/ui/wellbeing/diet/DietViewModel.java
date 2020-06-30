package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.database.recipes.RecipeDAO;
import com.example.cancerhelpmate.database.recipes.RecipeDatabase;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;

import java.util.ArrayList;
import java.util.List;

public class DietViewModel extends AndroidViewModel {

    private RecipeDAO dao;
    public MutableLiveData<Boolean>dayStatisticsVisible = new MutableLiveData<>(false);
    public MutableLiveData<RecipeEntry>breakfastRecipe = new MutableLiveData<>();
    public MutableLiveData<RecipeEntry>lunchRecipe = new MutableLiveData<>();
    public MutableLiveData<RecipeEntry>dinnerRecipe = new MutableLiveData<>();
    public MutableLiveData<RecipeEntry>supperRecipe = new MutableLiveData<>();
    public MutableLiveData<RecipeEntry>extraRecipe = new MutableLiveData<>();

    public DietViewModel(@NonNull Application application) {
        super(application);
        //TODO store user choices in database
        dao = RecipeDatabase.getDatabase(application).getDAO();
    }

    public void refresh(){
        dayStatisticsVisible.setValue(getDayStatisticsVisible());
    }

    public void resetDay(){
        breakfastRecipe.setValue(null);
        lunchRecipe.setValue(null);
        dinnerRecipe.setValue(null);
        supperRecipe.setValue(null);
        extraRecipe.setValue(null);
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

    private boolean getDayStatisticsVisible(){
        return (breakfastRecipe.getValue() != null) || (lunchRecipe.getValue() != null) || (dinnerRecipe.getValue() != null) || (supperRecipe.getValue() != null);
    }

    public void setBreakfastRecipe(RecipeEntry recipe){
        breakfastRecipe.setValue(recipe);
    }

}
