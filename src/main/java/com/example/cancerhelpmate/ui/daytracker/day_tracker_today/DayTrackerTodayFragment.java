package com.example.cancerhelpmate.ui.daytracker.day_tracker_today;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.common.LinePagerIndicatorDecoration;
import com.example.cancerhelpmate.database.daytracker.DayTrackerEntry;
import com.example.cancerhelpmate.databinding.FragmentDayTrackerTodayBinding;
import com.example.cancerhelpmate.ui.daytracker.DayTrackerViewModel;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool.DayTrackerBodyWordsRecyclerAdapter;
import com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool.DayTrackerHeaderWordsRecyclerAdapter;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionSpinnerAdapter;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;


public class DayTrackerTodayFragment extends Fragment {
    private FragmentDayTrackerTodayBinding binding;
    private SeekBar painSeekBar;
    private SeekBar fatigueSeekBar;
    private SeekBar appetiteSeekBar;
    private Spinner emotionSpinner;
    private Button saveButton;
    private DayTrackerEntry entry;
    private DayTrackerViewModel viewModel;
    private EditText editText;
    private TextView treatmentSwitch;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_day_tracker_today,container,false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(DayTrackerViewModel.class);
        getEntry();
        binding.setEntry(entry);
        binding.setViewModel(viewModel);
        binding.setLifecycleOwner(this);
        getReferences(view);
        createBindings();
        setupWordsRecyclers(view);
        initEditText();
        return view;
    }

    private void getReferences(View view){
        painSeekBar = view.findViewById(R.id.day_tracker_dialog_pain_seek_bar);
        fatigueSeekBar = view.findViewById(R.id.day_tracker_dialog_fatigue_seek_bar);
        appetiteSeekBar = view.findViewById(R.id.day_tracker_dialog_appetite_seek_bar);
        emotionSpinner = view.findViewById(R.id.day_tracker_dialog_emotion_spinner);
        saveButton = view.findViewById(R.id.day_tracker_dialog_save_button);
        editText = view.findViewById(R.id.day_tracker_edit_text);
        treatmentSwitch = view.findViewById(R.id.day_tracker_dialog_treatment_text);
    }

    public void createBindings() {
        painSeekBar.setProgress(entry.getPainLevel());

        saveButton.setOnClickListener(saveButtonListener);
        treatmentSwitch.setOnClickListener(treatmentSwitchListener);
        setupPainSeekView();
        setupFatigueSeekView();
        setupAppetiteSeekView();
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

    private void setupFatigueSeekView(){
        fatigueSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                entry.setFatigueLevel(progress);
                viewModel.sliderFatigueLevel.setValue(progress + "");

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void setupAppetiteSeekView(){
        appetiteSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                entry.setAppetiteLevel(progress);
                viewModel.sliderAppetiteLevel.setValue(progress + "");

            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    private void initEditText(){
        if(editText.getText().toString().matches("")){
            editText.append("Today, ");
        }
    }

    private void setupWordsRecyclers(View view){
        RecyclerView recyclerView = view.findViewById(R.id.day_tracker_body_word_recycler);
        final DayTrackerBodyWordsRecyclerAdapter adapter = new DayTrackerBodyWordsRecyclerAdapter(recyclerView,editText);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());
        setupWordsHeaderRecycler(view,adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));

    }

    private void setupWordsHeaderRecycler(View view,DayTrackerBodyWordsRecyclerAdapter bodyAdapter){
        RecyclerView recyclerView = view.findViewById(R.id.day_tracker_header_word_recycler);
        final DayTrackerHeaderWordsRecyclerAdapter adapter = new DayTrackerHeaderWordsRecyclerAdapter( recyclerView,bodyAdapter);
        recyclerView.setAdapter(adapter);
        adapter.setItems(viewModel.getHeaderWords());
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL,false));
        //recyclerView.addItemDecoration(new LinePagerIndicatorDecoration());
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

    private View.OnClickListener treatmentSwitchListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            entry.toggleTreatment();
            binding.invalidateAll();
        }
    };


}