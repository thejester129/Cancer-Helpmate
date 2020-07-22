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
import com.example.cancerhelpmate.databinding.DietDinnerPopupBinding;
import com.example.cancerhelpmate.databinding.DietExtraPopupBinding;
import com.example.cancerhelpmate.databinding.DietLunchPopupBinding;
import com.example.cancerhelpmate.databinding.DietStatsPopupBinding;
import com.example.cancerhelpmate.databinding.DietSupperPopupBinding;
import com.example.cancerhelpmate.databinding.FragmentDietBinding;

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
        setupDinnerLayout();
        setupSupperLayout();
        setupExtraLayout();
        setupObserver();
        return view;
    }

    private void setupObserver(){
        viewModel.getTodaysLiveWellbeingEntry().observe(getViewLifecycleOwner(), new Observer<WellbeingEntry>() {
            @Override
            public void onChanged(WellbeingEntry entry) {
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

    private void setupDinnerLayout(){
        final DietDinnerPopupBinding dietDinnerPopupBinding = binding.dietDinnerPopupLayout;
        dietDinnerPopupBinding.setViewModel(viewModel);
        dietDinnerPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietDinnerPopupBinding.lunchPopupButton.setOnClickListener(dinnerPopupListener);
        viewModel.getTodaysLiveDinnerRecipe().observe(getViewLifecycleOwner(), new Observer<RecipeEntry>() {
            @Override
            public void onChanged(RecipeEntry recipeEntry){
                if(recipeEntry != null){
                    dietDinnerPopupBinding.invalidateAll();
                }
            }
        });
    }

    private void setupSupperLayout(){
        final DietSupperPopupBinding dietSupperPopupBinding = binding.dietSupperPopupLayout;
        dietSupperPopupBinding.setViewModel(viewModel);
        dietSupperPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietSupperPopupBinding.lunchPopupButton.setOnClickListener(supperPopupListener);
        viewModel.getTodaysLiveSupperRecipe().observe(getViewLifecycleOwner(), new Observer<RecipeEntry>() {
            @Override
            public void onChanged(RecipeEntry recipeEntry){
                if(recipeEntry != null){
                    dietSupperPopupBinding.invalidateAll();
                }
            }
        });
    }

    private void setupExtraLayout(){
        final DietExtraPopupBinding dietExtraPopupBinding = binding.dietExtraPopupLayout;
        dietExtraPopupBinding.setViewModel(viewModel);
        dietExtraPopupBinding.setLifecycleOwner(getViewLifecycleOwner());
        dietExtraPopupBinding.lunchPopupButton.setOnClickListener(extraPopupListener);
        viewModel.getTodaysLiveExtraRecipe().observe(getViewLifecycleOwner(), new Observer<RecipeEntry>() {
            @Override
            public void onChanged(RecipeEntry recipeEntry){
                if(recipeEntry != null){
                    dietExtraPopupBinding.invalidateAll();
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
                openRecipeBrowserDialog();
            }
            viewModel.browsingRecipeType.setValue(DietRecipeType.BREAKFAST);
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
                openRecipeBrowserDialog();
            }
            viewModel.browsingRecipeType.setValue(DietRecipeType.LUNCH);
        }
    };

    private View.OnClickListener dinnerPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysDinnerRecipe() != null){
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(viewModel.getTodaysDinnerRecipe(),viewModel,false);
                dialog.show(getChildFragmentManager(), "tag");
            }
            else{
                openRecipeBrowserDialog();
            }
            viewModel.browsingRecipeType.setValue(DietRecipeType.DINNER);
        }
    };

    private View.OnClickListener supperPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysSupperRecipe() != null){
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(viewModel.getTodaysSupperRecipe(),viewModel,false);
                dialog.show(getChildFragmentManager(), "tag");
            }
            else{
                openRecipeBrowserDialog();
            }
            viewModel.browsingRecipeType.setValue(DietRecipeType.SUPPER);
        }
    };

    private View.OnClickListener extraPopupListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysExtraRecipe() != null){
                DialogFragment dialog = DietRecipeInformationDialog.newInstance(viewModel.getTodaysExtraRecipe(),viewModel,false);
                dialog.show(getChildFragmentManager(), "tag");
            }
            else{
                openRecipeBrowserDialog();
            }
            viewModel.browsingRecipeType.setValue(DietRecipeType.EXTRA);
        }
    };

    private void openRecipeBrowserDialog(){
        DialogFragment dialog = DietRecipeBrowserDialog.newInstance(viewModel);
        dialog.show(getChildFragmentManager(), "tag");
    }


}
