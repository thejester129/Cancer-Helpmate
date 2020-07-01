package com.example.cancerhelpmate.database.wellbeing;

import androidx.lifecycle.LiveData;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverters;

import com.example.cancerhelpmate.common.DateManager;
import com.example.cancerhelpmate.database.recipes.RecipeEntry;

import org.jetbrains.annotations.NotNull;

import java.util.List;

@Entity(tableName = "wellbeing")
public class WellbeingEntry {

    @PrimaryKey
    @NotNull
    @ColumnInfo(name = "wellbeing_date")
    private String date;
    @ColumnInfo(name = "wellbeing_breakfast_recipe")
    private RecipeEntry breakfast_recipe;
    @ColumnInfo(name = "wellbeing_lunch_recipe")
    private RecipeEntry lunch_recipe;
    @ColumnInfo(name = "wellbeing_dinner_recipe")
    private RecipeEntry dinner_recipe;
    @ColumnInfo(name = "wellbeing_supper_recipe")
    private RecipeEntry supper_recipe;
    @ColumnInfo(name = "wellbeing_extra_recipe")
    private RecipeEntry extra_recipe;


    public WellbeingEntry(){
        this.date = DateManager.getTodayAsString();
    }

    public RecipeEntry getBreakfast_recipe() {
        return breakfast_recipe;
    }

    public void setBreakfast_recipe(RecipeEntry breakfast_recipe) {
        this.breakfast_recipe = breakfast_recipe;
    }

    @NotNull
    public String getDate() {
        return date;
    }

    public void setDate(@NotNull String date) {
        this.date = date;
    }

    public RecipeEntry getLunch_recipe() {
        return lunch_recipe;
    }

    public void setLunch_recipe(RecipeEntry lunch_recipe) {
        this.lunch_recipe = lunch_recipe;
    }

    public RecipeEntry getDinner_recipe() {
        return dinner_recipe;
    }

    public void setDinner_recipe(RecipeEntry dinner_recipe) {
        this.dinner_recipe = dinner_recipe;
    }

    public RecipeEntry getSupper_recipe() {
        return supper_recipe;
    }

    public void setSupper_recipe(RecipeEntry supper_recipe) {
        this.supper_recipe = supper_recipe;
    }

    public RecipeEntry getExtra_recipe() {
        return extra_recipe;
    }

    public void setExtra_recipe(RecipeEntry extra_recipe) {
        this.extra_recipe = extra_recipe;
    }
}
