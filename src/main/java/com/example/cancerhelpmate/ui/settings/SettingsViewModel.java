package com.example.cancerhelpmate.ui.settings;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.database.settings.SettingsDAO;
import com.example.cancerhelpmate.database.settings.SettingsDatabase;

public class SettingsViewModel extends AndroidViewModel {
    SettingsDAO settingsDAO;
    public SettingsViewModel(@NonNull Application application) {
        super(application);
        settingsDAO = SettingsDatabase.getDatabase(application).getDAO();
        settingsDAO.getSettings();
    }

    public void resetDatabase() {
        SettingsDatabase.resetDatabase();
    }
}
