package com.example.calculator.Db;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = HistoryEntity.class , version = 1 )
public abstract class HisDB extends RoomDatabase {

    private static HisDB instance;

    public abstract Dao hsDao();

    public static synchronized HisDB getInstance(Context context)
    {
        if (instance ==null)
        {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    HisDB.class,"history_table").fallbackToDestructiveMigration()
                    .addCallback(dbCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback()
    {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();
        }

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void>
    {
        private Dao hsDao;

        PopulateDataAsyncTask(HisDB db)
        {
            hsDao = db.hsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }
    }
}
