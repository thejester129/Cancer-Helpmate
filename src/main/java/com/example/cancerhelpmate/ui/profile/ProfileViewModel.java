package com.example.cancerhelpmate.ui.profile;

import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.common.ImageManager;
import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.profile.ProfileEntry;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileDAO dao;
    private ProfileEntry editProfile;
    public MutableLiveData<String> name = new MutableLiveData<>();
    public MutableLiveData<Integer> editPicture = new MutableLiveData<>();
    public MutableLiveData<String> diagnosis = new MutableLiveData<>();
    public MutableLiveData<Integer> currentDay = new MutableLiveData<>();
    public MutableLiveData<Integer> totalDays = new MutableLiveData<>();

    public ProfileViewModel(@NotNull Application application) {
        super(application);
        dao = ProfileDatabase.getDatabase(application).getDAO();
        editProfile = dao.getProfile();
        refresh();
    }

    public boolean isInRecovery(){
        return (getDaysLeft() <= 0);
    }

    public void refresh(){
        name.setValue(getProfile().getFirstName()+" "+getProfile().getSecondName());
        diagnosis.setValue(getProfile().getDiagnosis());
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

    public void setEditProfilePicture(int picture){
        editProfile.setPicture(picture);
        editPicture.setValue(picture);
    }

    public void setMale(){
        dao.setMale(true);
        dao.setFemale(false);
        dao.setOther(false);
    }

    public void setFemale(){
        dao.setMale(false);
        dao.setFemale(true);
        dao.setOther(false);
    }

    public void setOther(){
        dao.setMale(false);
        dao.setFemale(false);
        dao.setOther(true);
    }

    public LiveData<ProfileEntry> getLiveProfile() {
        return dao.getLiveProfile();
    }

    public LiveData<Integer> getLivePicture() {
        return dao.getLivePicture();
    }

    public LiveData<Boolean>getLiveIsInitialised(){
        return dao.getLiveInitialised();
    }

    public ProfileEntry getProfile() {
        return dao.getProfile();
    }

    public void setFirstName(String name) {
        dao.setFirstName(name);
    }

    public void setSecondName(String name) {
        dao.setSecondName(name);
    }

    public void setInitialised() {
        dao.setInitialised(true);
    }

    public void setEditProfileDiagnosis(String diagnosis){
        editProfile.setDiagnosis(diagnosis);
    }

    public void updateProfile(ProfileEntry entry){
        dao.updateEntry(entry);
    }


    public int getCurrentDay(){
        return DateManager.noOfDaysBetween(getProfile().getStart_date(), DateManager.getTodayAsString());
    }

    public int getTotalDays(){
        return DateManager.noOfDaysBetween(getProfile().getStart_date(), getProfile().getEnd_date()) ;
    }

    public int getDaysLeft(){
        return DateManager.noOfDaysBetween(DateManager.getTodayAsString(), getProfile().getEnd_date());
    }

    public String getProgressText(){
        if(!isInRecovery()){
            if(getDaysLeft() > 1){
                return getDaysLeft() + " days left until end of treatment";
            }
            else {
                return getDaysLeft() + " day left until end of treatment";
            }

        }
        else{
            int daysInRecovery = (getDaysLeft() * -1) + 1;
            if(daysInRecovery > 1){
                return ((getDaysLeft() * -1) + 1) + " days in recovery";
            }
            else{
                return ((getDaysLeft() * -1) + 1) + " day in recovery";
            }
        }
    }

    public List<Integer> getProfilePictures(){
        List<Integer>pictures = new ArrayList<>();
        pictures.add(R.drawable.profile_pic_1);
        pictures.add(R.drawable.profile_pic_3);
        pictures.add(R.drawable.profile_pic_2);
        pictures.add(R.drawable.profile_pic_4);
        pictures.add(R.drawable.profile_pic_5);
        pictures.add(R.drawable.profile_pic_6);

        return pictures;
    }

    public void resetDatabase() {
        ProfileDatabase.resetDatabase();
    }

}