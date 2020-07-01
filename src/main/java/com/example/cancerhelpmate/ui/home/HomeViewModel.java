package com.example.cancerhelpmate.ui.home;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.database.profile.ProfileDatabase;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class HomeViewModel extends AndroidViewModel {
    private ProfileViewModel profileViewModel;
    private enum TimePeriod { MORNING, AFTERNOON, DAY, EVENING }

    public HomeViewModel(@NonNull Application application) {
        super(application);
    }

    public void setProfileViewModel(ProfileViewModel profileViewModel){
        this.profileViewModel = profileViewModel;
    }

    public String getWelcomeText(){
        return getWelcomePrefix() + ", " + profileViewModel.getProfile().getName();
    }

    private String getWelcomePrefix(){
        switch (getTimePeriod()){
            case MORNING:
                return "Good Morning";
            case AFTERNOON:
            case DAY:
                return "Good Afternoon";
            case EVENING:
                return "Good Evening";
            default:
                return "";
        }
    }

    private TimePeriod getTimePeriod(){
        Date date = new Date();
        Calendar calendar = GregorianCalendar.getInstance();
        calendar.setTime(date);
        int hour = calendar.get(Calendar.HOUR_OF_DAY);

        if(hour < 12){
            return TimePeriod.MORNING;
        }
        else if(hour < 15){
            return TimePeriod.AFTERNOON;
        }
        else if(hour < 18){
            return TimePeriod.DAY;
        }
        return TimePeriod.EVENING;
    }


}