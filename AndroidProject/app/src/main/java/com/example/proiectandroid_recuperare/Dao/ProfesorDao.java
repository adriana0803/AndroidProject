package com.example.proiectandroid_recuperare.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroid_recuperare.DataModels.Profesor;

import java.util.List;

@Dao
public interface ProfesorDao {
    @Query("SELECT * FROM profesori")
    List<Profesor> getAllProfesori();

    @Insert
    void insertAll(List<Profesor> profesori);
}
