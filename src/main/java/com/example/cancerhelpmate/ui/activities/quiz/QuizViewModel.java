package com.example.cancerhelpmate.ui.activities.quiz;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class QuizViewModel extends ViewModel {
    public MutableLiveData<Boolean> quizStarted = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> quizFinished = new MutableLiveData<>(false);
    public MutableLiveData<Boolean> currentQuestionAnswered = new MutableLiveData<>(false);
    private Queue<QuizQuestion> currentQuestions;
    public MutableLiveData<QuizQuestion> currentQuestion = new MutableLiveData<>();
    public MutableLiveData<QuizAnswer> answer1 = new MutableLiveData<>();
    public MutableLiveData<QuizAnswer> answer2 = new MutableLiveData<>();
    public MutableLiveData<QuizAnswer> answer3 = new MutableLiveData<>();
    public MutableLiveData<QuizAnswer> answer4 = new MutableLiveData<>();
    public MutableLiveData<Integer> currentScore = new MutableLiveData<>(0);
    public MutableLiveData<Integer> totalScore = new MutableLiveData<>(getQuizQuestions().size());


    public void start(){
        quizStarted.setValue(true);
        List<QuizQuestion>questions = getQuizQuestions();
        Collections.shuffle(questions);
        currentQuestions = new LinkedList<>(questions);
        totalScore.setValue(questions.size());
        nextQuestion();
    }

    public void nextQuestion(){
        if(currentQuestions.size() > 0){
            QuizQuestion question = currentQuestions.remove();
            currentQuestion.setValue(question);

            List<QuizAnswer>answers = question.getAnswers();
            Collections.shuffle(answers);

            answer1.setValue(answers.get(0));
            answer2.setValue(answers.get(1));
            answer3.setValue(answers.get(2));
            answer4.setValue(answers.get(3));

            currentQuestionAnswered.setValue(false);
        }
        else{
            quizStarted.setValue(false);
            quizFinished.setValue(true);
        }

    }

    public void selectAnswer(boolean correct){
        if(currentQuestionAnswered.getValue()){

        }
        else{
            currentQuestionAnswered.setValue(true);
            if(correct){
                currentScore.setValue(currentScore.getValue()+1);
            }

        }
    }

    public boolean showStartText(){
        return !quizStarted.getValue() && !quizFinished.getValue();
    }

    public List<QuizQuestion> getQuizQuestions(){
        List<QuizQuestion> questions = new ArrayList<>();

        questions.add(new QuizQuestion("Having high levels of calcium is called",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("Hypercalcaemia", true));
                    add(new QuizAnswer("Extracalceosis",false));
                    add(new QuizAnswer("Calsicotis",false));
                    add(new QuizAnswer("Extracurricular",false));
                }}));

        questions.add(new QuizQuestion("What is the top preventable cause of cancer",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("Smoking", true));
                    add(new QuizAnswer("Obesity",false));
                    add(new QuizAnswer("Alcohol",false));
                    add(new QuizAnswer("UV Radiation",false));
                }}));

        questions.add(new QuizQuestion("How many minutes of moderate exercise is recommended for an average adult per week?",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("150", true));
                    add(new QuizAnswer("60",false));
                    add(new QuizAnswer("90",false));
                    add(new QuizAnswer("30",false));
                }}));

        questions.add(new QuizQuestion("Which of these helps you sleep better?",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("Going to bed at the same time", true));
                    add(new QuizAnswer("Drinking caffeine before bed",false));
                    add(new QuizAnswer("Not eating anything before bed",false));
                    add(new QuizAnswer("Nap during the day",false));
                }}));

        questions.add(new QuizQuestion("Which of these aids in stopping sickness?",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("Ginger", true));
                    add(new QuizAnswer("Cola",false));
                    add(new QuizAnswer("Sugar",false));
                    add(new QuizAnswer("Chewing gum",false));
                }}));

        questions.add(new QuizQuestion("How much water is recommended to drink daily?",
                new ArrayList<QuizAnswer>(){{
                    add(new QuizAnswer("2.2 litres", true));
                    add(new QuizAnswer("1.7 litres",false));
                    add(new QuizAnswer("2 litres",false));
                    add(new QuizAnswer("3 litres",false));
                }}));



        return questions;
    }
}
