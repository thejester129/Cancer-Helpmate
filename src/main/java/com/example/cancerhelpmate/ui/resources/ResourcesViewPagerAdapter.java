package com.example.cancerhelpmate.ui.resources;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.cancerhelpmate.ui.wellbeing.diet.DietFragment;

public class ResourcesViewPagerAdapter extends FragmentPagerAdapter {

    private int COUNT = 3;

    ResourcesViewPagerAdapter(FragmentManager fm) {
        super(fm,BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new ResourcesDiagnosisFragment();
                break;
            case 1:
                fragment = new ResourcesTreatmentFragment();
                break;
            case 2:
                fragment = new ResourcesRecoveryFragment();
                break;
            default:
                throw new RuntimeException("Not enough tabs in resources viewpager");
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
                return "Diagnosis";
            case 1 :
                return "Treatment";
            case 2 :
                return "Recovery";
            default:
                throw new RuntimeException("Not enough tabs in resources viewpager");
        }
    }
}
