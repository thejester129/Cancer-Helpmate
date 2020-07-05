package com.example.cancerhelpmate.ui.wellbeing.exercise;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDAO;
import com.example.cancerhelpmate.database.wellbeing.WellbeingDatabase;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewModel;

import java.util.ArrayList;
import java.util.List;

public class ExerciseViewModel extends AndroidViewModel {
    private WellbeingDAO wellbeingDAO;

    public ExerciseViewModel(@NonNull Application application) {
        super(application);
        wellbeingDAO = WellbeingDatabase.getDatabase(application).getDAO();
    }


    public List<ExerciseItem> getExercises(){
        List<ExerciseItem> exerciseItems = new ArrayList<>();

        exerciseItems.add(new ExerciseItem("How Exercise Can Help", "There are very good reasons for exercising. It can improve your quality of life and help you feel better. Some studies show that it can help to speed up recovery after cancer treatment. Regular exercise can reduce stress and give you more energy.\n" +
                "               \n " +
                "Exercise has plenty of benefits, such as:\n \n" +
                " • Making you feel better about yourself\n \n" +
                " • Allowing you to get together with friends\n \n" +
                " • Making you feel more energised\n \n" +
                " • Helping with falling asleep easier\n \n" +
                " • Helping you deal with your emotions\n \n" +
                " • Keeping your bones strong\n \n" +
                " • Healing tissue and organs that have been damaged by cancer treatment"));

        exerciseItems.add(new ExerciseItem("Total Body Cardio", "Time-efficient",R.drawable.total_body_cardio_thumbnail,"https://www.youtube.com/watch?v=UYAma0-M6WA"));
        exerciseItems.add(new ExerciseItem("Lower Body Workout", "Improves bone health, can boost metabolism",R.drawable.lower_body_workout_thumbnail,"https://www.youtube.com/watch?v=YpATzLqgw1k"));
        exerciseItems.add(new ExerciseItem("Core and Stability Workout", "Improves everyday tasks, good for back health",R.drawable.core_stability_thumbnail,"https://www.youtube.com/watch?v=PQjeyBIlmzw&feature=emb_title"));
        exerciseItems.add(new ExerciseItem("Stretch and Flexibility Workout", "Fewer injuries, improves posture and balance, greater strength",R.drawable.stretch_flexibility_thumbnail,"https://www.youtube.com/watch?v=q_-ib1emBjI"));

        return exerciseItems;
    }

    public void addStep(){
        wellbeingDAO.setSteps(wellbeingDAO.getItem(DateManager.getTodayAsString()).getSteps_done() + 1, DateManager.getTodayAsString());
    }

    public LiveData<Integer> getTodaysSteps(){
        return wellbeingDAO.getSteps(DateManager.getTodayAsString());
    }
}
