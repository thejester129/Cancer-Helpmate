package com.example.cancerhelpmate.ui.daytracker;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cancerhelpmate.ui.daytracker.day_tracker_graphs.DayTrackerGraphsFragment;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_records.DayTrackerRecordsFragment;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_today.DayTrackerTodayFragment;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_weekly.DayTrackerWeeklyFragment;


public class DayTrackerViewPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 4;

    DayTrackerViewPagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DayTrackerTodayFragment();
                break;
            case 1:
                fragment = new DayTrackerRecordsFragment();
                break;
            case 2:
                fragment = new DayTrackerGraphsFragment();
                break;
            case 3:
                fragment = new DayTrackerWeeklyFragment();
                break;
            default:
                throw new RuntimeException("Not enough tabs in daytracker viewpager");
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return COUNT;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        switch (position){
            case 0 :
                return "Today";
            case 1 :
                return "Past";
            case 2 :
                return "Graph";
            case 3 :
                return "Weekly";
            default:
                throw new RuntimeException("Not enough tabs in daytracker viewpager");
        }
    }
}