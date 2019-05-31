package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import java.sql.Date;
import java.sql.Time;
import java.time.MonthDay;

@Entity(tableName = "Pill")
public class Pill {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id;     //Do not init!

    @ColumnInfo(name = "patientMail")
    public String patientMail;

    @ColumnInfo(name = "pillName")
    public String pillName;

    @ColumnInfo(name = "pillCount")
    public int pillCount;

    @ColumnInfo(name = "placeInKit")
    public int placeInKit;

    @ColumnInfo(name = "pillInputDate")
    public String pillInputDate;

    @ColumnInfo(name = "takeRate")
    public int takeRate;

    @ColumnInfo(name = "lastUse")
    public long lastUse;

    public int courseLen;
    public long startDay;
    public int pillsPerDay;
    public int pillsPerTake;
    public int pillsTakenToday;
    public int timeBetweenTakes;
}