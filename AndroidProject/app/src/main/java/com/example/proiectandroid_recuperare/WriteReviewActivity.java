package com.example.proiectandroid_recuperare;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid_recuperare.Dao.ReviewDao;
import com.example.proiectandroid_recuperare.DataModels.Review;
import com.example.proiectandroid_recuperare.Database.AppDatabase;
import com.google.android.material.textfield.TextInputEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class WriteReviewActivity extends AppCompatActivity {
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_write_review);

        executorService = Executors.newSingleThreadExecutor();

        List<String> spinnerEntries = readDenumireFromJson(getApplicationContext(),"cursuri.json");

        Spinner spn = findViewById(R.id.spinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, spinnerEntries);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn.setAdapter(adapter);

        findViewById(R.id.saveButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppDatabase db = AppDatabase.getDatabase(getApplicationContext());
                TextInputEditText tiet = findViewById(R.id.editText);
                String tiet_text = tiet.getText().toString();

                int oldSize = db.reviewDao().getAllReviews().size();

                db.reviewDao().insert(new Review(spn.getSelectedItem().toString(), tiet_text));
                if(oldSize+1 == db.reviewDao().getAllReviews().size()){
                    Toast.makeText(getApplicationContext(),"Review inserted in the database, current nr of reviews:"+(oldSize+1),Toast.LENGTH_LONG).show();
                    //Toast.makeText(getApplicationContext(),"Nr of students:"+db.studentDao().getAllStudents().size(),Toast.LENGTH_LONG).show();
                }
                finish();
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executorService.shutdown();
    }

    public static List<String> readDenumireFromJson(Context context, String fileName) {
        List<String> denumireList = new ArrayList<>();

        try {
            InputStream inputStream = context.getAssets().open(fileName);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String denumire = jsonObject.getString("denumire");
                denumireList.add(denumire);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return denumireList;
    }

}