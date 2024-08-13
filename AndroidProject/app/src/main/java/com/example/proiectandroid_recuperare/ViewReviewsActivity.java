package com.example.proiectandroid_recuperare;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.proiectandroid_recuperare.Adapters.ReviewAdapter;
import com.example.proiectandroid_recuperare.DataModels.Review;
import com.example.proiectandroid_recuperare.Database.AppDatabase;

import java.util.List;

import android.os.Handler;
import android.os.Looper;

public class ViewReviewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_reviews);

        EdgeToEdge.enable(this);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ListView lvReviews = findViewById(R.id.listViewReviews);
        if (lvReviews != null) {
            AppDatabase db = AppDatabase.getDatabase(this);

            List<Review> reviewList = db.reviewDao().getAllReviews();
            lvReviews.setAdapter(new ReviewAdapter(this, reviewList));
        } else {
            Log.e("ViewReviewsActivity", "ListView not found");
        }

        Button closeButton = findViewById(R.id.closeButtonReviews);
        if (closeButton != null) {
            closeButton.setOnClickListener(v -> finish());
        } else {
            Log.e("ViewReviewsActivity", "Close Button not found");
        }
    }
}
