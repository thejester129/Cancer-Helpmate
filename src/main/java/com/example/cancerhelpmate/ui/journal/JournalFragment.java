package com.example.cancerhelpmate.ui.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.database.profile.ProfileEntry;
import com.example.cancerhelpmate.databinding.FragmentHomeBinding;
import com.example.cancerhelpmate.databinding.FragmentJournalBinding;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JournalFragment extends Fragment {

    private JournalViewModel journalViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        FragmentJournalBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_journal,container,false);
        journalViewModel = new ViewModelProvider(this).get(JournalViewModel.class);
        binding.setProfileViewModel(journalViewModel);
        binding.setLifecycleOwner(this);
        setupObserver();
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
}
