package com.example.cancerhelpmate.ui.wellbeing.mental_wellbeing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietViewModel;

public class MentalWellbeingFragment extends Fragment {
    private MentalWellbeingViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mental_wellbeing, container, false);
        viewModel = new ViewModelProvider(this).get(MentalWellbeingViewModel.class);

        return view;
    }
}
