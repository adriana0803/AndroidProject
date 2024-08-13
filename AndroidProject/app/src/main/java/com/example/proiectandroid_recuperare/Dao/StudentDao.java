package com.example.proiectandroid_recuperare.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroid_recuperare.DataModels.Student;

import java.util.List;

@Dao
public interface StudentDao {
    @Query("SELECT * FROM studenti")
    List<Student> getAllStudents();

    @Insert
    void insertAll(List<Student> students);
}
