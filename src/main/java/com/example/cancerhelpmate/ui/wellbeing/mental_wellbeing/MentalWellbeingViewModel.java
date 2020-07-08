package com.example.cancerhelpmate.ui.wellbeing.mental_wellbeing;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.activities.ActivityItem;

import java.util.ArrayList;
import java.util.List;

public class MentalWellbeingViewModel extends AndroidViewModel {
    public MentalWellbeingViewModel(@NonNull Application application) {
        super(application);
    }

    public List<ActivityItem> getMentalWellbeingActivities(){
        List<ActivityItem> activityItems = new ArrayList<>();

        activityItems.add(new ActivityItem("Breathing Exercise",R.id.nav_breathing_exercise, R.drawable.gradient_breathing_background_menu, R.drawable.breathing_exercise_icon));

        return activityItems;
    }
}
