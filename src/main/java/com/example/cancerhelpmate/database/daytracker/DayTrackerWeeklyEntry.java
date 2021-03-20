package com.example.cancerhelpmate.database.daytracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


import com.example.cancerhelpmate.common.DateManager;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "day_trackers_weekly")
public class DayTrackerWeeklyEntry {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "day_tracker_weekly_date")
    private String date;
    @ColumnInfo(name = "day_tracker_weekly_quality_of_life")
    private float quality_of_life;
    @ColumnInfo(name = "day_tracker_weekly_health")
    private float health;
    @ColumnInfo(name = "day_tracker_weekly_information")
    private float information;
    @ColumnInfo(name = "day_tracker_weekly_comment")
    private String comment;

    @Ignore
    public DayTrackerWeeklyEntry() {
        this.date = DateManager.getTodayAsString();
    }

    public DayTrackerWeeklyEntry( float quality_of_life, float health, float information, String comment) {
        this.date = DateManager.getTodayAsString();
        this.quality_of_life = quality_of_life;
        this.health = health;
        this.information = information;
        this.comment = comment;
    }

    @NotNull
    public String getDate() {
        return date;
    }

    public String getDayMonthYearDate() {
        return DateManager.dateToDayMonthString(date);
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public float getQuality_of_life() {
        return quality_of_life;
    }

    public void setQuality_of_life(float quality_of_life) {
        this.quality_of_life = quality_of_life;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public float getInformation() {
        return information;
    }

    public void setInformation(float information) {
        this.information = information;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}

