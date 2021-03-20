package com.example.cancerhelpmate.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.checklist.ChecklistEntry;
import com.example.cancerhelpmate.databinding.FragmentChecklistBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChecklistFragment extends Fragment {

    private ChecklistViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentChecklistBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checklist, container, false);
        viewModel = new ViewModelProvider(this).get(ChecklistViewModel.class);
        View view = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupDefaultItems();
        setupRecycler(view);
        createBindings(binding);
        setHasOptionsMenu(true);
        return view;
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.checklist_recycler_view);
        final ChecklistRecyclerAdapter adapter = new ChecklistRecyclerAdapter( recyclerView, viewModel, getContext());
        recyclerView.setAdapter(adapter);
        //touch helper
        ChecklistItemTouchHelperCallback itemTouchHelperCallback = new ChecklistItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        adapter.setItems(viewModel.getItems());
        viewModel.getLiveItems().observe(getViewLifecycleOwner(), new Observer<List<ChecklistEntry>>() {
            @Override
            public void onChanged(@Nullable final List<ChecklistEntry> items) {
                adapter.setItems(items);
            }
        });
    }

    private void createBindings(FragmentChecklistBinding binding) {
        binding.checklistAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = ChecklistEntryDialog.newInstance(viewModel);
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
    }

    private void setupDefaultItems(){
        ChecklistEntry entry1 = viewModel.getItem("Complete today's Day Tracker");
        if(entry1 !=null){
            DayTrackerViewModel dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
            entry1.setChecked(dayTrackerViewModel.getTodaysEntryFilled());
            viewModel.updateEntry(entry1);
        }

        ChecklistEntry entry2 = viewModel.getItem("Enter a meal in Diet section");
        if(entry2 !=null){
            DietViewModel dietViewModel = new ViewModelProvider(this).get(DietViewModel.class);
            entry2.setChecked(dietViewModel.getDayStatisticsVisible());
            viewModel.updateEntry(entry2);
        }
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        menu.findItem(R.id.action_profile).setIcon(profileViewModel.getProfile().getPicture());
    }
}
