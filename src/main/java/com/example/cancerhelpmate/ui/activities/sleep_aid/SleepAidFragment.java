package com.example.cancerhelpmate.ui.activities.sleep_aid;

import android.content.DialogInterface;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.ui.activities.ActivitiesViewModel;

import java.util.ArrayList;
import java.util.List;

public class SleepAidFragment extends Fragment {
    private SleepAidViewModel viewModel;
    private MediaPlayer mediaPlayer;
    private Button startButton;
    private Button stopButton;
    private SleepAidLevel CURRENT_LEVEL;
    private Spinner levelChoiceSpinner;
    private ImageView background;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sleep_aid, container, false);
        viewModel = new ViewModelProvider(this).get(SleepAidViewModel.class);
        getControlReferences(view);
        createBindings();
        initSpinner();
        setHasOptionsMenu(true);
        return view;
    }

    private void getControlReferences(View view){
        startButton = view.findViewById(R.id.sleep_aid_start_button);
        stopButton = view.findViewById(R.id.sleep_aid_stop_button);
        levelChoiceSpinner = view.findViewById(R.id.sleep_aid_spinner);
        background = view.findViewById(R.id.sleep_aid_background);
    }

    private void createBindings(){
        startButton.setOnClickListener(startButtonListener);
        stopButton.setOnClickListener(stopButtonListener);
        levelChoiceSpinner.setOnItemSelectedListener(levelChoiceSpinnerListener);
        background.setBackgroundResource(R.drawable.forest_background);
    }

    private View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            start();
        }
    };

    private View.OnClickListener stopButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            stop();
        }
    };

    private void start(){
        startButton.setVisibility(View.INVISIBLE);
        stopButton.setVisibility(View.VISIBLE);
        mediaPlayer = MediaPlayer.create(getContext(), CURRENT_LEVEL.getSound());
        mediaPlayer.setLooping(true);
        mediaPlayer.start();
    }

    private void stop(){
        stopButton.setVisibility(View.INVISIBLE);
        startButton.setVisibility(View.VISIBLE);
        mediaPlayer.stop();
    }

    private AdapterView.OnItemSelectedListener levelChoiceSpinnerListener = new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
            if(mediaPlayer != null){
                stop();
            }
            int item = levelChoiceSpinner.getSelectedItemPosition();
            setLevel(item);
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    };

    private void setLevel(int id){
        CURRENT_LEVEL = viewModel.getLevel(id);
        background.setBackgroundResource(CURRENT_LEVEL.getPicture());
    }

    private void initSpinner(){

        List<String> levels = new ArrayList<>();
        for(SleepAidLevel sleepAidLevel:viewModel.getLevels()){
            levels.add(sleepAidLevel.getName());
        }

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireActivity(), R.layout.sleep_aid_spinner_item, levels);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        levelChoiceSpinner.setAdapter(dataAdapter);
        levelChoiceSpinner.setSelection(0);
    }

    @Override
    public void onDestroyView() {
        if(mediaPlayer != null){
            mediaPlayer.stop();
        }
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_profile).setVisible(false);
    }

}
