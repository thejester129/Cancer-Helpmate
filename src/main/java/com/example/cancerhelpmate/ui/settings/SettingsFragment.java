package com.example.cancerhelpmate.ui.settings;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
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
import com.example.cancerhelpmate.ui.activities.ActivitiesViewModel;
import com.example.cancerhelpmate.ui.checklist.ChecklistViewModel;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.home.HomeViewModel;
import com.example.cancerhelpmate.ui.journal.JournalViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileEditDialog;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewModel;

import org.jetbrains.annotations.NotNull;

public class SettingsFragment extends Fragment {
    private SettingsViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentSettingsBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_settings,container,false);
        viewModel = new ViewModelProvider(this).get(SettingsViewModel.class);
        binding.setSettingsViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setupBindings(binding);
        setupObserver(binding);
        setHasOptionsMenu(true);
        return binding.getRoot();
    }

    private void setupBindings(FragmentSettingsBinding binding){
        binding.settingsResetButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                resetApp();
            }
        });
        binding.settingsAcknowledgementsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.toggleAcknowledgements();
            }
        });
    }

    private void setupObserver(final FragmentSettingsBinding binding){
        viewModel.acknowledgementsVisible.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean visible) {
                if(visible){
                    binding.settingsAcknowledgementsDropdown.setImageResource(R.drawable.ic_arrow_up_black);
                    binding.settingsAcknowledgementsDescription.setVisibility(View.VISIBLE);
                }
                else{
                    binding.settingsAcknowledgementsDropdown.setImageResource(R.drawable.ic_arrow_down_black);
                    binding.settingsAcknowledgementsDescription.setVisibility(View.GONE);
                }
            }
        });
    }

    private void resetApp(){
        ChecklistViewModel checklistViewModel = new ViewModelProvider(this).get(ChecklistViewModel.class);
        checklistViewModel.resetDatabase();

        DayTrackerViewModel dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        dayTrackerViewModel.resetDatabase();

        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.resetDatabase();

        WellbeingViewModel wellbeingViewModel = new ViewModelProvider(this).get(WellbeingViewModel.class);
        wellbeingViewModel.resetDatabase();

        viewModel.resetDatabase();

        Toast toast = Toast.makeText(getContext(),"App Reset", Toast.LENGTH_SHORT);
        toast.show();
    }
    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        menu.findItem(R.id.action_profile).setIcon(profileViewModel.getProfile().getPicture());
    }

}