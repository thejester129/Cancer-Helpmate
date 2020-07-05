package com.example.cancerhelpmate.ui.resources;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

public class ResourcesFragment  extends Fragment {
    private RecyclerView resourcesRecycler;
    private ResourcesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_resources, container, false);
        viewModel = new ViewModelProvider(this).get(ResourcesViewModel.class);
        resourcesRecycler = root.findViewById(R.id.resources_recycler_view);
        setupRecycler();
        return root;
    }

    private void setupRecycler(){
        ResourcesAdapter adapter = new ResourcesAdapter( getChildFragmentManager(), resourcesRecycler, viewModel.getResources());
        resourcesRecycler.setAdapter(adapter);
    }

}
