package com.example.cancerhelpmate.ui.checklist;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.ChecklistEntryDialogBinding;

import de.hdodenhof.circleimageview.CircleImageView;

public  class ChecklistEntryDialog extends DialogFragment {
    private ChecklistViewModel viewModel;
    private ImageView closeButton;
    private Button saveButton;

    public ChecklistEntryDialog(ChecklistViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public static ChecklistEntryDialog newInstance(ChecklistViewModel viewModel) {
        return new ChecklistEntryDialog(viewModel);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ChecklistEntryDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.checklist_entry_dialog,container,false);
        binding.setEntry(viewModel.editEntry);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        getReferences(view);
        createBindings();
        return view;
    }

    private void getReferences(View view){
        closeButton = view.findViewById(R.id.checklist_dialog_close_button);
        saveButton = view.findViewById(R.id.checklist_dialog_save_button);
    }

    public void createBindings() {
        //close
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //save
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.saveEditEntry();
                dismiss();
            }
        });
    }

}
