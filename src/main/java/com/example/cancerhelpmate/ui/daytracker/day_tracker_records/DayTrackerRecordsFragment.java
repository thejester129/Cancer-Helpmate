package com.example.cancerhelpmate.ui.daytracker.day_tracker_records;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DayTrackerRecordsFragment extends Fragment {

    private DayTrackerViewModel viewModel;
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_tracker_records, container, false);
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        setupRecycler(view);
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.day_tracker_recycler_view);
        final DayTrackerRecordsRecyclerAdapter adapter = new DayTrackerRecordsRecyclerAdapter(getParentFragmentManager(), recyclerView, viewModel, view);
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getDayTrackerEntries());
        viewModel.getLiveDayTrackerEntries().observe(getViewLifecycleOwner(), new Observer<List<DayTrackerEntry>>() {
            @Override
            public void onChanged(@NotNull List<DayTrackerEntry> entries) {
                adapter.setItems(entries);
            }
        });
    }
}