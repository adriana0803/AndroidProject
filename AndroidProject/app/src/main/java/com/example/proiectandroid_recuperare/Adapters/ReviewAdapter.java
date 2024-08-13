package com.example.proiectandroid_recuperare.Adapters;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.proiectandroid_recuperare.DataModels.Review;
import com.example.proiectandroid_recuperare.R;

import java.util.List;

public class ReviewAdapter extends ArrayAdapter<Review> {

    private Context context;
    private List<Review> reviews;

    public ReviewAdapter(Context context, List<Review> reviews) {
        super(context, 0, reviews);
        this.context = context;
        this.reviews = reviews;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(context).inflate(R.layout.item_review, parent, false);
        }

        Review currentReview = reviews.get(position);

        TextView idReviewTextView = listItemView.findViewById(R.id.idReviewTextView);
        idReviewTextView.setText(idReviewTextView.getText().toString()+String.valueOf(currentReview.getId()));

        TextView cursTextView = listItemView.findViewById(R.id.cursRecenzatTextView);
        cursTextView.setText(cursTextView.getText().toString()+currentReview.getCurs());

        TextView reviewTextView = listItemView.findViewById(R.id.reviewTextView);
        reviewTextView.setText(reviewTextView.getText().toString()+currentReview.getReviewText());

        if (position % 2 == 0) {
            listItemView.setBackgroundColor(Color.parseColor("#00D2FF"));
        } else {
            listItemView.setBackgroundColor(Color.parseColor("#9874a7"));
        }

        return listItemView;
    }
}

