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

    public long getId() { return this.id; }
    public String getFName() { return this.FName; }
    public String getSName() { return this.SName; }
    public String getEMail() { return this.eMail; }
    public String getPass() { return pass; }

    public void setId(long id) { this.id = id; }
    public void setFName(String FName) { this.FName = FName; }
    public void setSName(String SName) { this.SName = SName; }
    public void setEMail(String eMail) { this.eMail = eMail; }
    public void setPass(String pass) { this.pass = pass; }
}
