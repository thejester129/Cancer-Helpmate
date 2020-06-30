package com.example.cancerhelpmate.ui.daytracker;

import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.FragmentDaytrackerBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DayTrackerFragment extends Fragment {
    private DayTrackerViewModel viewModel;
    private DayTrackerRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDaytrackerBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_daytracker,container,false);
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        View view = binding.getRoot();
        binding.setDayTrackerViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupRecycler(view);
        setupObserver();
        return view;
    }

    private void setupObserver( ){
        viewModel.getLiveDayTrackerEntries().observe(getViewLifecycleOwner(), new Observer<List<DayTrackerEntry>>() {
            @Override
            public void onChanged(@NotNull List<DayTrackerEntry> entries) {
                viewModel.refresh();
                adapter.setItems(entries);
            }
        });

        viewModel.getTodaysLiveEntry().observe(getViewLifecycleOwner(), new Observer<DayTrackerEntry>() {
            @Override
            public void onChanged(@NotNull DayTrackerEntry entry) {
                refreshRecyclerView();
            }
        });
    }

    private void refreshRecyclerView(){
        recyclerView.setAdapter(adapter);
    }


    private void setupRecycler(View view){
        recyclerView = view.findViewById(R.id.day_tracker_recycler_view);
        adapter = new DayTrackerRecyclerAdapter(getParentFragmentManager(), recyclerView, viewModel, view);
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getDayTrackerEntries());
    }
}
