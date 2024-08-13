package com.example.proiectandroid_recuperare.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroid_recuperare.DataModels.AdaptedCurs;
import com.example.proiectandroid_recuperare.DataModels.Profesor;

import java.util.List;

@Dao
public interface CursDao {
    @Query("SELECT * FROM cursuri")
    List<AdaptedCurs> getAllCursuri();

    @Insert
    void insertAll(List<AdaptedCurs> curs);
}
