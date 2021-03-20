package com.example.cancerhelpmate.ui.daytracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.cancerhelpmate.MainActivity;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.DayTrackerTodayPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentDaytrackerBinding;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_records.DayTrackerRecordsRecyclerAdapter;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewPagerAdapter;
import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class DayTrackerFragment extends Fragment {
    private DayTrackerViewModel viewModel;
    private DayTrackerRecordsRecyclerAdapter adapter;
    private RecyclerView recyclerView;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daytracker, container, false);
        setupViewPager(view);
        setHasOptionsMenu(true);
        return view;
    }

    private void setupViewPager(View view){
        ViewPager viewPager = view.findViewById(R.id.day_tracker_viewpager);
        DayTrackerViewPagerAdapter adapter = new DayTrackerViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.day_tracker_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        ProfileViewModel profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        menu.findItem(R.id.action_profile).setIcon(profileViewModel.getProfile().getPicture());
    }

}
