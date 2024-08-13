package com.example.proiectandroid_recuperare.Dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.proiectandroid_recuperare.DataModels.Review;

import java.util.List;

@Dao
public interface ReviewDao {
    @Query("SELECT * FROM reviews")
    List<Review> getAllReviews();

    @Insert
    void insert(Review review);
}
