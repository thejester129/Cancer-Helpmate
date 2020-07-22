package com.example.cancerhelpmate.ui.daytracker.day_tracker_today.word_tool;

public class DayTrackerBodyWord {
    private String text;
    private String headerText;

    public DayTrackerBodyWord(String text, String headerText) {
        this.text = text;
        this.headerText = headerText;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getHeaderText() {
        return headerText;
    }

    public void setHeaderText(String headerText) {
        this.headerText = headerText;
    }
}
