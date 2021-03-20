package com.example.cancerhelpmate.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.settings.SettingsDAO;
import com.example.cancerhelpmate.database.settings.SettingsDatabase;

public class SettingsViewModel extends AndroidViewModel {
    private SettingsDAO settingsDAO;
    public MutableLiveData<Boolean> acknowledgementsVisible = new MutableLiveData<>(false);

    public SettingsViewModel(@NonNull Application application) {
        super(application);
        settingsDAO = SettingsDatabase.getDatabase(application).getDAO();
        settingsDAO.getSettings();
    }

    public void toggleAcknowledgements(){
        acknowledgementsVisible.setValue(!acknowledgementsVisible.getValue());
    }

    public void resetDatabase() {
        SettingsDatabase.resetDatabase();
    }
}
