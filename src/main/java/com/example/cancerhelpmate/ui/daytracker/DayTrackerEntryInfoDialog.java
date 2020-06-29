package com.example.cancerhelpmate.ui.daytracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.DayTrackerEntryInfoDialogBinding;

public  class DayTrackerEntryInfoDialog extends DialogFragment {
    private DayTrackerEntry entry;
    private ImageButton toolbarBackButton;

    public DayTrackerEntryInfoDialog(DayTrackerEntry entry) {
        this.entry = entry;
    }

    public static DayTrackerEntryInfoDialog newInstance(DayTrackerEntry entry) {
        return new DayTrackerEntryInfoDialog(entry);
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
        DayTrackerEntryInfoDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.day_tracker_entry_info_dialog,container,false);
        binding.setEntry(entry);
        binding.setLifecycleOwner(this);
        View view = binding.getRoot();
        toolbarBackButton = view.findViewById(R.id.day_tracker_dialog_toolbar_back_button);
        toolbarBackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });

        return view;
    }

}

