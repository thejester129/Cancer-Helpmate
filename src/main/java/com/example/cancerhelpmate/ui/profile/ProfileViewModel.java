package com.example.cancerhelpmate.ui.profile;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.profile.ProfileEntry;

import org.jetbrains.annotations.NotNull;

public class ProfileViewModel extends AndroidViewModel {
    private ProfileDAO profileDAO;

    public ProfileViewModel(@NotNull Application application) {
        super(application);
        profileDAO = ProfileDatabase.getDatabase(application).getDAO();
    }

    public LiveData<ProfileEntry> getLiveProfile() {
        return profileDAO.getLiveProfile();
    }


    public ProfileEntry getProfile() {
        return profileDAO.getProfile();
    }


    public void updateProfile(ProfileEntry profileItem) {
        new updateAsyncTask(profileDAO).execute(profileItem);
    }

    public String getName() {
        return getProfile().getName();
    }

    public void setName(String name) {
        profileDAO.setName(name);
        updateProfile(getProfile());
    }

    public void setInitialised() {
        profileDAO.setInitialised(true);
        updateProfile(getProfile());
    }

    public void setChemotherapyTreatment(boolean treatment){
        ProfileEntry profile = getProfile();
        profileDAO.setChemotherapyTreatment(treatment);
        updateProfile(profile);
    }

    public void setRadiotherapyTreatment(boolean treatment){
        ProfileEntry profile = getProfile();
        profileDAO.setRadiotherapyTreatment(treatment);
        updateProfile(profile);
    }

    public void setSurgeryTreatment(boolean treatment){
        ProfileEntry profile = getProfile();
        profileDAO.setSurgeryTreatment(treatment);
        updateProfile(profile);
    }

    public void setBoneMarrowTreatment(boolean treatment){
        ProfileEntry profile = getProfile();
        profileDAO.setBoneMarrowTreatment(treatment);
        updateProfile(profile);
    }

    public void setOtherTreatment(boolean treatment){
        ProfileEntry profile = getProfile();
        profileDAO.setOtherTreatment(treatment);
        updateProfile(profile);
    }

    public void setDiagnosis(String diagnosis){
        ProfileEntry profile = getProfile();
        profileDAO.setDiagnosis(diagnosis);
        updateProfile(profile);
    }

    public String getStartTreatmentDate(){
        return getProfile().getStart_date();
    }

    public String getEndTreatmentDate(){
        return getProfile().getEnd_date();
    }

    public void reset() {
        ProfileDatabase.resetDatabase();
    }


    private static class updateAsyncTask extends AsyncTask<ProfileEntry, Void, Void> {

        private ProfileDAO mAsyncTaskDao;

        updateAsyncTask(ProfileDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final ProfileEntry... params) {
            mAsyncTaskDao.updateEntry(params[0]);
            return null;
        }
    }
}
