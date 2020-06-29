package com.example.cancerhelpmate.ui.journal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.cancerhelpmate.database.journal.JournalDAO;
import com.example.cancerhelpmate.database.journal.JournalDatabase;
import com.example.cancerhelpmate.database.journal.JournalEntry;

import java.util.List;

public class JournalViewModel extends AndroidViewModel {
    private JournalDAO dao;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        dao = JournalDatabase.getDatabase(application).getDAO();
        dao.getJournals();
    }

    public LiveData<List<JournalEntry>> getLiveJournals(){
        return dao.getLiveJournals();
    }

    public List<JournalEntry> getJournals(){
        return dao.getJournals();
    }

    public void addEntry(JournalEntry plannerEntry){
        dao.addEntry(plannerEntry);
    }

    public void updateEntry(JournalEntry plannerEntry){
        dao.updateEntry(plannerEntry);
    }

    public void deleteEntry(JournalEntry plannerEntry){
        dao.deleteEntry(plannerEntry);
    }

    public void refresh(){

    }

    public void resetDatabase(){
        dao.deleteTable();
    }
}
