package com.example.cancerhelpmate.ui.wellbeing.exercise;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.ExerciseInformationDialogBinding;
import com.example.cancerhelpmate.databinding.ExerciseRecyclerItemBinding;


public  class ExerciseInformationDialog extends DialogFragment {
    private ExerciseItem exerciseItem;


    public ExerciseInformationDialog(ExerciseItem exerciseItem) {
        this.exerciseItem = exerciseItem;
    }

    public static ExerciseInformationDialog newInstance(ExerciseItem exerciseItem) {
        return new ExerciseInformationDialog(exerciseItem);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ExerciseInformationDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.exercise_information_dialog, container, false);
        binding.setExercise(exerciseItem);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        ImageButton closeButton = view.findViewById(R.id.diet_recipe_information_close_button);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        return view;
    }

}