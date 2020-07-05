package com.example.cancerhelpmate.ui.wellbeing.exercise;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.FragmentExerciseBinding;


public class ExerciseFragment extends Fragment  {

    private ExerciseViewModel viewModel;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentExerciseBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_exercise,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setViewModel(viewModel);
        setupRecycler(view);
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.exercise_recycler_view);
        final ExerciseRecyclerAdapter adapter = new ExerciseRecyclerAdapter(getParentFragmentManager(), recyclerView, viewModel,requireActivity());
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getExercises());
    }


}
