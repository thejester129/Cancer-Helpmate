package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.databinding.ChecklistEntryDialogBinding;
import com.example.cancerhelpmate.databinding.DietRecipeInformationDialogBinding;
import com.example.cancerhelpmate.ui.checklist.ChecklistViewModel;

import de.hdodenhof.circleimageview.CircleImageView;

public  class DietRecipeInformationDialog extends DialogFragment {
    private DietViewModel viewModel;
    private RecipeEntry recipeEntry;
    private ImageButton closeButton;
    private Button selectButton;
    private ImageView image;

    public DietRecipeInformationDialog(RecipeEntry recipeEntry, DietViewModel viewModel) {
        this.viewModel = viewModel;
        this.recipeEntry = recipeEntry;
    }

    public static DietRecipeInformationDialog newInstance(RecipeEntry recipeEntry, DietViewModel viewModel) {
        return new DietRecipeInformationDialog(recipeEntry,viewModel);
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
        DietRecipeInformationDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.diet_recipe_information_dialog, container, false);
        binding.setRecipe(recipeEntry);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        getReferences(view);
        createBindings();
        return view;
    }

    private void getReferences(View view) {
        closeButton = view.findViewById(R.id.diet_recipe_information_close_button);
        selectButton = view.findViewById(R.id.diet_recipe_information_select_button);
        image = view.findViewById(R.id.diet_recipe_information_image);
    }

    public void createBindings() {
        image.setImageResource(recipeEntry.getImageLink());
        //close
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //save
        selectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
    }
}