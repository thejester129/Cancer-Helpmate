package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.DietBreakfastPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentDietBinding;
import com.example.cancerhelpmate.databinding.FragmentJournalBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerEntryEditDialog;
import com.example.cancerhelpmate.ui.journal.JournalViewModel;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewModel;

import java.util.ArrayList;
import java.util.List;

public class DietFragment extends Fragment {

    private DietViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDietBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diet,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DietViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupBreakfastLayout(binding);
        return view;
    }

    private void setupBreakfastLayout(FragmentDietBinding binding){
        DietBreakfastPopupBinding dietBreakfastPopupBinding = binding.dietBreakfastPopupLayout;
        dietBreakfastPopupBinding.setViewModel(viewModel);
        dietBreakfastPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietBreakfastPopupBinding.breakfastPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = DietRecipeBrowserDialog.newInstance(viewModel,DietRecipeType.BREAKFAST);
                dialog.show(getChildFragmentManager(), "tag");
            }
        });
    }

}
