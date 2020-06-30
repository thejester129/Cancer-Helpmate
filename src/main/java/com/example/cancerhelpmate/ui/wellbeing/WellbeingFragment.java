package com.example.cancerhelpmate.ui.wellbeing;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.cancerhelpmate.R;
import com.google.android.material.tabs.TabLayout;


public class WellbeingFragment extends Fragment {
    private WellbeingViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wellbeing, container, false);
        viewModel = new ViewModelProvider(this).get(WellbeingViewModel.class);
        setupViewPager(view);

        return view;
    }

    private void setupViewPager(View view){
        ViewPager viewPager = view.findViewById(R.id.wellbeing_viewpager);
        WellbeingViewPagerAdapter adapter = new WellbeingViewPagerAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);

        TabLayout tabLayout = view.findViewById(R.id.wellbeing_tab_layout);
        tabLayout.setupWithViewPager(viewPager);
    }
}

