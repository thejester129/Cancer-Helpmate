package com.example.cancerhelpmate.ui.activities.breathing_activity;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cancerhelpmate.R;

import java.util.Timer;
import java.util.TimerTask;


public class BreathingActivityFragment extends Fragment {
    private Button startButton;
    private TextView titleText;
    private TextView timerText;
    private Timer timer;
    private TimerTask timerTask;
    private ImageView circle;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_breathing_activity, container, false);
        getControlReferences(view);
        createBindings();
        setHasOptionsMenu(true);
        return view;
    }

    private void getControlReferences(View view){
        startButton = view.findViewById(R.id.breathing_exercise_start_button);
        titleText = view.findViewById(R.id.breathing_exercise_title);
        timerText = view.findViewById(R.id.breathing_exercise_timer);
        circle = view.findViewById(R.id.breathing_exercise_circle);
    }

    private void createBindings(){
        startButton.setOnClickListener(startButtonListener);
    }

    private View.OnClickListener startButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if(startButton.getText().equals("Start")){
                startTimer();
            }
            else{
                stopTimer();
            }
        }
    };

    private void setupTimer(){
        timerTask =   new TimerTask() {
            boolean timer_reverse = false;
            int seconds_passed = 1;
            @Override
            public void run() {
                requireActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(!timer_reverse){
                            seconds_passed++;
                            if(seconds_passed > 5){
                                timer_reverse = true;
                                seconds_passed--;
                                titleText.setText(R.string.breathe_out);
                            }
                        }
                        else{
                            seconds_passed--;
                            if(seconds_passed < 1){
                                timer_reverse = false;
                                seconds_passed++;
                                titleText.setText(R.string.breathe_in);
                                circleAnimation();
                            }

                        }

                        timerText.setText(String.valueOf(seconds_passed));
                    }
                });

            }
        };
    }

    private void startTimer(){
        startButton.setText(R.string.stop);
        titleText.setText(R.string.breathe_in);
        timerText.setText("1");
        timer = new Timer();
        setupTimer();
        timer.scheduleAtFixedRate(timerTask,1000,1000);
        circleAnimation();
    }

    private void stopTimer(){
        startButton.setText(R.string.start);
        timer.cancel();
        circle.clearAnimation();
        circle.animate().cancel();
        circle.setVisibility(View.INVISIBLE);
        titleText.setText("");
        timerText.setText("");
    }

    private void circleAnimation(){
        Animation animation1 = AnimationUtils.loadAnimation(getContext(),
                R.anim.zoom);
        circle.startAnimation(animation1);
    }

    @Override
    public void onDestroyView() {
        if(timer != null){
            stopTimer();
        }
        super.onDestroyView();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_profile).setVisible(false);
    }

}

