package com.example.proiectandroid_recuperare.DataModels;


import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity(tableName = "reviews")
public class Review {
    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;
    private String curs;
    private String reviewText;

    public Review() {
    }

    public Review(String curs, String reviewText) {
        this.curs = curs;
        this.reviewText = reviewText;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCurs() {
        return curs;
    }

    public void setCurs(String curs) {
        this.curs = curs;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }
}
