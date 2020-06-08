package com.example.cancerhelpmate.ui.journal;

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
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.databinding.FragmentJournalBinding;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentJournalBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_journal,container,false);
        journalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
        binding.setJournalViewModel(journalViewModel);
        binding.setLifecycleOwner(this);
        setupObserver();
        setupRecycler(binding.getRoot());
        setupBindings(binding);
        return binding.getRoot();
    }

    private void setupObserver( ){
        journalViewModel.getLiveJournals().observe(getViewLifecycleOwner(), new Observer<List<JournalEntry>>() {
            @Override
            public void onChanged(@NotNull List<JournalEntry> profile) {
                journalViewModel.refresh();
            }
        });
    }

    private void setupRecycler(View view){
        RecyclerView recyclerView = view.findViewById(R.id.journal_recycler_view);
        final JournalRecyclerAdapter adapter = new JournalRecyclerAdapter(getParentFragmentManager(), recyclerView, journalViewModel, view);
        recyclerView.setAdapter(adapter);
        adapter.setItems(journalViewModel.getJournals());
        journalViewModel.getLiveJournals().observe(getViewLifecycleOwner(), new Observer<List<JournalEntry>>() {
            @Override
            public void onChanged(@Nullable final List<JournalEntry> entries) {
                adapter.setItems(entries);
            }
        });
    }

    private void setupBindings(FragmentJournalBinding binding){
        binding.journalAddButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                DialogFragment dialog = JournalEntryDialog.newInstance(journalViewModel);
                dialog.show(getParentFragmentManager(), "tag");
            }
        });
    }
}
