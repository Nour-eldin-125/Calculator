package com.example.calculator.Db;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

@androidx.room.Entity (tableName = "history_table")
public class HistoryEntity {
    @PrimaryKey (autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo (name = "Calculation")
    @NonNull
    private String calculation;

    @ColumnInfo (name = "result")
    @NonNull
    private String result;

    public HistoryEntity(@NonNull String calculation, @NonNull String result) {
        this.calculation = calculation;
        this.result = result;
    }
    public void setId(int id)
    {
        this.id=id;
    }

    public int getId() {
        return id;
    }

    @NonNull
    public String getCalculation() {
        return calculation;
    }

    @NonNull
    public String getResult() {
        return result;
    }
}
