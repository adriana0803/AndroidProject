package com.example.proiectandroid_recuperare;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.proiectandroid_recuperare.Adapters.StudentAdapter;
import com.example.proiectandroid_recuperare.DataModels.Curs;
import com.example.proiectandroid_recuperare.DataModels.Student;

import java.util.List;

public class CursDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_curs_details);

        TextView textIdCurs = findViewById(R.id.text_idCurs);
        TextView textDenumire = findViewById(R.id.text_denumire);
        TextView textCodCurs = findViewById(R.id.text_codCurs);
        TextView textProfesorResponsabil = findViewById(R.id.text_profesorResponsabil);
        TextView textAnUniversitar = findViewById(R.id.text_anUniversitar);
        TextView textNumarCredite = findViewById(R.id.text_numarCredite);

        ListView listStudents = findViewById(R.id.list_students);
        Button buttonClose = findViewById(R.id.button_close);


        Intent intent = getIntent();
        Curs curs = (Curs) intent.getSerializableExtra("curs_data");
        textIdCurs.setText(textIdCurs.getText().toString()+curs.getIdCurs());
        textDenumire.setText(textDenumire.getText().toString()+curs.getDenumire());
        textCodCurs.setText(textCodCurs.getText().toString()+curs.getCodCurs());
        textProfesorResponsabil.setText(textProfesorResponsabil.getText().toString()
                +curs.getProfesorResponsabil().getTitluAcademic()+" "
                +curs.getProfesorResponsabil().getNume() + " " +curs.getProfesorResponsabil().getPrenume());
        textAnUniversitar.setText(textAnUniversitar.getText().toString()+curs.getAnUniversitar());
        textNumarCredite.setText(textNumarCredite.getText().toString()+curs.getNumarCredite());


        List<Student> studenti = (List<Student>) intent.getSerializableExtra("studenti");
        listStudents.setAdapter(new StudentAdapter(CursDetailsActivity.this,studenti));

        buttonClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }
}