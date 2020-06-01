package com.example.cancerhelpmate.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.ui.profile.ProfileEditDialog;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import org.jetbrains.annotations.NotNull;

public class HomeFragment extends Fragment {

    private HomeViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private TextView welcomeTextView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);
        getViewModels();
        getReferences(root);
        setupProfileListener();
        return root;
    }

    private void getViewModels(){
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        profileViewModel.getProfile();
    }

    private void getReferences(View view){
        welcomeTextView = view.findViewById(R.id.home_welcome_text);
    }


    private void setupProfileListener(){
        profileViewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry profile) {
                if(profile != null){
                    if( !profile.isInitialised()){
                        DialogFragment dialog = ProfileEditDialog.newInstance(profileViewModel);
                        dialog.show(getParentFragmentManager(), "tag");
                    }
                    else{
                        String welcomeText = "Welcome " + profileViewModel.getProfile().getName();
                        welcomeTextView.setText(welcomeText);
                    }
                }
            }
        });
    }
}
