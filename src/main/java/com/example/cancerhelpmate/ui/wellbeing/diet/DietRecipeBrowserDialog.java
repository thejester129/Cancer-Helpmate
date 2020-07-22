package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.WebDialog;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserDialogBinding;
import com.example.cancerhelpmate.ui.resources.ResourceWebDialog;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietBrowserFilterSettings;
import com.example.cancerhelpmate.ui.wellbeing.diet.filter.DietBrowserSortDialog;

import java.util.List;


public class DietRecipeBrowserDialog extends DialogFragment implements PopupMenu.OnMenuItemClickListener{

    private DietViewModel viewModel;
    private DietRecipeBrowserRecyclerAdapter adapter;

    public DietRecipeBrowserDialog(DietViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static DietRecipeBrowserDialog newInstance(DietViewModel viewModel) {
        return new DietRecipeBrowserDialog(viewModel);
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
        DietRecipeBrowserDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.diet_recipe_browser_dialog,container,false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();
        setupRecycler(view);
        setupObserver();
        binding.dietRecipeBrowserDialogToolbarCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        binding.dietRecipeBrowserAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialogFragment = new DietRecipeAddDialog(viewModel);
                dialogFragment.show(getParentFragmentManager(),"tag");
            }
        });

        binding.dietRecipeBrowserDialogToolbarFilterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popup = new PopupMenu(getContext(), v);
                popup.setOnMenuItemClickListener(DietRecipeBrowserDialog.this);
                popup.inflate(R.menu.diet_menu);
                popup.show();
            }
        });
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.diet_recipe_browser_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        adapter = new DietRecipeBrowserRecyclerAdapter(recyclerView, viewModel, getChildFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getFilteredRecipes());
        viewModel.getLiveRecipes().observe(getViewLifecycleOwner(), new Observer<List<RecipeEntry>>() {
            @Override
            public void onChanged(@Nullable final List<RecipeEntry> recipes) {
                adapter.setItems(recipes);
            }
        });
    }

    private void setupObserver(){
        viewModel.exitRecipeBrowser.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean exit) {
                if(exit){
                    dismiss();
                    viewModel.exitRecipeBrowser.setValue(false);
                }
            }
        });
        viewModel.browserFilterSettings.observe(getViewLifecycleOwner(), new Observer<DietBrowserFilterSettings>() {
            @Override
            public void onChanged(DietBrowserFilterSettings filterItem) {
                adapter.setItems(viewModel.getFilteredRecipes());
            }
        });
    }


    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_filter:
                DialogFragment dialogFragment = new DietBrowserSortDialog(viewModel);
                dialogFragment.show(getParentFragmentManager(),"tag");
                return true;
            case R.id.action_full_list:
                DialogFragment dialog = WebDialog.newInstance("https://be.macmillan.org.uk/Downloads/CancerInformation/LivingWithAndAfterCancer/MAC17345RecipesLowresPDF20181220.pdf");
                dialog.show(getParentFragmentManager(), "tag");
                return true;

            default:
                return false;
        }
    }

}
