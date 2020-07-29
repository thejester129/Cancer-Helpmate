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
import com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool.DayTrackerBodyWord;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool.DayTrackerHeaderWord;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;
import com.jjoe64.graphview.series.DataPoint;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class DayTrackerViewModel extends AndroidViewModel {
    private DayTrackerDAO dao;
    public MutableLiveData<String> sliderPainLevel = new MutableLiveData<>("0");
    public MutableLiveData<String> sliderFatigueLevel = new MutableLiveData<>("0");
    public MutableLiveData<String> sliderAppetiteLevel = new MutableLiveData<>("0");
    public MutableLiveData<DayTrackerGraphSettings> graphSettings = new MutableLiveData<>(new DayTrackerGraphSettings());

    public DayTrackerViewModel(@NonNull Application application) {
        super(application);
        dao = DayTrackerDatabase.getDatabase(application).getDAO();
        dao.getDayTrackers();
        initTodaysValues();
    }

    private void initTodaysValues(){
        if(getTodaysEntryFilled()){
           sliderPainLevel.setValue(getTodaysEntry().getPainLevel() + "");
           sliderFatigueLevel.setValue(getTodaysEntry().getFatigueLevel() + "");
           sliderAppetiteLevel.setValue(getTodaysEntry().getAppetiteLevel() + "");
        }
    }

    public void toggleTodayTreatment(){
        dao.setTreatment(!getTodaysEntry().isTreatment());
    }

    public void toggleGraphPainChecked(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.togglePainChecked();
        graphSettings.setValue(settings);
    }

    public void toggleGraphFatigueChecked(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.toggleFatigueChecked();
        graphSettings.setValue(settings);
    }

    public void toggleGraphAppetiteChecked(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.toggleAppetiteChecked();
        graphSettings.setValue(settings);
    }

    public void toggleGraphTreatmentChecked(){
        DayTrackerGraphSettings settings = graphSettings.getValue();
        settings.toggleTreatmentChecked();
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

        for(int i = 1; i <= getGraphTotalDays(); i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, (-getGraphTotalDays() + i));

            String date = DateManager.parseDateToString(cal);
            if(dao.getDayTracker(date)!=null){
                painValuesList.add(new DataPoint(i,dao.getDayTracker(date).getPainLevel()));
            }
        }

        DataPoint[] painPoints = new DataPoint[painValuesList.size()];

        for(int i = 0; i < painValuesList.size(); i ++){
            painPoints[i] = painValuesList.get(i);
        }

        return painPoints;
    }

    public DataPoint[] getGraphFatigueValues(){
        List<DataPoint>fatigueValuesList = new ArrayList<>();

        for(int i = 1; i <= getGraphTotalDays(); i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, (-getGraphTotalDays() + i));

            String date = DateManager.parseDateToString(cal);
            if(dao.getDayTracker(date)!=null){
                fatigueValuesList.add(new DataPoint(i,dao.getDayTracker(date).getFatigueLevel()));
            }
        }

        DataPoint[] fatiguePoints = new DataPoint[fatigueValuesList.size()];

        for(int i = 0; i < fatigueValuesList.size(); i ++){
            fatiguePoints[i] = fatigueValuesList.get(i);
        }

        return fatiguePoints;
    }

    public DataPoint[] getGraphAppetiteValues(){
        List<DataPoint>valuesList = new ArrayList<>();

        for(int i = 1; i <= getGraphTotalDays(); i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, (-getGraphTotalDays() + i));

            String date = DateManager.parseDateToString(cal);
            if(dao.getDayTracker(date)!=null){
                valuesList.add(new DataPoint(i,dao.getDayTracker(date).getAppetiteLevel()));
            }
        }

        DataPoint[] points = new DataPoint[valuesList.size()];

        for(int i = 0; i < valuesList.size(); i ++){
            points[i] = valuesList.get(i);
        }

        return points;
    }

    public DataPoint[] getGraphTreatmentValues(){
        final int TREATMENT_VALUE = 10;
        List<DataPoint>valuesList = new ArrayList<>();

        for(int i = 1; i <= getGraphTotalDays(); i++){
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, (-getGraphTotalDays() + i));

            String date = DateManager.parseDateToString(cal);
            if(dao.getDayTracker(date)!=null){
                if(dao.getDayTracker(date).isTreatment()){
                    valuesList.add(new DataPoint(i,TREATMENT_VALUE));
                }
                else{
                    valuesList.add(new DataPoint(i,0));
                }
            }
            else{
                valuesList.add(new DataPoint(i,0));
            }
        }

        DataPoint[] points = new DataPoint[valuesList.size()];

        for(int i = 0; i < valuesList.size(); i ++){
            points[i] = valuesList.get(i);
        }

        return points;
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

    private void addTestData(){
        //TODO delete
        if(dao.getDayTrackers().size()==0){
            addEntry(new DayTrackerEntry("11/07/2020",5,5,5, new DayTrackerEmotions.DayTrackerEmotionHappy(),false));
            addEntry(new DayTrackerEntry("12/07/2020",1,2,10,new DayTrackerEmotions.DayTrackerEmotionTired(),false));
            addEntry(new DayTrackerEntry("13/07/2020",3,5,8,new DayTrackerEmotions.DayTrackerEmotionSad(),true));
            addEntry(new DayTrackerEntry("14/07/2020",7,1,3,new DayTrackerEmotions.DayTrackerEmotionSad(),false));
            addEntry(new DayTrackerEntry("15/07/2020",10,7,1,new DayTrackerEmotions.DayTrackerEmotionHappy(),true));
            addEntry(new DayTrackerEntry("16/07/2020",2,3,7,new DayTrackerEmotions.DayTrackerEmotionEmotional(),false));
        }
    }

    public List<DayTrackerHeaderWord> getHeaderWords(){
        List<DayTrackerHeaderWord>words = new ArrayList<>();

        words.add(new DayTrackerHeaderWord("I feel", new ArrayList<DayTrackerBodyWord>(){{
            add(new DayTrackerBodyWord("happy", "I feel"));
            add(new DayTrackerBodyWord("okay", "I feel"));
            add(new DayTrackerBodyWord("motivated", "I feel"));
            add(new DayTrackerBodyWord("thankful", "I feel"));
            add(new DayTrackerBodyWord("relaxed", "I feel"));
            add(new DayTrackerBodyWord("tired", "I feel"));
            add(new DayTrackerBodyWord("bored", "I feel"));
            add(new DayTrackerBodyWord("overwhelmed", "I feel"));
            add(new DayTrackerBodyWord("depressed", "I feel"));
            add(new DayTrackerBodyWord("lonely", "I feel"));
            add(new DayTrackerBodyWord("angry", "I feel"));
        }}));

        words.add(new DayTrackerHeaderWord("I did", new ArrayList<DayTrackerBodyWord>(){{
            add(new DayTrackerBodyWord("some exercise", "I did"));
            add(new DayTrackerBodyWord("homework", "I did"));
            add(new DayTrackerBodyWord("treatment", "I did"));
            add(new DayTrackerBodyWord("not that much", "I did"));
            add(new DayTrackerBodyWord("nothing", "I did"));
        }}));

        words.add(new DayTrackerHeaderWord("I went", new ArrayList<DayTrackerBodyWord>(){{
            add(new DayTrackerBodyWord("for a walk", "I went"));
            add(new DayTrackerBodyWord("jogging", "I went"));
            add(new DayTrackerBodyWord("shopping", "I went"));
            add(new DayTrackerBodyWord("out", "I went"));
            add(new DayTrackerBodyWord("to meet friends", "I went"));
            add(new DayTrackerBodyWord("to a meeting", "I went"));
        }}));

        words.add(new DayTrackerHeaderWord("I ate", new ArrayList<DayTrackerBodyWord>(){{
            add(new DayTrackerBodyWord("a lot", "I ate"));
            add(new DayTrackerBodyWord("not much", "I ate"));
            add(new DayTrackerBodyWord("a good amount", "I ate"));
            add(new DayTrackerBodyWord("healthy foods", "I ate"));
            add(new DayTrackerBodyWord("junk", "I ate"));
        }}));

        words.add(new DayTrackerHeaderWord("I slept", new ArrayList<DayTrackerBodyWord>(){{
            add(new DayTrackerBodyWord("a lot", "I slept"));
            add(new DayTrackerBodyWord("well", "I slept"));
            add(new DayTrackerBodyWord("not that much", "I slept"));
            add(new DayTrackerBodyWord("badly", "I slept"));
        }}));


        return words;
    }

    public void resetDatabase(){
        dao.deleteDayTrackersTable();
    }


}
