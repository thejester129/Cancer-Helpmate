package com.example.cancerhelpmate.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.checklist.ChecklistEntry;
import com.example.cancerhelpmate.databinding.FragmentChecklistBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ChecklistFragment extends Fragment {

    private ChecklistViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentChecklistBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_checklist, container, false);
        viewModel = new ViewModelProvider(this).get(ChecklistViewModel.class);
        View view = binding.getRoot();
        binding.setLifecycleOwner(getViewLifecycleOwner());
        setupRecycler(view);
        createBindings(binding);
        return view;
    }

    private void setupRecycler(View view) {
        RecyclerView recyclerView = view.findViewById(R.id.checklist_recycler_view);
        final ChecklistRecyclerAdapter adapter = new ChecklistRecyclerAdapter( recyclerView, viewModel, getContext());
        recyclerView.setAdapter(adapter);
        //touch helper
        ChecklistItemTouchHelperCallback itemTouchHelperCallback = new ChecklistItemTouchHelperCallback(adapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(itemTouchHelperCallback);
        touchHelper.attachToRecyclerView(recyclerView);

        adapter.setItems(viewModel.getItems());
        viewModel.getLiveItemCount().observe(getViewLifecycleOwner(), new Observer<Integer>() {
            @Override
            public void onChanged(@Nullable final Integer count) {
                adapter.setItems(viewModel.getItems());
            }
        });
    }

    private void createBindings(FragmentChecklistBinding binding) {
        binding.checklistAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DialogFragment dialog = ChecklistEntryDialog.newInstance(viewModel);
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
    }
}
