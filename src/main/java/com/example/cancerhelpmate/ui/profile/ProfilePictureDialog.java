package com.example.cancerhelpmate.ui.profile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.LinePagerIndicatorDecoration;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfilePictureDialog extends DialogFragment {

    private RecyclerView imageRecycler;
    private ImageView closeButton;
    private CircleImageView profilePicture;
    private ProfileViewModel viewModel;

    public ProfilePictureDialog(ProfileViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static ProfilePictureDialog newInstance(ProfileViewModel viewModel) {
        return new ProfilePictureDialog(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_picture_dialog, container, false);
        imageRecycler = view.findViewById(R.id.profile_picture_recycler_view);
        closeButton = view.findViewById(R.id.profile_picture_dialog_close);
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        initRecycler();
        return view;
    }

    private void initRecycler() {
        ProfilePictureRecyclerAdapter adapter = new ProfilePictureRecyclerAdapter(viewModel.getProfilePictures(), imageRecycler, viewModel, this);
        imageRecycler.setAdapter(adapter);
        imageRecycler.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        imageRecycler.addItemDecoration(new LinePagerIndicatorDecoration());
    }

}