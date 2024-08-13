package com.example.proiectandroid_recuperare;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.example.proiectandroid_recuperare.Adapters.CursAdapter;
import com.example.proiectandroid_recuperare.DataModels.AdaptedCurs;
import com.example.proiectandroid_recuperare.DataModels.Curs;
import com.example.proiectandroid_recuperare.DataModels.Profesor;
import com.example.proiectandroid_recuperare.DataModels.Student;
import com.example.proiectandroid_recuperare.Database.AppDatabase;
import com.example.proiectandroid_recuperare.Util.Util;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView cursListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        List<Student> studenti = Util.readStudentsFromJson(getApplicationContext(),"studenti.json");
        List<Profesor> profesori = Util.readProfesoriFromJson(getApplicationContext(),"profesori.json");
        List<Curs> cursuri = Util.readCursuriFromJson(getApplicationContext(),"cursuri.json",studenti);

        cursListView = findViewById(R.id.mainListView);


        AppDatabase db = AppDatabase.getDatabase(getApplicationContext());


        List<AdaptedCurs> adaptedCursuri = new ArrayList<>();

        boolean loopCompleted = false;

        for (Curs curs : cursuri) {
            List<Integer> studentIds = new ArrayList<>();

            for (Student student : curs.getStudentiInrolati()) {
                studentIds.add(student.getIdStudent());
            }
            AdaptedCurs adaptedCurs = new AdaptedCurs(curs.getDenumire(), curs.getCodCurs(), curs.getProfesorResponsabil().getIdProfesor(), curs.getAnUniversitar(), curs.getNumarCredite(), Util.parseToString(studentIds));
            adaptedCursuri.add(adaptedCurs);

            if (curs == cursuri.get(cursuri.size() - 1)) {
                loopCompleted = true;
            }
        }

        if (loopCompleted) {
                loadData(db, studenti, profesori, adaptedCursuri);
        }


        List<Curs> coursesSetFromDB = new ArrayList<>();

        List<Profesor> profesorsSetFromDB = db.profesorDao().getAllProfesori();
        List<Student> studentsSetFromDB = db.studentDao().getAllStudents();



        List<AdaptedCurs> coursesFromDB = db.cursDao().getAllCursuri();
        for(AdaptedCurs curs : coursesFromDB){
            Profesor profesor = Util.findProfesorById(profesori,curs.getIdProfesorResponsabil());
            Curs curs1 = new Curs(curs.getDenumire(),curs.getCodCurs(),profesor,curs.getAnUniversitar(),curs.getNumarCredite());
            curs1.setIdCurs(curs.getIdCurs());
            List<Integer> studentIds = Util.parseStudentIds(curs.getStudentiInrolati());
            List<Student> students = new ArrayList<>();
            for(int id : studentIds){
                students.add(Util.findStudentById(id,studentsSetFromDB));
            }
            students.size();
            curs1.setStudentiInrolati(students);
            coursesSetFromDB.add(curs1);
        }


        Toast.makeText(getApplicationContext(),"Profesori:"+db.profesorDao().getAllProfesori().size() +"; "+"Studenti:"+db.studentDao().getAllStudents().size() + "; Cursuri:"+ db.cursDao().getAllCursuri().size(),Toast.LENGTH_LONG).show();

        CursAdapter adapter = new CursAdapter(getApplicationContext(),coursesSetFromDB);

        cursListView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.burger_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.writeReviewOption) {
            //Toast.makeText(this, "Write review selected", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(MainActivity.this,WriteReviewActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.viewReviewsOption) {
            Intent intent = new Intent(MainActivity.this,ViewReviewsActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.copyrightInfoOption) {
            showCustomAlertDialog(MainActivity.this);
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void loadData(AppDatabase db, List<Student> studenti, List<Profesor> profesori, List<AdaptedCurs> cursuri) {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        boolean isDatabaseInitialized = sharedPreferences.getBoolean("isDatabaseInitialized", false);

        if (!isDatabaseInitialized) {
            db.studentDao().insertAll(studenti);
            List<Student> studentsDB = db.studentDao().getAllStudents();
            Log.d("Database", "Students inserted, total entries: " + studentsDB.size());

            db.profesorDao().insertAll(profesori);
            List<Profesor> profesoriDB = db.profesorDao().getAllProfesori();
            Log.d("Database", "Profesors inserted, total entries: " + profesoriDB.size());

            db.cursDao().insertAll(cursuri);
            List<AdaptedCurs> cursuriAdapted = db.cursDao().getAllCursuri();
            Log.d("DatabaseSQL", "Cursuri inserted, total entries: " + cursuriAdapted.size());


            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("isDatabaseInitialized", true);
            editor.apply();
        }
    }

    public static void showCustomAlertDialog(Context context) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.copyright_info, null);

        builder.setView(view);
        AlertDialog alertDialog = builder.create();
        view.findViewById(R.id.copyrightInfoOkButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        alertDialog.show();
    }

}