package com.example.cancerhelpmate.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.databinding.FragmentProfileBinding;
import com.example.cancerhelpmate.databinding.FragmentSettingsBinding;
import com.example.cancerhelpmate.ui.checklist.ChecklistViewModel;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.home.HomeViewModel;
import com.example.cancerhelpmate.ui.journal.JournalViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileEditDialog;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment {
    private SettingsViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSettingsBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container,false);
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding.setSettingsViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setupBindings(binding);
        setupObserver();
        return binding.getRoot();
    }

    private void setupBindings(FragmentSettingsBinding binding){
        binding.settingsResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetApp();
            }
        });
    }

    private void setupObserver( ){
        //TODO observer
    }

    private void resetApp(){
        ChecklistViewModel checklistViewModel = new ViewModelProvider(this).get(ChecklistViewModel.class);
        checklistViewModel.resetDatabase();

        DayTrackerViewModel dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        dayTrackerViewModel.resetDatabase();

        JournalViewModel journalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
        journalViewModel.resetDatabase();

        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.resetDatabase();

        viewModel.resetDatabase();

        Toast toast = Toast.makeText(getContext(),"App Reset", Toast.LENGTH_SHORT);
        toast.show();
    }
}