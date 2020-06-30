package com.example.cancerhelpmate.ui.wellbeing;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cancerhelpmate.ui.wellbeing.diet.DietFragment;
import com.example.cancerhelpmate.ui.wellbeing.exercise.ExerciseFragment;
import com.example.cancerhelpmate.ui.wellbeing.mental_wellbeing.MentalWellbeingFragment;

public class WellbeingViewPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 3;

    WellbeingViewPagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new DietFragment();
                break;
            case 1:
                fragment = new ExerciseFragment();
                break;
            case 2:
                fragment = new MentalWellbeingFragment();
                break;
            default:
                throw new RuntimeException("Not enough tabs in wellbeing viewpager");
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
                return "Diet";
            case 1 :
                return "Exercise";
            case 2 :
                return "Mental";
            default:
                throw new RuntimeException("Not enough tabs in wellbeing viewpager");
        }
    }
}

