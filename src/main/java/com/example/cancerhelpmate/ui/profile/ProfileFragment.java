package com.example.cancerhelpmate.ui.profile;

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
import com.example.cancerhelpmate.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

public class ProfileFragment extends Fragment {
    private ProfileViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setProfileViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setupBindings(binding);
        setupObserver();
        return binding.getRoot();
    }

    private void setupBindings(FragmentProfileBinding binding){
        binding.profileSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialog = ProfileEditDialog.newInstance();
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
    }

    private void setupObserver( ){
        viewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry profile) {
                viewModel.refresh();
            }
        });
    }
}
