package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.sql.Date;
import java.sql.Time;
import java.time.MonthDay;

@Entity(tableName = "Pill")
public class Pill {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    public long id =0;

    @ColumnInfo(name = "patientMail")
    public String patientMail = null;

    @ColumnInfo(name = "pillName")
    public String pillName = null;

    @ColumnInfo(name = "pillCount")
    public int pillCount = 0;

    @ColumnInfo(name = "placeInKit")
    public int placeInKit = 0;

    @ColumnInfo(name = "pillInputDate")
    public String pillInputDate = null;

    @ColumnInfo(name = "takeRate")
    public int takeRate = 0;

    @ColumnInfo(name = "lastUse")
    public String lastUse = null;

    public String courseLen;
    public String startDay;
    public int pillsPerDay;
    public int pillsTakenToday;
    public int timeBetweenTakes;
}
