package com.example.cancerhelpmate.database.wellbeing;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.cancerhelpmate.database.recipes.RecipeEntry;

import java.util.List;

@Dao
public interface WellbeingDAO {

    @Insert
    public long addEntry(WellbeingEntry entry);

    @Update
    public void updateEntry(WellbeingEntry entry);

    @Delete
    public void deleteEntry(WellbeingEntry entry);

    @Query("select * from wellbeing where wellbeing_date == :date")
    public WellbeingEntry getItem(String date);

    @Query("select * from wellbeing where wellbeing_date == :date")
    public LiveData<WellbeingEntry> getLiveItem(String date);

    @Query("select wellbeing_breakfast_recipe from wellbeing where wellbeing_date == :date")
    public LiveData<RecipeEntry> getLiveBreakfastRecipe(String date);

    @Query("select wellbeing_lunch_recipe from wellbeing where wellbeing_date == :date")
    public LiveData<RecipeEntry> getLiveLunchRecipe(String date);

    @Query("select wellbeing_dinner_recipe from wellbeing where wellbeing_date == :date")
    public LiveData<RecipeEntry> getLiveDinnerRecipe(String date);

    @Query("select wellbeing_supper_recipe from wellbeing where wellbeing_date == :date")
    public LiveData<RecipeEntry> getLiveSupperRecipe(String date);

    @Query("select wellbeing_extra_recipe from wellbeing where wellbeing_date == :date")
    public LiveData<RecipeEntry> getLiveExtraRecipe(String date);

    @Query("update wellbeing set wellbeing_breakfast_recipe = :recipe where wellbeing_date == :date")
    public void setBreakfastRecipe(RecipeEntry recipe, String date);

    @Query("update wellbeing set wellbeing_lunch_recipe = :recipe where wellbeing_date == :date")
    public void setLunchRecipe(RecipeEntry recipe, String date);

    @Query("update wellbeing set wellbeing_dinner_recipe = :recipe where wellbeing_date == :date")
    public void setDinnerRecipe(RecipeEntry recipe, String date);

    @Query("update wellbeing set wellbeing_supper_recipe = :recipe where wellbeing_date == :date")
    public void setSupperRecipe(RecipeEntry recipe, String date);

    @Query("update wellbeing set wellbeing_extra_recipe = :recipe where wellbeing_date == :date")
    public void setExtraRecipe(RecipeEntry recipe, String date);

    @Query("update wellbeing set wellbeing_steps_done = :steps where wellbeing_date == :date")
    public void setSteps(int steps, String date);

    @Query("select wellbeing_steps_done from wellbeing where wellbeing_date == :date")
    public LiveData<Integer> getSteps(String date);

    @Query("select * from wellbeing")
    public List<WellbeingEntry> getItems();

    @Query("select * from wellbeing")
    public LiveData<List<WellbeingEntry>> getLiveItems();

    @Query("DELETE FROM wellbeing")
    public void deleteTable();
}