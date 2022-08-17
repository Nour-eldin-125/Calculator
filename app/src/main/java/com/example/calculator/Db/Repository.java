package com.example.calculator.Db;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class Repository {

    private Dao hsDao;

    private LiveData<List<HistoryEntity>> getAll;

    public Repository (Application app)
    {
        HisDB db = HisDB.getInstance(app);
        hsDao = db.hsDao();
        getAll = hsDao.getAllHs();
    }

    //insert
    public void insert (HistoryEntity he)
    {
        new InsertAsyncTask(hsDao).execute(he);
    }

    //delete
    public void delete (HistoryEntity he)
    {
        new DeleteAsyncTask(hsDao).execute(he);
    }

    //getAll
    public  LiveData<List<HistoryEntity>> getAllHs(){
        return getAll;
    }

    private static class InsertAsyncTask extends AsyncTask<HistoryEntity , Void,Void>
    {
        private Dao hsDao;

        public InsertAsyncTask(Dao hsDao){
            this.hsDao = hsDao;
        }
        @Override
        protected Void doInBackground(HistoryEntity... historyEntities) {
            hsDao.insert(historyEntities[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<HistoryEntity , Void,Void>
    {
        private Dao hsDao;

        public DeleteAsyncTask(Dao hsDao){
            this.hsDao = hsDao;
        }
        @Override
        protected Void doInBackground(HistoryEntity... historyEntities) {
            hsDao.delete(historyEntities[0]);
            return null;
        }
    }

}
