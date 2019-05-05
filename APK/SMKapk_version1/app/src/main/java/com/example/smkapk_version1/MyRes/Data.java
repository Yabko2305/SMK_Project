package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Data")
public class Data {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "fName")
    private String FName;

    @ColumnInfo(name = "sName")
    private String SName;

    @ColumnInfo(name = "eMail")
    private String eMail;

    @ColumnInfo(name = "pass")
    private String pass;

    @ColumnInfo(name = "rememberMe")
    private boolean remember;

    @ColumnInfo(name = "picNum")
    private int picNum;

    public long     getId() { return this.id; }
    public String   getFName() { return this.FName; }
    public String   getSName() { return this.SName; }
    public String   getEMail() { return this.eMail; }
    public String   getPass() { return pass; }
    public boolean  isRemember() { return this.remember; }
    public int      getPicNum() { return picNum; }

    public void setId(long id) { this.id = id; }
    public void setFName(String FName) { this.FName = FName; }
    public void setSName(String SName) { this.SName = SName; }
    public void setEMail(String eMail) { this.eMail = eMail; }
    public void setPass(String pass) { this.pass = pass; }
    public void setRemember(boolean remember) { this.remember = remember; }
    public void setPicNum(int picNum) { this.picNum = picNum; }

    @ColumnInfo(name = "userPicture")
    private int userPicture;

    public int getUserPicture() { return this.userPicture;    }
    public void setUserPicture(int userPicture) { this.userPicture = userPicture; }

    @ColumnInfo(name = "pillName1")
    int pillName1;
    @ColumnInfo(name = "pillCount1")
    int pillCount1;
    @ColumnInfo(name = "pillInputDate1")
    int pillInputDate1;

    @ColumnInfo(name = "pillName2")
    int pillName2;
    @ColumnInfo(name = "pillCount2")
    int pillCount2;
    @ColumnInfo(name = "pillInputDate2")
    int pillInputDate2;

    @ColumnInfo(name = "pillName3")
    int pillName3;
    @ColumnInfo(name = "pillCount3")
    int pillCount3;
    @ColumnInfo(name = "pillInputDate3")
    int pillInputDate3;

    @ColumnInfo(name = "pillName4")
    int pillName4;
    @ColumnInfo(name = "pillCount4")
    int pillCount4;
    @ColumnInfo(name = "pillInputDate4")
    int pillInputDate4;

    @ColumnInfo(name = "pillName5")
    int pillName5;
    @ColumnInfo(name = "pillCount5")
    int pillCount5;
    @ColumnInfo(name = "pillInputDate5")
    int pillInputDate5;

    @ColumnInfo(name = "pillName6")
    int pillName6;
    @ColumnInfo(name = "pillCount6")
    int pillCount6;
    @ColumnInfo(name = "pillInputDate6")
    int pillInputDate6;
}
