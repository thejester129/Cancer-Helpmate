package com.example.cancerhelpmate.ui.profile;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.ImageManager;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.databinding.FragmentProfileBinding;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

public class ProfileFragment extends Fragment {
    private ProfileViewModel viewModel;
    public static final int GET_FROM_GALLERY = 3;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentProfileBinding binding = DataBindingUtil.inflate(inflater,R.layout.fragment_profile,container,false);
        viewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        View view = binding.getRoot();
        binding.setProfileViewModel(viewModel);
        binding.setLifecycleOwner(this);
        setupBindings(binding);
        setupObserver(binding);
        setHasOptionsMenu(true);
        return view;
    }

    private void setupBindings(FragmentProfileBinding binding){
        binding.profileSettingsButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialog = ProfileEditDialog.newInstance(viewModel);
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
        binding.profileUserImage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI), GET_FROM_GALLERY);

            }
        });
    }

    private void setupObserver(final FragmentProfileBinding binding){
        viewModel.getLiveProfile().observe(getViewLifecycleOwner(), new Observer<ProfileEntry>() {
            @Override
            public void onChanged(@NotNull ProfileEntry entry) {
                viewModel.refresh();
                binding.invalidateAll();
            }
        });
        viewModel.getLivePicture().observe(getViewLifecycleOwner(), new Observer<byte []>() {
            @Override
            public void onChanged(@NotNull byte [] picture) {
                if(picture != null){
                    //TODO set image
                }
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //Detects request codes
        if(requestCode==GET_FROM_GALLERY && resultCode == Activity.RESULT_OK) {
            Uri selectedImage = data.getData();
            String path = selectedImage.getPath();
            Bitmap bitmap = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(requireContext().getContentResolver(), selectedImage);
                byte[] image = ImageManager.bitmapToByteArray(bitmap);
                viewModel.setProfilePicture(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_profile).setVisible(false);
    }

}
