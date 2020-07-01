package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.database.wellbeing.WellbeingEntry;
import com.example.cancerhelpmate.databinding.DietBreakfastPopupBinding;
import com.example.cancerhelpmate.databinding.DietLunchPopupBinding;
import com.example.cancerhelpmate.databinding.DietStatsPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentDietBinding;
import com.example.cancerhelpmate.ui.wellbeing.WellbeingViewModel;

public class DietFragment extends Fragment {
    private FragmentDietBinding binding;
    private DietViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_diet,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DietViewModel.class);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupStatsLayout();
        setupBreakfastLayout();
        setupLunchLayout();
        setupObserver();
        return view;
    }

    private void setupObserver(){
        viewModel.getTodaysLiveWellbeingEntry().observe(getViewLifecycleOwner(), new Observer<WellbeingEntry>() {
            @Override
            public void onChanged(WellbeingEntry entry) {
                viewModel.refresh();
                binding.invalidateAll();
            }
        });
    }

    private void setupStatsLayout(){
        final DietStatsPopupBinding statsPopupBinding = binding.dietStatsPopupLayout;
        statsPopupBinding.setViewModel(viewModel);
        statsPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        statsPopupBinding.dietStatsPopupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = DietStatsDialog.newInstance(viewModel);
                dialog.show(getChildFragmentManager(), "tag");
            }
        });
        viewModel.getTodaysLiveWellbeingEntry().observe(getViewLifecycleOwner(), new Observer<WellbeingEntry>() {
            @Override
            public void onChanged(WellbeingEntry entry) {
                statsPopupBinding.invalidateAll();
            }
        });
    }

    private void setupBreakfastLayout(){
        final DietBreakfastPopupBinding dietBreakfastPopupBinding = binding.dietBreakfastPopupLayout;
        dietBreakfastPopupBinding.setViewModel(viewModel);
        dietBreakfastPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietBreakfastPopupBinding.breakfastPopupButton.setOnClickListener(breakfastPopupListener);
        viewModel.getTodaysLiveBreakfastRecipe().observe(getViewLifecycleOwner(), new Observer<RecipeEntry>() {
            @Override
            public void onChanged(RecipeEntry recipeEntry){
                if(recipeEntry != null){
                    dietBreakfastPopupBinding.invalidateAll();
                }
            }
        });
    }

    private void setupLunchLayout(){
        final DietLunchPopupBinding dietLunchPopupBinding = binding.dietLunchPopupLayout;
        dietLunchPopupBinding.setViewModel(viewModel);
        dietLunchPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietLunchPopupBinding.lunchPopupButton.setOnClickListener(lunchPopupListener);
        viewModel.getTodaysLiveLunchRecipe().observe(getViewLifecycleOwner(), new Observer<RecipeEntry>() {
            @Override
            public void onChanged(RecipeEntry recipeEntry){
                if(recipeEntry != null){
                    dietLunchPopupBinding.invalidateAll();
                }
            }
        });
    }

    private View.OnClickListener breakfastPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysBreakfastRecipe() != null){
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(viewModel.getTodaysBreakfastRecipe(),viewModel,false);
                dialog.show(getChildFragmentManager(), "tag");
            }
            else{
                DialogFragment dialog = DietRecipeBrowserDialog.newInstance(viewModel);
                dialog.show(getChildFragmentManager(), "tag");
            }
            viewModel.browsingRecipeType.setValue(RecipeType.BREAKFAST);

        }
    };

    private View.OnClickListener lunchPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysLunchRecipe() != null){
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(viewModel.getTodaysLunchRecipe(),viewModel,false);
                dialog.show(getChildFragmentManager(), "tag");
            }
            else{
                DialogFragment dialog = DietRecipeBrowserDialog.newInstance(viewModel);
                dialog.show(getChildFragmentManager(), "tag");
            }
            viewModel.browsingRecipeType.setValue(RecipeType.LUNCH);

        }
    };

}
