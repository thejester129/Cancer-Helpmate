package com.example.cancerhelpmate.ui.daytracker;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.daytracker.DayTrackerDAO;
import com.example.cancerhelpmate.database.daytracker.DayTrackerDatabase;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;

import java.util.List;

public class DayTrackerViewModel extends AndroidViewModel {
    private DayTrackerDAO dao;
    private boolean todaysEntryFilled;
    public MutableLiveData<String> sliderPainLevel = new MutableLiveData<>("0");

    public DayTrackerViewModel(@NonNull Application application) {
        super(application);
        dao = DayTrackerDatabase.getDatabase(application).getDAO();
        dao.getDayTrackers();
        refresh();
        initTodaysValues();
        addTestData();
    }

    private void initTodaysValues(){
        if(todaysEntryFilled){
           sliderPainLevel.setValue(getTodaysEntry().getPainLevel() + "");
        }
    }

    private void addTestData(){
        //TODO delete
        if(dao.getDayTrackers().size()==0){
            addEntry(new DayTrackerEntry("11/04/2020",5));
            addEntry(new DayTrackerEntry("12/04/2020",5));
            addEntry(new DayTrackerEntry("13/04/2020",5));
            addEntry(new DayTrackerEntry("14/04/2020",5));
            addEntry(new DayTrackerEntry("15/04/2020",5));
            addEntry(new DayTrackerEntry("16/04/2020",5));
            addEntry(new DayTrackerEntry("17/04/2020",5));
        }
    }

    public void refresh(){
        todaysEntryFilled = (getTodaysEntry() != null);
    }

    public boolean getTodaysEntryFilled(){
        return todaysEntryFilled;
    }

    public List<DayTrackerEntry>getDayTrackerEntries(){
        return dao.getDayTrackers();
    }

    public LiveData<List<DayTrackerEntry>>getLiveDayTrackerEntries(){
        return dao.getLiveDayTrackers();
    }

    public DayTrackerEntry getTodaysEntry(){
        return dao.getDayTracker(DateManager.getTodayAsString());
    }

    public LiveData<DayTrackerEntry> getTodaysLiveEntry(){
        return dao.getLiveDayTracker(DateManager.getTodayAsString());
    }

    public void addEntry(DayTrackerEntry dayTrackerEntry){
        dao.addEntry(dayTrackerEntry);
    }

    public void updateEntry(DayTrackerEntry dayTrackerEntry){
        dao.updateEntry(dayTrackerEntry);
    }

    public void deleteEntry(DayTrackerEntry dayTrackerEntry){
        dao.deleteEntry(dayTrackerEntry);
    }

    public void resetDatabase(){
        dao.deleteTable();
    }

}
