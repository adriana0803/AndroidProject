package com.example.proiectandroid_recuperare.Util;

import android.content.Context;

import com.example.proiectandroid_recuperare.DataModels.Curs;
import com.example.proiectandroid_recuperare.DataModels.Profesor;
import com.example.proiectandroid_recuperare.DataModels.Student;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class Util {
    public static List<Student> readStudentsFromJson(Context context, String filename) {
        List<Student> students = new ArrayList<>();

        try {
            // Read JSON file from assets folder
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            // Parse JSON array
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int idStudent = jsonObject.getInt("idStudent");
                String nume = jsonObject.getString("nume");
                String prenume = jsonObject.getString("prenume");
                int grupa = jsonObject.getInt("grupa");
                String facultate = jsonObject.getString("facultate");
                String specializare = jsonObject.getString("specializare");
                int anStudiu = jsonObject.getInt("anStudiu");

                // Create student object and add to list
                Student student = new Student(idStudent, nume, prenume, grupa, facultate, specializare, anStudiu);
                students.add(student);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return students;
    }

    public static List<Profesor> readProfesoriFromJson(Context context, String filename) {
        List<Profesor> profesori = new ArrayList<>();

        try {
            // Read JSON file from assets folder
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            // Parse JSON array
            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String nume = jsonObject.getString("nume");
                String prenume = jsonObject.getString("prenume");
                String titluAcademic = jsonObject.getString("titluAcademic");
                String facultate = jsonObject.getString("facultate");
                String disciplinaPredata = jsonObject.getString("disciplinaPredata");

                // Create profesor object and add to list
                Profesor profesor = new Profesor(nume, prenume, titluAcademic, facultate, disciplinaPredata);
                profesori.add(profesor);
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return profesori;
    }
    public static List<Curs> readCursuriFromJson(Context context, String filename, List<Student> studenti) {
        List<Curs> cursuri = new ArrayList<>();

        try {
            InputStream is = context.getAssets().open(filename);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            String json = new String(buffer, "UTF-8");

            JSONArray jsonArray = new JSONArray(json);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                JSONObject profesorObject = jsonObject.getJSONObject("profesorResponsabil");
                String denumire = jsonObject.getString("denumire");
                String codCurs = jsonObject.getString("codCurs");
                String profesorNume = profesorObject.getString("nume");
                String profesorPrenume = profesorObject.getString("prenume");
                String profesorTitluAcademic = profesorObject.getString("titluAcademic");
                String profesorFacultate = profesorObject.getString("facultate");
                String profesorDiscplinaPredata = profesorObject.getString("disciplinaPredata");

                int idCurs = jsonObject.getInt("idCurs");

                String anUniversitar = jsonObject.getString("anUniversitar");
                int numarCredite = jsonObject.getInt("numarCredite");
                JSONArray studentiInrolatiArray = jsonObject.getJSONArray("studentiInrolati");

                // Convert JSONArray of student IDs to a list of integers
                List<Integer> studentiInrolatiIds = new ArrayList<>();
                for (int j = 0; j < studentiInrolatiArray.length(); j++) {
                    studentiInrolatiIds.add(studentiInrolatiArray.getInt(j));
                }

                // Create a new Curs object with the extracted data
                Curs curs = new Curs(denumire, codCurs, new Profesor(profesorNume, profesorPrenume, profesorTitluAcademic, profesorFacultate, profesorDiscplinaPredata), anUniversitar, numarCredite);

                curs.setIdCurs(idCurs);

                for (Student student : studenti) {
                    student.getIdStudent();
                }


                for(int studentID : studentiInrolatiIds){
                    Student student = findStudentById(studentID,studenti);
                    curs.addStudent(student);
                }

                cursuri.add(curs);
            }

        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }

        return cursuri;
    }

    public static Student findStudentById(int studentId, List<Student> studenti) {
        for (Student student : studenti) {
            if (student.getIdStudent() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static String parseToString(List<Integer> integerList) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < integerList.size(); i++) {
            stringBuilder.append(integerList.get(i));
            if (i < integerList.size() - 1) {
                stringBuilder.append(", ");
            }
        }
        return stringBuilder.toString();
    }

    public static List<Integer> parseStudentIds(String studentIdsString) {
        List<Integer> studentIds = new ArrayList<>();

        String[] studentIdsArray = studentIdsString.split(", ");

        for (String idString : studentIdsArray) {
            try {
                int id = Integer.parseInt(idString.trim());
                studentIds.add(id);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        return studentIds;
    }

    public static Profesor findProfesorById(List<Profesor> profesori, int id) {
        for (Profesor profesor : profesori) {
            if (profesor.getIdProfesor() == id) {
                return profesor;
            }
        }
        return null;
    }
}
