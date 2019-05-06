package com.example.smkapk_version1.MyRes;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao()
public interface PillDao {
    @Insert
    void insert(Pill pill);

    @Delete
    void delete(Pill pill);

    @Update
    void update(Pill pill);

    @Query("SELECT * FROM Pill")
    List<Pill> getAll();

    @Query("SELECT * FROM Pill WHERE id= :id")
    Pill getById(int id);

    @Query("SELECT * FROM Pill WHERE patientMail= :patientMail")
    Pill getByMail(String patientMail);
}
