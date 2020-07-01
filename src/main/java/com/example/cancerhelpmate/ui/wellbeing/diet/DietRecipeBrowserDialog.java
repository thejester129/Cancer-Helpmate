package com.example.cancerhelpmate.ui.wellbeing.diet;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.database.wellbeing.WellbeingEntry;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserDialogBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;


public class DietRecipeBrowserDialog extends DialogFragment {

    private DietViewModel viewModel;

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
        ImageButton backButton = view.findViewById(R.id.diet_recipe_browser_dialog_toolbar_close_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.diet_recipe_browser_recycler);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
        final DietRecipeBrowserRecyclerAdapter adapter = new DietRecipeBrowserRecyclerAdapter(recyclerView, viewModel, getChildFragmentManager());
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getRecipes());
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
    }

}
