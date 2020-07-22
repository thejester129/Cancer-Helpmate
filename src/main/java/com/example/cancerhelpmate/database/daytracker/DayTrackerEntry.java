package com.example.cancerhelpmate.database.daytracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotionItem;
import com.example.cancerhelpmate.ui.daytracker.emotions.DayTrackerEmotions;

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
    @ColumnInfo(name = "day_tracker_fatigue_level")
    private int fatigueLevel;
    @ColumnInfo(name = "day_tracker_appetite_level")
    private int appetiteLevel;
    @ColumnInfo(name = "day_tracker_treatment")
    private boolean treatment;
    @ColumnInfo(name = "day_tracker_description")
    private String description;

    public DayTrackerEntry() {
        this.date = DateManager.getTodayAsString();
        this.emotion = new DayTrackerEmotions.DayTrackerEmotionHappy();
        this.treatment = true;
    }

    @Ignore
    public DayTrackerEntry(@NotNull String date, int painLevel,int fatigueLevel, int appetiteLevel, DayTrackerEmotionItem emotion,boolean treatment) {
        this.date = date;
        this.painLevel = painLevel;
        this.fatigueLevel = fatigueLevel;
        this.appetiteLevel = appetiteLevel;
        this.emotion = emotion;
        this.treatment = treatment;
    }

    @Ignore
    public DayTrackerEntry(@NotNull String date, int painLevel,int fatigueLevel,int appetiteLevel, DayTrackerEmotionItem emotion, String description) {
        this.date = date;
        this.painLevel = painLevel;
        this.fatigueLevel = fatigueLevel;
        this.appetiteLevel = appetiteLevel;
        this.emotion = emotion;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getFatigueLevel() {
        return fatigueLevel;
    }

    public void setFatigueLevel(int fatigueLevel) {
        this.fatigueLevel = fatigueLevel;
    }

    public int getAppetiteLevel() {
        return appetiteLevel;
    }

    public void setAppetiteLevel(int appetiteLevel) {
        this.appetiteLevel = appetiteLevel;
    }

    public boolean isTreatment() {
        return treatment;
    }

    public void setTreatment(boolean treatment) {
        this.treatment = treatment;
    }

    public void toggleTreatment(){
        this.treatment = !treatment;
    }
}
