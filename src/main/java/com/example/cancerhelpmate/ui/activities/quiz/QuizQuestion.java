package com.example.cancerhelpmate.ui.activities.quiz;

import java.util.List;

public class QuizQuestion {
    private String question;
    private List<QuizAnswer> answers;

    public QuizQuestion(String question, List<QuizAnswer> answers) {
        this.question = question;
        this.answers = answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<QuizAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<QuizAnswer> answers) {
        this.answers = answers;
    }


}
