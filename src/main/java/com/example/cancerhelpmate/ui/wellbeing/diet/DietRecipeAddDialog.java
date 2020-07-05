package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.databinding.DietRecipeAddDialogBinding;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserDialogBinding;

public class DietRecipeAddDialog extends DialogFragment {
    private DietViewModel dietViewModel;
    private RecipeEntry recipeEntry;

    public DietRecipeAddDialog(DietViewModel dietViewModel) {
        this.dietViewModel = dietViewModel;
        recipeEntry = new RecipeEntry();
    }

    public static DietRecipeAddDialog newInstance(DietViewModel dietViewModel) {
        return new DietRecipeAddDialog(dietViewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.FullscreenDialogTheme);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        DietRecipeAddDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.diet_recipe_add_dialog,container,false);
        binding.setRecipe(recipeEntry);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();

        binding.dietRecipeAddDialogCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        binding.dietRecipeAddDialogAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dietViewModel.addRecipe(recipeEntry);
                dismiss();
            }
        });

        return view;
    }
}
