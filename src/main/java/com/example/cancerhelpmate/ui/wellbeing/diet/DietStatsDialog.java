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
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;
import com.example.cancerhelpmate.databinding.DietRecipeBrowserDialogBinding;
import com.example.cancerhelpmate.databinding.DietStatsDialogBinding;
import com.example.cancerhelpmate.databinding.DietStatsDialogBindingImpl;

import java.util.List;

public class DietStatsDialog extends DialogFragment {

    private DietViewModel viewModel;

    public DietStatsDialog(DietViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static DietStatsDialog newInstance(DietViewModel viewModel) {
        return new DietStatsDialog(viewModel);
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
        DietStatsDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.diet_stats_dialog,container,false);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(getViewLifecycleOwner());
        View view = binding.getRoot();

        ImageButton backButton = view.findViewById(R.id.diet_stats_dialog_back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        return view;
    }

}