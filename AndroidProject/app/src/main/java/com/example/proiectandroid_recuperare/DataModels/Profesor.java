package com.example.proiectandroid_recuperare.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "profesori")
public class Profesor implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int idProfesor;
    private String nume;
    private String prenume;
    private String titluAcademic;
    private String facultate;
    private String disciplinaPredata;

    public Profesor(String nume, String prenume, String titluAcademic, String facultate, String disciplinaPredata) {
        this.nume = nume;
        this.prenume = prenume;
        this.titluAcademic = titluAcademic;
        this.facultate = facultate;
        this.disciplinaPredata = disciplinaPredata;
    }

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getTitluAcademic() {
        return titluAcademic;
    }

    public void setTitluAcademic(String titluAcademic) {
        this.titluAcademic = titluAcademic;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getDisciplinaPredata() {
        return disciplinaPredata;
    }

    public void setDisciplinaPredata(String disciplinaPredata) {
        this.disciplinaPredata = disciplinaPredata;
    }

    @Override
    public String toString() {
        return "Profesor{" +
                "idProfesor=" + idProfesor +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", titluAcademic='" + titluAcademic + '\'' +
                ", facultate='" + facultate + '\'' +
                ", disciplinaPredata='" + disciplinaPredata + '\'' +
                '}';
    }
}
