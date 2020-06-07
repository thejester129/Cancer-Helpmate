package com.example.cancerhelpmate.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.databinding.FragmentHomeBinding;
import com.example.cancerhelpmate.ui.profile.ProfileEditDialog;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private ProfileViewModel profileViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentHomeBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setProfileViewModel(profileViewModel);
        binding.setLifecycleOwner(this);
        setupObserver();
        return binding.getRoot();
    }

    private void setupObserver( ){
        profileViewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry profile) {
                profileViewModel.refresh();
            }
        });
    }

}
