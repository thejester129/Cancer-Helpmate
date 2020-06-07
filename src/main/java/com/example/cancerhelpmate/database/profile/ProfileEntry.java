package com.example.cancerhelpmate.database.profile;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cancerhelpmate.common.DateManager;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "profile")
public class ProfileEntry {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name="profile_id")
    private int id;
    @ColumnInfo(name="profile_name")
    private String name;
    @ColumnInfo(name="profile_start_date")
    private String start_date;
    @ColumnInfo(name="profile_end_date")
    private String end_date;
    @ColumnInfo(name="profile_diagnosis")
    private String diagnosis;
    @ColumnInfo(name="profile_treatment_chemotherapy")
    private boolean treatment_chemotherapy;
    @ColumnInfo(name="profile_treatment_radiotherapy")
    private boolean treatment_radiotherapy;
    @ColumnInfo(name="profile_treatment_surgery")
    private boolean treatment_surgery;
    @ColumnInfo(name="profile_treatment_bone_marrow")
    private boolean treatment_bone_marrow;
    @ColumnInfo(name="profile_treatment_other")
    private boolean treatment_other;
    @ColumnInfo(name="profile_initialised")
    private boolean initialised;


    @Ignore
    public ProfileEntry(){

    }

    public ProfileEntry(@NotNull int id) {
        this.id = id;
        this.name = "";
        this.initialised = false;
        this.start_date = DateManager.getTodayAsString();
        this.end_date = DateManager.getTodayAsString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public boolean isTreatment_chemotherapy() {
        return treatment_chemotherapy;
    }

    public void setTreatment_chemotherapy(boolean treatment_chemotherapy) {
        this.treatment_chemotherapy = treatment_chemotherapy;
    }

    public boolean isTreatment_radiotherapy() {
        return treatment_radiotherapy;
    }

    public void setTreatment_radiotherapy(boolean treatment_radiotherapy) {
        this.treatment_radiotherapy = treatment_radiotherapy;
    }

    public boolean isTreatment_surgery() {
        return treatment_surgery;
    }

    public void setTreatment_surgery(boolean treatment_surgery) {
        this.treatment_surgery = treatment_surgery;
    }

    public boolean isTreatment_bone_marrow() {
        return treatment_bone_marrow;
    }

    public void setTreatment_bone_marrow(boolean treatment_bone_marrow) {
        this.treatment_bone_marrow = treatment_bone_marrow;
    }

    public boolean isInitialised() {
        return initialised;
    }

    public void setInitialised(boolean initialised) {
        this.initialised = initialised;
    }

    public boolean isTreatment_other() {
        return treatment_other;
    }

    public void setTreatment_other(boolean treatment_other) {
        this.treatment_other = treatment_other;
    }
}
