package com.example.cancerhelpmate.ui.daytracker.day_tracker_today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.FragmentDayTrackerTodayBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionSpinnerAdapter;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;


public class DayTrackerTodayFragment extends Fragment {
    private SeekBar painSeekBar;
    private Spinner emotionSpinner;
    private Button saveButton;
    private DayTrackerEntry entry;
    private DayTrackerViewModel viewModel;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        FragmentDayTrackerTodayBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_tracker_today,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        getEntry();
        binding.setEntry(entry);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getReferences(view);
        createBindings();
        return view;
    }

    private void getReferences(View view){
        painSeekBar = view.findViewById(R.id.day_tracker_dialog_pain_seek_bar);
        emotionSpinner = view.findViewById(R.id.day_tracker_dialog_emotion_spinner);
        saveButton = view.findViewById(R.id.day_tracker_dialog_save_button);
    }

    public void createBindings() {
        painSeekBar.setProgress(entry.getPainLevel());

        saveButton.setOnClickListener(saveButtonListener);
        setupPainSeekView();
        setupSpinner();
    }

    private void getEntry(){
        if(viewModel.getTodaysEntryFilled()){
            entry = viewModel.getTodaysEntry();
        }
        else{
            entry = new DayTrackerEntry();
        }
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
        emotionSpinner.setSelection(entry.getEmotion().getCode());
    }

    private View.OnClickListener saveButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(viewModel.getTodaysEntryFilled()){
                viewModel.updateEntry(entry);
                Toast.makeText(getContext(), "Entry updated!", Toast.LENGTH_SHORT).show();
            }
            else{
                viewModel.addEntry(entry);
                Toast.makeText(getContext(), "Entry added!", Toast.LENGTH_SHORT).show();
            }
        }
    };


}