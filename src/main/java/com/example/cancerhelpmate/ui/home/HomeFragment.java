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
import com.example.cancerhelpmate.databinding.FragmentHomeBinding;
import com.example.cancerhelpmate.databinding.HomeDayTrackerLayoutBinding;
import com.example.cancerhelpmate.databinding.HomeWelcomeLayoutBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryEditDialog;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {
    private HomeViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private DayTrackerViewModel dayTrackerViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        getViewModels();
        viewModel.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        setupWelcomeLayout(binding);
        setupDayTrackerLayout(binding);
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
        dayTrackerViewModel.getTodaysLiveEntry().observe(getViewLifecycleOwner(), new Observer<DayTrackerEntry>() {
            @Override
            public void onChanged(@NotNull DayTrackerEntry entry) {
                dayTrackerViewModel.refresh();
                binding.invalidateAll();
            }
        });
    }

    private void setupWelcomeLayout(FragmentHomeBinding binding) {
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


    private void setupDayTrackerLayout(FragmentHomeBinding binding){
        HomeDayTrackerLayoutBinding homeDayTrackerLayoutBinding = binding.homeDayTrackerLayout;
        homeDayTrackerLayoutBinding.setDayTrackerViewModel(dayTrackerViewModel);
        homeDayTrackerLayoutBinding.setLifecycleOwner(getViewLifecycleOwner());

        homeDayTrackerLayoutBinding.dayTrackerPopupButton.setOnClickListener(new View.OnClickListener() {
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

    private void getViewModels(){
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        dayTrackerViewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
    }

}
