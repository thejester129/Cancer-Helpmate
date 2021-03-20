package com.example.cancerhelpmate.ui.resources;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;

public class ResourcesTreatmentFragment  extends Fragment {
    private RecyclerView resourcesRecycler;
    private ResourcesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_resources_treatment, container, false);
        viewModel = new ViewModelProvider(this).get(ResourcesViewModel.class);
        resourcesRecycler = root.findViewById(R.id.resources_treatment_recycler_view);
        setupRecycler();
        return root;
    }

    private void setupRecycler(){
        ResourcesAdapter adapter = new ResourcesAdapter( getChildFragmentManager(), resourcesRecycler, viewModel.getTreatmentResources());
        resourcesRecycler.setAdapter(adapter);
    }

}