package com.example.cancerhelpmate.database.recipes;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface RecipeDAO {

    @Insert
    public long addEntry(RecipeEntry entry);

    @Update
    public void updateEntry(RecipeEntry entry);

    @Delete
    public void deleteEntry(RecipeEntry entry);

    @Query("select * from recipes where recipe_id == :id")
    public RecipeEntry getItem(int id);

    @Query("select * from recipes where recipe_id == :id")
    public LiveData<RecipeEntry> getLiveItem(int id);

    @Query("select * from recipes")
    public List<RecipeEntry> getAllItems();

    @Query("select * from recipes")
    public LiveData<List<RecipeEntry>> getAllLiveItems();

    @Query("select count(*) from recipes")
    public LiveData<Integer> getLiveCount();

    @Query("DELETE FROM recipes")
    public void deleteTable();
}