package com.example.cancerhelpmate.ui.wellbeing.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietViewModel;

public class ExerciseFragment extends Fragment {

    private ExerciseViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_exercise, container, false);
        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);

        return view;
    }
}
