package com.example.cancerhelpmate.ui.activities.quiz;

public class QuizAnswer {
    private String text;
    private boolean correct;

    public QuizAnswer(String text, boolean correct) {
        this.text = text;
        this.correct = correct;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean getCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }
}
