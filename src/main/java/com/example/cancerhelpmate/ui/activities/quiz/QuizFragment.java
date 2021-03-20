package com.example.cancerhelpmate.ui.activities.quiz;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.cancerhelpmate.R;
import com.example.cancerhelpmate.databinding.FragmentActivitiesQuizBinding;
import com.example.cancerhelpmate.databinding.FragmentChecklistBinding;
import com.example.cancerhelpmate.ui.activities.ActivitiesViewModel;
import com.example.cancerhelpmate.ui.profile.ProfileViewModel;

public class QuizFragment extends Fragment {
    private QuizViewModel viewModel;
    private ProfileViewModel profileViewModel;
    private FragmentActivitiesQuizBinding binding;
    private Button startButton;
    private Button nextButton;
    private Button finishButton;
    private Button answer1;
    private Button answer2;
    private Button answer3;
    private Button answer4;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_activities_quiz, container, false);
        View view = binding.getRoot();
        viewModel = new ViewModelProvider(this).get(QuizViewModel.class);
        profileViewModel = new ViewModelProvider(this).get(ProfileViewModel.class);
        binding.setHighScore(profileViewModel.getProfile().getQuizHighScore());
        binding.setViewModel(viewModel);
        getReferences(view);
        createBindings();
        setHasOptionsMenu(true);

        return view;
    }

    private void getReferences(View view){
        startButton = view.findViewById(R.id.quiz_start_button);
        nextButton = view.findViewById(R.id.quiz_next_button);
        finishButton = view.findViewById(R.id.quiz_finish_button);
        answer1 = view.findViewById(R.id.quiz_answer_1);
        answer2 = view.findViewById(R.id.quiz_answer_2);
        answer3 = view.findViewById(R.id.quiz_answer_3);
        answer4 = view.findViewById(R.id.quiz_answer_4);
    }

    private void createBindings(){
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.start();
                refresh();
            }
        });

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.nextQuestion();
                refresh();
            }
        });

        finishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO exit
                if(viewModel.currentScore.getValue() > profileViewModel.getProfile().getQuizHighScore()){
                    profileViewModel.setQuizHighScore(viewModel.currentScore.getValue());
                    binding.setHighScore(viewModel.currentScore.getValue());
                    refresh();
                }
                getChildFragmentManager().popBackStack();
            }
        });

        answer1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewModel.currentQuestionAnswered.getValue()){
                    if(viewModel.answer1.getValue().getCorrect()){
                        viewModel.selectAnswer(true);
                        answer1.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        refresh();
                    }
                    else{
                        viewModel.selectAnswer(false);
                        answer1.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
                    }
                }
            }
        });

        answer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewModel.currentQuestionAnswered.getValue()) {
                    if (viewModel.answer2.getValue().getCorrect()) {
                        viewModel.selectAnswer(true);
                        answer2.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        refresh();
                    } else {
                        viewModel.selectAnswer(false);
                        answer2.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
                    }
                }
            }
        });

        answer3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewModel.currentQuestionAnswered.getValue()) {
                    if (viewModel.answer3.getValue().getCorrect()) {
                        viewModel.selectAnswer(true);
                        answer3.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        refresh();
                    } else {
                        viewModel.selectAnswer(false);
                        answer3.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
                    }
                }
            }
        });

        answer4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!viewModel.currentQuestionAnswered.getValue()) {
                    if (viewModel.answer4.getValue().getCorrect()) {
                        viewModel.selectAnswer(true);
                        answer4.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        refresh();
                    } else {
                        viewModel.selectAnswer(false);
                        answer4.setBackgroundColor(getResources().getColor(R.color.colorSecondary));
                    }
                }
            }
        });

        viewModel.currentQuestionAnswered.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean answered) {
                if(!answered){
                    answer1.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    answer2.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    answer3.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                    answer4.setBackgroundColor(getResources().getColor(android.R.color.transparent));
                }
            }
        });
    }

    private void refresh(){
        binding.invalidateAll();
    }

    @Override
    public void onCreateOptionsMenu(
            Menu menu, MenuInflater inflater) {
        menu.findItem(R.id.action_profile).setVisible(false);
    }


}