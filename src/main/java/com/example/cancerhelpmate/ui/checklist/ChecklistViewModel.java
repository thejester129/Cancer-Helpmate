package com.example.cancerhelpmate.ui.checklist;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import com.example.cancerhelpmate.database.checklist.ChecklistDAO;
import com.example.cancerhelpmate.database.checklist.ChecklistDatabase;
import com.example.cancerhelpmate.database.checklist.ChecklistEntry;

import java.util.List;

public class ChecklistViewModel extends AndroidViewModel {
    private ChecklistDAO dao;
    public ChecklistEntry editEntry = new ChecklistEntry();

    public ChecklistViewModel(@NonNull Application application) {
        super(application);
        dao = ChecklistDatabase.getDatabase(application).getDAO();
        dao.getAllItems();
    }

    public void refresh(){

    }

    public void toggleChecked(ChecklistEntry entry){
        entry.setChecked(!entry.isChecked());
        dao.updateEntry(entry);
    }

    public LiveData<List<ChecklistEntry>> getLiveItems(){
        return dao.getAllLiveItems();
    }

    public LiveData<Integer> getLiveItemCount(){
        return dao.getLiveCount();
    }

    public ChecklistEntry getItem(String title){
        return dao.getItem(title);
    }

    public List<ChecklistEntry> getItems(){
        return dao.getAllItems();
    }

    public void addEntry(ChecklistEntry entry){
        dao.addEntry(entry);
    }

    public void updateEntry(ChecklistEntry entry){
        dao.updateEntry(entry);
    }

    public void saveEditEntry(){
        addEntry(editEntry);
        editEntry.setTitle("");
    }

    public void deleteEntry(ChecklistEntry entry){
        dao.deleteEntry(entry);
    }

    public void resetDatabase(){
        dao.deleteTable();
    }

}
