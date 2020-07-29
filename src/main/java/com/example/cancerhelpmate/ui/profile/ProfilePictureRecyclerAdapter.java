package com.example.cancerhelpmate.ui.profile;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.cancerhelpmate.R;

import java.util.List;

import java.util.List;

public class ProfilePictureRecyclerAdapter extends RecyclerView.Adapter<ProfilePictureRecyclerAdapter.ViewHolder>{

    private RecyclerView recyclerView;
    private List<Integer> profilePictures;
    private ProfileViewModel profileViewModel;
    private ProfilePictureDialog profilePictureDialog;

    public ProfilePictureRecyclerAdapter(List<Integer> profilePictures, RecyclerView recyclerView, ProfileViewModel profileViewModel, ProfilePictureDialog profilePictureDialog) {
        this.recyclerView = recyclerView;
        this.profilePictures = profilePictures;
        this.profileViewModel = profileViewModel;
        this.profilePictureDialog = profilePictureDialog;
    }

    @NonNull
    @Override
    public ProfilePictureRecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.profile_picture_recycler_item, parent, false);
        final ProfilePictureRecyclerAdapter adapter = this;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int pos = recyclerView.getChildAdapterPosition(v);
                profileViewModel.setEditProfilePicture(profilePictures.get(pos));
                profilePictureDialog.dismiss();
            }

        });
        ProfilePictureRecyclerAdapter.ViewHolder holder = new ProfilePictureRecyclerAdapter.ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ProfilePictureRecyclerAdapter.ViewHolder holder, int position) {
        holder.image.setImageResource( profilePictures.get(position));
    }

    @Override
    public int getItemCount() {
        return profilePictures.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView image;

        private ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.profile_picture_recycler_item_image);
        }
    }
}