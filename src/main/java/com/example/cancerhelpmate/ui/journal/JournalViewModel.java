package com.example.cancerhelpmate.ui.journal;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.cancerhelpmate.database.journal.JournalDAO;
import com.example.cancerhelpmate.database.journal.JournalDatabase;
import com.example.cancerhelpmate.database.journal.JournalEntry;
import com.example.cancerhelpmate.database.profile.ProfileDAO;
import com.example.cancerhelpmate.database.profile.ProfileDatabase;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class JournalViewModel extends AndroidViewModel {
    private JournalDAO journalDAO;

    public JournalViewModel(@NonNull Application application) {
        super(application);
        journalDAO = JournalDatabase.getDatabase(application).getDAO();
        journalDAO.getJournals();
    }

    public LiveData<List<JournalEntry>> getLiveJournals(){
        return journalDAO.getLiveJournals();
    }

    public List<JournalEntry> getJournals(){
        return journalDAO.getJournals();
    }

    public void addEntry(JournalEntry plannerEntry){
        journalDAO.addEntry(plannerEntry);
    }

    public void updateEntry(JournalEntry plannerEntry){
        journalDAO.updateEntry(plannerEntry);
    }

    public void deleteEntry(JournalEntry plannerEntry){
        journalDAO.deleteEntry(plannerEntry);
    }

    public void refresh(){

    }
}
