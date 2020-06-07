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

    @Query("update profile set profile_name = :name where profile_id == 0")
    public void setName(String name);

    @Query("update profile set profile_treatment_chemotherapy = :treatment where profile_id == 0")
    public void setChemotherapyTreatment(boolean treatment);

    @Query("update profile set profile_treatment_radiotherapy = :treatment where profile_id == 0")
    public void setRadiotherapyTreatment(boolean treatment);

    @Query("update profile set profile_treatment_surgery = :treatment where profile_id == 0")
    public void setSurgeryTreatment(boolean treatment);

    @Query("update profile set profile_treatment_bone_marrow = :treatment where profile_id == 0")
    public void setBoneMarrowTreatment(boolean treatment);

    @Query("update profile set profile_treatment_other = :treatment where profile_id == 0")
    public void setOtherTreatment(boolean treatment);

    @Query("update profile set profile_diagnosis = :diagnosis where profile_id == 0")
    public void setDiagnosis(String diagnosis);

    @Query("update profile set profile_start_date = :date where profile_id == 0")
    public void setStartTreatmentDate(String date);

    @Query("update profile set profile_end_date = :date where profile_id == 0")
    public void setEndTreatmentDate(String date);

    @Query("update profile set profile_initialised = :initialised where profile_id == 0")
    public void setInitialised(boolean initialised);

    @Query("select profile_diagnosis from profile where profile_id == 0")
    public LiveData<String> getDiagnosis();

    @Query("DELETE FROM profile")
    public void deleteTable();

}