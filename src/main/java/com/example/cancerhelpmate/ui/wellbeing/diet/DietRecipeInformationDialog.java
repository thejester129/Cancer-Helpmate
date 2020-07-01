package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

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
    private Button changeButton;
    private boolean browserMode;

    public DietRecipeInformationDialog(RecipeEntry recipeEntry, DietViewModel viewModel, boolean browserMode) {
        this.viewModel = viewModel;
        this.recipeEntry = recipeEntry;
        this.browserMode = browserMode;
    }

    public static DietRecipeInformationDialog newInstance(RecipeEntry recipeEntry, DietViewModel viewModel, boolean browserMode) {
        return new DietRecipeInformationDialog(recipeEntry,viewModel,browserMode);
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
        changeButton = view.findViewById(R.id.diet_recipe_information_change_button);
    }

    public void createBindings() {
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        if(browserMode){
            changeButton.setVisibility(View.GONE);

            selectButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    viewModel.setBrowsingRecipe(recipeEntry);
                    viewModel.exitRecipeBrowser.setValue(true);
                    dismiss();
                }
            });
        }
        else{
            selectButton.setVisibility(View.GONE);

            changeButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    DialogFragment dialog = DietRecipeBrowserDialog.newInstance(viewModel);
                    dialog.show(getParentFragmentManager(), "tag");
                    dismiss();

                }
            });
        }

    }
}