package com.example.cancerhelpmate.ui.activities;

import androidx.lifecycle.ViewModel;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.activities.quiz.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class ActivitiesViewModel extends ViewModel {

    public List<ActivityItem> getActivities(){
        List<ActivityItem> activityItems = new ArrayList<>();
        activityItems.add(new ActivityItem("Breathing Exercise", R.id.nav_breathing_exercise, R.drawable.gradient_breathing_background_menu, R.drawable.breathing_exercise_icon));
        activityItems.add(new ActivityItem("Quiz", R.id.nav_quiz, R.drawable.secondary_color_gradient, R.drawable.quiz));
        activityItems.add(new ActivityItem("Relaxation Aid", R.id.nav_sleep_aid, R.drawable.sleep_aid_gradient_background_menu, R.drawable.sleep_aid_icon));

        return activityItems;
    }


}
