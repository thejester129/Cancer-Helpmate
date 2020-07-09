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
import com.example.cancerhelpmate.database.daytracker.DayTrackerWeeklyEntry;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs.DayTrackerGraphSettings;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DayTrackerViewModel extends AndroidViewModel {
    private DayTrackerDAO dao;
    public MutableLiveData<String> sliderPainLevel = new MutableLiveData<>("0");
    public MutableLiveData<DayTrackerGraphSettings> graphSettings = new MutableLiveData<>(new DayTrackerGraphSettings());
    public boolean test;

    public DayTrackerViewModel(@NonNull Application application) {
        super(application);
        dao = DayTrackerDatabase.getDatabase(application).getDAO();
        dao.getDayTrackers();
        initTodaysValues();
        addTestData();
    }

    private void initTodaysValues(){
        if(getTodaysEntryFilled()){
           sliderPainLevel.setValue(getTodaysEntry().getPainLevel() + "");
        }
    }

    public void toggleGraphPainChecked(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.togglePainChecked();
        graphSettings.setValue(settings);
    }

    public void setGraphTimelineLastWeek(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.setGraphModeLastWeek(true);
        graphSettings.setValue(settings);
    }

    public void setGraphTimelineLastMonth(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.setGraphModeLastMonth(true);
        graphSettings.setValue(settings);
    }


    public int getGraphTotalDays(){
        if(graphSettings.getValue().isGraphModeLastWeek()){
            return  7;
        }
        else{
            return  28;
        }
    }

    public DataPoint[] getGraphPainValues(){

        List<DataPoint>painValuesList = new ArrayList<>();

        for(int i = 0; i < getGraphTotalDays(); i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, (-getGraphTotalDays() + i));

            String date = DateManager.parseDateToString(cal);
            if(dao.getDayTracker(date)!=null){
                painValuesList.add(new DataPoint(i+1,dao.getDayTracker(date).getPainLevel()));
            }
        }

        DataPoint[] painPoints = new DataPoint[painValuesList.size()];

        for(int i = 0; i < painValuesList.size(); i ++){
            painPoints[i] = painValuesList.get(i);
        }

        return painPoints;
    }

    private void addTestData(){
        //TODO delete
        if(dao.getDayTrackers().size()==0){
            addEntry(new DayTrackerEntry("01/07/2020",5, new DayTrackerEmotions.DayTrackerEmotionHappy()));
            addEntry(new DayTrackerEntry("02/07/2020",1,new DayTrackerEmotions.DayTrackerEmotionTired()));
            addEntry(new DayTrackerEntry("03/07/2020",3,new DayTrackerEmotions.DayTrackerEmotionSad()));
            addEntry(new DayTrackerEntry("04/07/2020",7,new DayTrackerEmotions.DayTrackerEmotionSad()));
            addEntry(new DayTrackerEntry("05/07/2020",10,new DayTrackerEmotions.DayTrackerEmotionHappy()));
            addEntry(new DayTrackerEntry("06/07/2020",2,new DayTrackerEmotions.DayTrackerEmotionEmotional()));
            addEntry(new DayTrackerEntry("07/07/2020",5,new DayTrackerEmotions.DayTrackerEmotionEnergetic(),"Today was a good day, did some things and some more things after that."));
        }
    }

    public boolean getTodaysEntryFilled(){
        return getTodaysEntry() != null;
    }

    public List<DayTrackerEntry>getDayTrackerEntries(){
        return dao.getDayTrackers();
    }

    public List<DayTrackerWeeklyEntry>getWeeklyDayTrackerEntries(){
        return dao.getWeeklyDayTrackers();
    }

    public LiveData<List<DayTrackerEntry>>getLiveDayTrackerEntries(){
        return dao.getLiveDayTrackers();
    }

    public LiveData<List<DayTrackerWeeklyEntry>>getLiveWeeklyDayTrackerEntries(){
        return dao.getLiveWeeklyDayTrackers();
    }

    public DayTrackerEntry getTodaysEntry(){
        return dao.getDayTracker(DateManager.getTodayAsString());
    }

    public LiveData<DayTrackerEntry> getTodaysLiveEntry(){
        return dao.getLiveDayTracker(DateManager.getTodayAsString());
    }

    public boolean weeklyDayTrackerReady(){
        if(dao.getWeeklyDayTrackers().size() > 0){
            String lastEntryDate = dao.getWeeklyDayTrackers().get(dao.getWeeklyDayTrackers().size() - 1).getDate();
            int daysBetween = DateManager.noOfDaysBetween(lastEntryDate,DateManager.getTodayAsString());
            return (daysBetween > 6);
        }
        return true;
    }

    public void addEntry(DayTrackerEntry dayTrackerEntry){
        dao.addEntry(dayTrackerEntry);
    }

    public void addEntry(DayTrackerWeeklyEntry dayTrackerEntry){
        dao.addEntry(dayTrackerEntry);
    }

    public void updateEntry(DayTrackerEntry dayTrackerEntry){
        dao.updateEntry(dayTrackerEntry);
    }

    public void deleteEntry(DayTrackerEntry dayTrackerEntry){
        dao.deleteEntry(dayTrackerEntry);
    }

    public void resetDatabase(){
        dao.deleteDayTrackersTable();
    }


}
