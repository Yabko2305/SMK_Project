package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insert(Data data);

    @Delete
    void delete(Data data);

    @Update
    void update(Data data);

    @Query("UPDATE Data SET rememberMe = 'false'")
    void changeAllToFalse();

    @Query("SELECT * FROM Data")
    List<Data> getAll();

    @Query("SELECT * FROM Data WHERE eMail = :mail")
    Data getByMail(String mail);

    @Query("SELECT * FROM Data WHERE rememberMe = :bool")
    Data getByBoolean(Boolean bool);
}
