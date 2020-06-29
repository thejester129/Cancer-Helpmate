package com.example.cancerhelpmate.ui.profile;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.profile.ProfileEntry;

import org.jetbrains.annotations.NotNull;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileDAO dao;
    private ProfileEntry editProfile;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<String> diagnosis = new MutableLiveData<>();
    public MutableLiveData<String> welcomeText = new MutableLiveData<>();
    public MutableLiveData<Integer> currentDay = new MutableLiveData<>();
    public MutableLiveData<Integer> totalDays = new MutableLiveData<>();

    public ProfileViewModel(@NotNull Application application) {
        super(application);
        dao = ProfileDatabase.getDatabase(application).getDAO();
        dao.getProfile();
        refresh();
    }

    public boolean isInRecovery(){
        return (getDaysLeft() <= 0);
    }

    public void refresh(){
        name.setValue(getProfile().getName());
        diagnosis.setValue(getProfile().getDiagnosis());
        welcomeText.setValue(getWelcomeText());
        currentDay.setValue(getCurrentDay());
        totalDays.setValue(getTotalDays());
    }

    public ProfileEntry getEditProfile() {
        return editProfile;
    }

    public void setEditProfile(ProfileEntry editProfile) {
        this.editProfile = editProfile;
    }

    public void updateEditProfile(){
        updateProfile(editProfile);
        setInitialised();
    }

    public boolean editProfileComplete(){
        //TODO checks
        return true;
    }

    public LiveData<ProfileEntry> getLiveProfile() {
        return dao.getLiveProfile();
    }

    public ProfileEntry getProfile() {
        return dao.getProfile();
    }

    public void setName(String name) {
        dao.setName(name);
    }

    public void setInitialised() {
        dao.setInitialised(true);
    }

    public void saveCurrentProfile(){
        updateProfile(editProfile);
    }

    public void updateProfile(ProfileEntry entry){
        dao.updateEntry(entry);
    }

    public void updateTreatment(boolean chemotherapy, boolean radiotherapy, boolean surgery, boolean boneMarrow, boolean other){
        dao.setChemotherapyTreatment(chemotherapy);
        dao.setRadiotherapyTreatment(radiotherapy);
        dao.setSurgeryTreatment(surgery);
        dao.setBoneMarrowTreatment(boneMarrow);
        dao.setOtherTreatment(other);
    }

    public void setDiagnosis(String diagnosis){
        dao.setDiagnosis(diagnosis);
    }

    public void setStartTreatmentDate(String date){
        dao.setStartTreatmentDate(date);
    }

    public void setEndTreatmentDate(String date){
        dao.setEndTreatmentDate(date);
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
        if(!isInRecovery()){
            return getDaysLeft() + " days left until end of treatment";
        }
        else{
            return ((getDaysLeft() * -1) + 1) + " days in recovery";
        }
    }

    public String getWelcomeText(){
        return getWelcomePrefix() + ", " + getProfile().getName();
    }

    private String getWelcomePrefix(){
        long time = System.currentTimeMillis();
        long HOUR = 1000 * 60 * 60;
        long MORNING = 12 * HOUR;
        long DAY = 18 * HOUR;
        String prefix;
        //TODO time periods
        if(time < MORNING){
            return "Good morning";
        }
        else if(time < DAY){
            return"Good afternoon";
        }

        return "Good evening";
    }


    public void resetDatabase() {
        ProfileDatabase.resetDatabase();
    }

}