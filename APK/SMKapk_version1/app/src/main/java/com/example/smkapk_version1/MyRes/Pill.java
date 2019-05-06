package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;

@Entity(tableName = "Pill")
public class Pill {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;

    @ColumnInfo(name = "pillName")
    public String pillName;

    @ColumnInfo(name = "pillCount")
    public int pillCount;

    @ColumnInfo(name = "pillInputDate")
    public String pillInputDate;

    @ColumnInfo(name = "takeRate")  //Як часто слід вживати
    public int takeRate;                   //Кожні takeRate годин

    @ColumnInfo(name = "lastUse")
    public String lastUse;
}
