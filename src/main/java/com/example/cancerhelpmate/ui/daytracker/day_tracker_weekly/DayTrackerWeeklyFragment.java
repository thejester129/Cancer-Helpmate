package com.example.cancerhelpmate.ui.daytracker.day_tracker_weekly;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.database.daytracker.DayTrackerWeeklyEntry;
import com.example.cancerhelpmate.databinding.DayTrackerWeeklyDialogEntryBinding;
import com.example.cancerhelpmate.databinding.DayTrackerWeeklyPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentDayTrackerWeeklyBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_records.DayTrackerRecordsRecyclerAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DayTrackerWeeklyFragment extends Fragment {
    private DayTrackerViewModel viewModel;
    private Button popupButton;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDayTrackerWeeklyBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_tracker_weekly,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getReferences(view);
        createBindings();
        setupObserver(binding);
        setupRecycler(view);
        return view;
    }

    private void setupObserver(final FragmentDayTrackerWeeklyBinding binding){
        viewModel.getLiveDayTrackerEntries().observe(getViewLifecycleOwner(), new Observer<List<DayTrackerEntry>>() {
            @Override
            public void onChanged(List<DayTrackerEntry> dayTrackerEntries) {
                binding.dayTrackerWeeklyPopup.getRoot().setVisibility(viewModel.weeklyDayTrackerReady() ? View.VISIBLE : View.GONE);
            }
        });
    }


    private void getReferences(View view){
        popupButton = view.findViewById(R.id.day_tracker_weekly_popup_button);
    }

    private void createBindings(){
        popupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = new DayTrackerWeeklyEntryDialog(viewModel);
                dialog.show(getParentFragmentManager(),"tag");
            }
        });
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.day_tracker_weekly_recycler);
        final DayTrackerWeeklyRecyclerAdapter adapter = new DayTrackerWeeklyRecyclerAdapter(getParentFragmentManager(), recyclerView, viewModel, view);
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getWeeklyDayTrackerEntries());
        viewModel.getLiveWeeklyDayTrackerEntries().observe(getViewLifecycleOwner(), new Observer<List<DayTrackerWeeklyEntry>>() {
            @Override
            public void onChanged(@NotNull List<DayTrackerWeeklyEntry> entries) {
                adapter.setItems(entries);
            }
        });
    }


}
