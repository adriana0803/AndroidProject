package com.example.proiectandroid_recuperare.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.proiectandroid_recuperare.CursDetailsActivity;
import com.example.proiectandroid_recuperare.DataModels.Curs;
import com.example.proiectandroid_recuperare.DataModels.Student;
import com.example.proiectandroid_recuperare.R;

import java.io.Serializable;
import java.util.List;

public class CursAdapter extends ArrayAdapter<Curs> {

    private Context context;

    public CursAdapter(Context context, List<Curs> cursuri) {
        super(context, 0, cursuri);
        this.context = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final Curs curs = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_curs, parent, false);
        }

        TextView denumireTextView = convertView.findViewById(R.id.denumire);
        TextView profesorTextView = convertView.findViewById(R.id.profesor);
        TextView anUniversitarTextView = convertView.findViewById(R.id.an_universitar);
        TextView studentiInrolatiTextView = convertView.findViewById(R.id.studenti_inrolati);
        ImageView eyeIcon = convertView.findViewById(R.id.eye_icon);

        denumireTextView.setText(curs.getDenumire());
        profesorTextView.setText("Profesor: " + curs.getProfesorResponsabil().getNume() + " " + curs.getProfesorResponsabil().getPrenume());
        anUniversitarTextView.setText("An universitar: " + curs.getAnUniversitar());
        studentiInrolatiTextView.setText("Studenti inrolati: " + curs.getStudentiInrolati().size());

        List<Student> enrolledStudents = curs.getStudentiInrolati();

        if (position % 2 == 0) {
            convertView.setBackgroundColor(Color.parseColor("#00D2FF"));
        } else {
            convertView.setBackgroundColor(Color.parseColor("#9874a7"));
        }

        eyeIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CursDetailsActivity.class);
                intent.putExtra("curs_data",curs);
                intent.putExtra("studenti", (Serializable) enrolledStudents);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}