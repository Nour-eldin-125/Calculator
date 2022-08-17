package com.example.calculator.Db;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@androidx.room.Dao
public interface Dao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(HistoryEntity his);

    @Delete
    void delete (HistoryEntity his);

    @Query("SELECT * FROM history_table")
    LiveData<List<HistoryEntity>> getAllHs();

}
