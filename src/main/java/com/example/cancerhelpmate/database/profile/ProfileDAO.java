package com.example.cancerhelpmate.database.profile;


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface ProfileDAO {

    @Insert
    public long addEntry(ProfileEntry profile);

    @Update
    public void updateEntry(ProfileEntry profile);

    @Delete
    public void deleteEntry(ProfileEntry profile);


    @Query("select * from profile where profile_id == 0 limit 1")
    public ProfileEntry getProfile();

    @Query("select * from profile where profile_id == 0")
    public LiveData<ProfileEntry> getLiveProfile();

    @Query("select profile_picture from profile where profile_id == 0")
    public LiveData<Integer> getLivePicture();

    @Query("select profile_initialised from profile where profile_id == 0")
    public LiveData<Boolean> getLiveInitialised();

    @Query("update profile set profile_first_name = :name where profile_id == 0")
    public void setFirstName(String name);

    @Query("update profile set profile_second_name = :name where profile_id == 0")
    public void setSecondName(String name);


    @Query("update profile set profile_initialised = :initialised where profile_id == 0")
    public void setInitialised(boolean initialised);

    @Query("update profile set profile_male = :male where profile_id == 0")
    public void setMale(boolean male);

    @Query("update profile set profile_female = :female where profile_id == 0")
    public void setFemale(boolean female);

    @Query("update profile set profile_other = :other where profile_id == 0")
    public void setOther(boolean other);

    @Query("update profile set profile_picture = :picture where profile_id == 0")
    public void setPicture(int picture);

    @Query("DELETE FROM profile")
    public void deleteTable();

}