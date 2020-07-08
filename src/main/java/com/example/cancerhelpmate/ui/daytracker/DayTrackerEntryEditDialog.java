package com.example.cancerhelpmate.ui.daytracker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.DialogFragment;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.DayTrackerEntryEditDialogBinding;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionSpinnerAdapter;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;

public  class DayTrackerEntryEditDialog extends DialogFragment {
    private DayTrackerViewModel viewModel;
    private DayTrackerEntry entry;
    private ImageButton toolbarCloseButton;
    private ImageButton toolbarSaveButton;
    private SeekBar painSeekBar;
    private Spinner emotionSpinner;
    private boolean addingEntry;

    public DayTrackerEntryEditDialog(DayTrackerViewModel viewModel) {
        this.viewModel = viewModel;
        entry = new DayTrackerEntry();
        addingEntry = true;
    }

    public static DayTrackerEntryEditDialog newInstance(DayTrackerViewModel viewModel) {
        return new DayTrackerEntryEditDialog(viewModel);
    }

    public DayTrackerEntryEditDialog(DayTrackerViewModel viewModel, DayTrackerEntry entry) {
        this.viewModel = viewModel;
        this.entry = entry;
    }

    public static DayTrackerEntryEditDialog newInstance(DayTrackerViewModel viewModel, DayTrackerEntry entry) {
        return new DayTrackerEntryEditDialog(viewModel,entry);
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
        DayTrackerEntryEditDialogBinding binding = DataBindingUtil.inflate(inflater, R.layout.day_tracker_entry_edit_dialog,container,false);
        binding.setEntry(entry);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getReferences(binding.getRoot());
        createBindings();
        return binding.getRoot();
    }

    private void getReferences(View view){
        toolbarCloseButton = view.findViewById(R.id.day_tracker_dialog_toolbar_close_button);
        toolbarSaveButton = view.findViewById(R.id.day_tracker_dialog_toolbar_save_button);
        painSeekBar = view.findViewById(R.id.day_tracker_dialog_pain_seek_bar);
        emotionSpinner = view.findViewById(R.id.day_tracker_dialog_emotion_spinner);
    }


    public void createBindings() {
        if(!addingEntry){
           setEntryValues();
        }
        //close
        toolbarCloseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dismiss();
            }
        });
        //save
        toolbarSaveButton.setOnClickListener(saveButtonListener);
        setupPainSeekView();
        setupSpinner();
    }

    private void setupSpinner(){
        emotionSpinner.setAdapter(new DayTrackerEmotionSpinnerAdapter(getContext(), DayTrackerEmotions.getAllEmotions()));
        emotionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                entry.setEmotion(DayTrackerEmotions.getAllEmotions().get(position));
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void setEntryValues(){
        painSeekBar.setProgress(entry.getPainLevel());
    }

    private void setupPainSeekView(){
        painSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                entry.setPainLevel(progress);
                viewModel.sliderPainLevel.setValue(progress + "");

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(addingEntry){
                viewModel.addEntry(entry);
            }
            else{
                viewModel.updateEntry(entry);
            }
            dismiss();
        }
    };

}
