package com.example.proiectandroid_recuperare.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.proiectandroid_recuperare.DataModels.Student;
import com.example.proiectandroid_recuperare.R;

import java.util.List;

public class StudentAdapter extends ArrayAdapter<Student> {

    public StudentAdapter(Context context, List<Student> students) {
        super(context, 0, students);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        Student student = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_student, parent, false);
        }

        TextView idTextView = convertView.findViewById(R.id.text_idStudent);
        TextView numeTextView = convertView.findViewById(R.id.text_nume);
        TextView prenumeTextView = convertView.findViewById(R.id.text_prenume);
        TextView grupaTextView = convertView.findViewById(R.id.text_grupa);
        TextView facultateTextView = convertView.findViewById(R.id.text_facultate);
        TextView specializareTextView = convertView.findViewById(R.id.text_specializare);
        TextView anStudiuTextView = convertView.findViewById(R.id.text_anStudiu);

        if (student != null) {
            idTextView.setText("IdStudent: " + student.getIdStudent());
            numeTextView.setText("Nume: " + student.getNume());
            prenumeTextView.setText("Prenume: " + student.getPrenume());
            grupaTextView.setText("Grupa: " + student.getGrupa());
            facultateTextView.setText("Facultate: " + student.getFacultate());
            specializareTextView.setText("Specializare: " + student.getSpecializare());
            anStudiuTextView.setText("An Studiu: " + student.getAnStudiu());
        }

        return convertView;
    }
}