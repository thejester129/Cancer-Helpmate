package com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool;

import java.util.List;

public class DayTrackerHeaderWord {
    private String text;
    private List<DayTrackerBodyWord>words;
    private boolean selected;

    public DayTrackerHeaderWord(String text, List<DayTrackerBodyWord> words) {
        this.text = text;
        this.words = words;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<DayTrackerBodyWord> getWords() {
        return words;
    }

    public void setWords(List<DayTrackerBodyWord> words) {
        this.words = words;
    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
