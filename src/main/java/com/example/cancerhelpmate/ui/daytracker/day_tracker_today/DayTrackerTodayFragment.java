package com.example.cancerhelpmate.ui.daytracker.day_tracker_today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.cancerhelpmate.R;


public class DayTrackerTodayFragment extends Fragment {

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_day_tracker_today, container, false);
        return view;
    }


}