package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

@Dao
public interface DataDao {
    @Insert
    void insert(Data data);

    @Delete
    void delete(Data data);

    @Query("SELECT * FROM Data")
    List<Data> getAll();

    @Query("SELECT * FROM Data WHERE eMail = :mail")
    Data getByMail(String mail);
}
