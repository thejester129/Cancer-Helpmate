package com.example.cancerhelpmate.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.database.wellbeing.WellbeingEntry;
import com.example.cancerhelpmate.databinding.DayTrackerTodayPopupBinding;
import com.example.cancerhelpmate.databinding.DietStatsPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentHomeBinding;
import com.example.cancerhelpmate.databinding.HomeActivitiesPopupBinding;
import com.example.cancerhelpmate.databinding.HomeWelcomeLayoutBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryEditDialog;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietStatsDialog;
import com.example.cancerhelpmate.ui.wellbeing.diet.DietViewModel;
import com.example.cancerhelpmate.ui.wellbeing.exercise.ExerciseViewModel;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private DayTrackerViewModel dayTrackerViewModel;
    private DietViewModel dietViewModel;
    private ExerciseViewModel exerciseViewModel;
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        getViewModels();
        viewModel.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        binding.setExerciseViewModel(exerciseViewModel);
        View view = binding.getRoot();
        setupWelcomeLayout();
        setupDayTrackerLayout();
        setupStatsLayout();
        setupActivitiesPopup();
        setupObservers(binding);
        return view;
    }

    private void setupObservers(final FragmentHomeBinding binding){
        profileViewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry profile) {
                profileViewModel.refresh();
                binding.invalidateAll();
            }
        });
    }

    private void setupWelcomeLayout() {
        HomeWelcomeLayoutBinding homeWelcomeLayoutBinding = binding.homeWelcomeLayout;
        homeWelcomeLayoutBinding.setHomeViewModel(viewModel);
        homeWelcomeLayoutBinding.setProfileViewModel(profileViewModel);
        homeWelcomeLayoutBinding.setLifecycleOwner(getViewLifecycleOwner());
        homeWelcomeLayoutBinding.homeWelcomeLayoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) getActivity();
                if(activity!=null){
                    activity.navigateToFrag(R.id.nav_profile);
                }
            }
        });
    }

    private void setupDayTrackerLayout(){
        final DayTrackerTodayPopupBinding homeDayTrackerLayoutBinding = binding.homeDayTrackerLayout;
        homeDayTrackerLayoutBinding.setViewModel(dayTrackerViewModel);
        homeDayTrackerLayoutBinding.setLifecycleOwner(getViewLifecycleOwner());

        homeDayTrackerLayoutBinding.dayTrackerPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) requireActivity();
                activity.navigateToFrag(R.id.nav_day_tracker);
            }
        });

        dayTrackerViewModel.getTodaysLiveEntry().observe(getViewLifecycleOwner(), new Observer<DayTrackerEntry>() {
            @Override
            public void onChanged(@NotNull DayTrackerEntry entry) {
                homeDayTrackerLayoutBinding.invalidateAll();
            }
        });
    }

    private void setupStatsLayout(){
        final DietStatsPopupBinding statsPopupBinding = binding.homeDietStatsPopup;
        statsPopupBinding.setViewModel(dietViewModel);
        statsPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        statsPopupBinding.dietStatsPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) requireActivity();
                activity.navigateToFrag(R.id.nav_wellbeing);
            }
        });
        dietViewModel.getTodaysLiveWellbeingEntry().observe(getViewLifecycleOwner(), new Observer<WellbeingEntry>() {
            @Override
            public void onChanged(WellbeingEntry entry) {
                statsPopupBinding.invalidateAll();
            }
        });
    }

    private void setupActivitiesPopup(){
        final HomeActivitiesPopupBinding statsPopupBinding = binding.homeActivitiesPopup;
        statsPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        statsPopupBinding.activitiesPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity activity = (MainActivity) requireActivity();
                activity.navigateToFrag(R.id.nav_activities);
            }
        });
    }

    private void getViewModels(){
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        dietViewModel = new ViewModelProvider(this).get(DietViewModel.class);
        exerciseViewModel = new ViewModelProvider(this).get(ExerciseViewModel.class);
    }

}
