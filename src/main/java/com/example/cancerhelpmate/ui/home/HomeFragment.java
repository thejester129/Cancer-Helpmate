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
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.databinding.FragmentHomeBinding;
import com.example.cancerhelpmate.databinding.HomeDayTrackerLayoutBinding;
import com.example.cancerhelpmate.databinding.HomeWelcomeLayoutBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryEditDialog;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        profileViewModel.refresh();
        setupWelcomeLayout(inflater,container);
        setupDayTrackerLayout(inflater, container);
        setupObserver(binding);
        return view;
    }

    private void setupObserver(final FragmentHomeBinding binding){
        profileViewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry profile) {
                profileViewModel.refresh();
                binding.invalidateAll();
            }
        });
    }

    private void setupWelcomeLayout(LayoutInflater inflater, ViewGroup container) {
        HomeWelcomeLayoutBinding binding = DataBindingUtil.inflate(inflater, R.layout.home_welcome_layout, container, false);
        binding.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
    }


        private void setupDayTrackerLayout(LayoutInflater inflater, ViewGroup container){
        HomeDayTrackerLayoutBinding binding = DataBindingUtil.inflate(inflater,R.layout.home_day_tracker_layout,container,false);
        final DayTrackerViewModel dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        binding.setDayTrackerViewModel(dayTrackerViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());

        binding.dayTrackerPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(dayTrackerViewModel.getTodaysEntryFilled()){
                    DialogFragment dialog = DayTrackerEntryEditDialog.newInstance(dayTrackerViewModel, dayTrackerViewModel.getTodaysEntry());
                    dialog.show(getChildFragmentManager(), "tag");
                }
                else{
                    DialogFragment dialog = DayTrackerEntryEditDialog.newInstance(dayTrackerViewModel);
                    dialog.show(getChildFragmentManager(), "tag");
                }
            }
        });
    }

}
