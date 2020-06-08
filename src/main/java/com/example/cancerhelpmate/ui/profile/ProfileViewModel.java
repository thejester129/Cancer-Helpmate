package com.example.cancerhelpmate.ui.profile;

import android.app.Application;
import android.os.AsyncTask;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;
import androidx.databinding.ObservableField;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.profile.ProfileEntry;

import org.jetbrains.annotations.NotNull;

import java.util.Observable;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileDAO profileDAO;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> diagnosis = new MutableLiveData<>();
    public MutableLiveData<String> welcomeText = new MutableLiveData<>();
    public MutableLiveData<Integer> currentDay = new MutableLiveData<>();
    public MutableLiveData<Integer> totalDays = new MutableLiveData<>();

    public ProfileViewModel(@NotNull Application application) {
        super(application);
        profileDAO = ProfileDatabase.getDatabase(application).getDAO();
        profileDAO.getProfile();
    }

    public void refresh(){
        name.setValue(getProfile().getName());
        diagnosis.setValue(getProfile().getDiagnosis());
        welcomeText.setValue(getWelcomeText());
        currentDay.setValue(getCurrentDay());
        totalDays.setValue(getTotalDays());
    }

    public LiveData<ProfileEntry> getLiveProfile() {
        return profileDAO.getLiveProfile();
    }

    public ProfileEntry getProfile() {
        return profileDAO.getProfile();
    }

    public void setName(String name) {
        profileDAO.setName(name);
    }

    public void setInitialised() {
        profileDAO.setInitialised(true);
    }

    public void updateTreatment(boolean chemotherapy, boolean radiotherapy, boolean surgery, boolean boneMarrow, boolean other){
        profileDAO.setChemotherapyTreatment(chemotherapy);
        profileDAO.setRadiotherapyTreatment(radiotherapy);
        profileDAO.setSurgeryTreatment(surgery);
        profileDAO.setBoneMarrowTreatment(boneMarrow);
        profileDAO.setOtherTreatment(other);
    }

    public void setDiagnosis(String diagnosis){
        profileDAO.setDiagnosis(diagnosis);
    }

    public void setStartTreatmentDate(String date){
        profileDAO.setStartTreatmentDate(date);
    }

    public void setEndTreatmentDate(String date){
        profileDAO.setEndTreatmentDate(date);
    }

    public int getCurrentDay(){
        return DateManager.noOfDaysBetween(getProfile().getStart_date(), DateManager.getTodayAsString()) + 1;
    }

    public int getTotalDays(){
        return DateManager.noOfDaysBetween(getProfile().getStart_date(), getProfile().getEnd_date()) + 1;
    }

    public int getDaysLeft(){
        return DateManager.noOfDaysBetween(DateManager.getTodayAsString(), getProfile().getEnd_date()) + 1;
    }

    public String getProgressText(){
        if(getDaysLeft() > 0){
            return getDaysLeft() + " days left until end of treatment";
        }
        else{
            //Recovering
            return ((getDaysLeft() * -1) + 1) + " days in recovery";
        }
    }

    public String getWelcomeText(){
        return "Welcome " + getProfile().getName();
    }


    public void resetDatabase() {
        ProfileDatabase.resetDatabase();
    }

}