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
    public LiveData<byte []> getLivePicture();

    @Query("select profile_initialised from profile where profile_id == 0")
    public LiveData<Boolean> getLiveInitialised();

    @Query("update profile set profile_name = :name where profile_id == 0")
    public void setName(String name);

    @Query("update profile set profile_picture = :image where profile_id == 0")
    public void setImage(byte[] image);

    @Query("update profile set profile_initialised = :initialised where profile_id == 0")
    public void setInitialised(boolean initialised);


    @Query("DELETE FROM profile")
    public void deleteTable();

}