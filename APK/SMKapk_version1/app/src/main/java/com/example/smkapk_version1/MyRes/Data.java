package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "Data")
public class Data {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private long id;

    @ColumnInfo(name = "eMail")
    private String eMail;

    @ColumnInfo(name = "pass")
    private String pass;

    public long getId() { return this.id; }
    public String getEMail() { return this.eMail; }
    public String getPass() { return pass; }

    public void setId(long id) { this.id = id; }
    public void setEMail(String eMail) { this.eMail = eMail; }
    public void setPass(String pass) { this.pass = pass; }
}
