package com.example.cancerhelpmate.ui.wellbeing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.recipes.RecipeDAO;
import com.example.cancerhelpmate.database.recipes.RecipeDatabase;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDAO;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDatabase;
import com.example.cancerhelpmate.database.wellbeing.WellbeingEntry;

public class WellbeingViewModel extends AndroidViewModel {
    private WellbeingDAO dao;
    private RecipeDAO recipeDAO;

    public WellbeingViewModel(@NonNull Application application) {
        super(application);
        dao = WellbeingDatabase.getDatabase(application).getDAO();
        recipeDAO = RecipeDatabase.getDatabase(application).getDAO();
        if(getTodaysWellbeingEntry() == null){
            dao.addEntry(new WellbeingEntry());
        }
    }

    public WellbeingEntry getTodaysWellbeingEntry(){
        return dao.getItem(DateManager.getTodayAsString());
    }

    public LiveData<WellbeingEntry> getTodaysLiveWellbeingEntry(){
        return dao.getLiveItem(DateManager.getTodayAsString());
    }

    public void resetDatabase(){
        recipeDAO.deleteTable();
        dao.deleteTable();
    }


}
