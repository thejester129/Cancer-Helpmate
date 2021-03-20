package com.example.cancerhelpmate.ui.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

public class ActivitiesFragment extends Fragment {
    private ActivitiesViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mental_wellbeing, container, false);
        viewModel = new ViewModelProvider(this).get(ActivitiesViewModel.class);
        setupRecycler(view);
        setHasOptionsMenu(true);
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.mental_wellbeing_activities_recycler_view);
        final ActivityRecyclerAdapter adapter = new ActivityRecyclerAdapter((MainActivity) requireActivity(),recyclerView);
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getActivities());
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        menu.findItem(R.id.action_profile).setIcon(profileViewModel.getProfile().getPicture());
    }
}
