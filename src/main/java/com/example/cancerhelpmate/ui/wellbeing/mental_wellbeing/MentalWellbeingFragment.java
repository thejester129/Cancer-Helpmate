package com.example.cancerhelpmate.ui.wellbeing.mental_wellbeing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.activities.ActivityRecyclerAdapter;
import com.example.cancerhelpmate.ui.resources.ResourcesAdapter;

public class MentalWellbeingFragment extends Fragment {
    private MentalWellbeingViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mental_wellbeing, container, false);
        viewModel = new ViewModelProvider(this).get(MentalWellbeingViewModel.class);
        setupRecycler(view);
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.mental_wellbeing_activities_recycler_view);
        final ResourcesAdapter adapter = new ResourcesAdapter(getParentFragmentManager(),recyclerView,viewModel.getMentalResources());
        recyclerView.setAdapter(adapter);
    }
}
