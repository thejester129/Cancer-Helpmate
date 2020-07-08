package com.example.cancerhelpmate.database.daytracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionItem;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "day_trackers")
public class DayTrackerEntry {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "day_tracker_date")
    private String date;
    @ColumnInfo(name = "day_tracker_emotion")
    private DayTrackerEmotionItem emotion;
    @ColumnInfo(name = "day_tracker_pain_level")
    private int painLevel;


    public DayTrackerEntry() {
        this.date = DateManager.getTodayAsString();
    }

    @Ignore
    public DayTrackerEntry(@NotNull String date, int painLevel, DayTrackerEmotionItem emotion) {
        this.date = date;
        this.painLevel = painLevel;
        this.emotion = emotion;
    }

    @NotNull
    public String getDate() {
        return date;
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public DayTrackerEmotionItem getEmotion() {
        return emotion;
    }

    public void setEmotion(DayTrackerEmotionItem emotion) {
        this.emotion = emotion;
    }

    public int getPainLevel() {
        return painLevel;
    }

    public void setPainLevel(int painLevel) {
        this.painLevel = painLevel;
    }

}
