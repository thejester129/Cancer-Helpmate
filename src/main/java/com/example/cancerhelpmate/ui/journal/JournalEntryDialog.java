package com.example.cancerhelpmate.ui.journal;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.databinding.FragmentJournalBinding;
import com.example.cancerhelpmate.databinding.JournalEntryDialogBinding;

import javax.security.auth.callback.Callback;

public  class JournalEntryDialog extends DialogFragment {
    private JournalViewModel viewModel;
    private JournalEntry entry;
    private ImageButton closeButton;
    private ImageButton saveButton;
    private boolean addEntry;

    public JournalEntryDialog(JournalViewModel viewModel) {
        this.viewModel = viewModel;
        entry = new JournalEntry();
        addEntry = true;
    }

    public JournalEntryDialog(JournalEntry entry, JournalViewModel viewModel) {
        this.entry = entry;
        this.viewModel = viewModel;
    }

    public static JournalEntryDialog newInstance(JournalViewModel viewModel) {
        return new JournalEntryDialog(viewModel);
    }

    public static JournalEntryDialog newInstance(JournalEntry entry, JournalViewModel viewModel) {
        return new JournalEntryDialog(entry, viewModel);
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
        JournalEntryDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.journal_entry_dialog,container,false);
        binding.setEntry(entry);
        binding.setLifecycleOwner(this);
        getReferences(binding.getRoot());
        createBindings();
        return binding.getRoot();
    }

    private void getReferences(View view){
        closeButton = view.findViewById(R.id.journal_dialog_close_button);
        saveButton = view.findViewById(R.id.journal_dialog_save_button);
    }


    public void createBindings() {
        //close
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addEntry){
                    viewModel.deleteEntry(entry);
                }
                dismiss();
            }
        });
        //save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(addEntry){
                    viewModel.addEntry(entry);
                }
                else{
                    viewModel.updateEntry(entry);
                }
                dismiss();
            }
        });
    }


}