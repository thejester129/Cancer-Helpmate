package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.app.Application;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
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

import java.util.List;
import java.util.Objects;

public class DietViewModel extends AndroidViewModel {

    private RecipeDAO dao;
    private WellbeingDAO wellbeingDAO;
    public MutableLiveData<Boolean>dayStatisticsVisible = new MutableLiveData<>(false);
    public MutableLiveData<Boolean>exitRecipeBrowser = new MutableLiveData<>(false);
    public MutableLiveData<Double>dailyCalories = new MutableLiveData<>();
    public MutableLiveData<RecipeType>browsingRecipeType = new MutableLiveData<>();

    public DietViewModel(@NonNull Application application) {
        super(application);
        dao = RecipeDatabase.getDatabase(application).getDAO();
        wellbeingDAO = WellbeingDatabase.getDatabase(application).getDAO();
    }

    public void refresh(){
        dayStatisticsVisible.setValue(getDayStatisticsVisible());
        dailyCalories.setValue(getTodaysCalories());
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

    private boolean getDayStatisticsVisible(){
        return (getTodaysBreakfastRecipe() != null) || (getTodaysLunchRecipe() != null) || (getTodaysBreakfastRecipe() != null) || (getTodaysDinnerRecipe() != null)
                || (getTodaysSupperRecipe() != null) || (getTodaysExtraRecipe() != null);
    }

    private double getTodaysCalories(){
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

    public double getRecommendedCalories(){
        //TODO recommended
        return 1800;
    }

    @BindingAdapter({"android:src"})
    public static void setImageViewResource(ImageView imageView, int resource) {
        imageView.setImageResource(resource);
    }

}
