package com.example.calculator;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.calculator.Db.HistoryEntity;
import com.example.calculator.Db.Repository;

import java.util.List;

public class HistoryViewModel extends AndroidViewModel {

    private Repository repo;
    private LiveData<List<HistoryEntity>> getAll;

    public HistoryViewModel(@NonNull Application application) {
        super(application);
        repo = new Repository(application);
        getAll = repo.getAllHs();
    }

    public void insert (HistoryEntity he)
    {
        repo.insert(he);
    }
    public void delete (HistoryEntity he)
    {
        repo.delete(he);
    }
    public LiveData<List<HistoryEntity>> getAllHs ()
    {
        return getAll;
    }
}
