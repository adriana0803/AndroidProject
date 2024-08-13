package com.example.proiectandroid_recuperare.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "studenti")
public class Student implements Serializable {
    @PrimaryKey(autoGenerate = false)
    private  int idStudent;
    private String nume;
    private String prenume;
    private int grupa;
    private String facultate;
    private String specializare;
    private int anStudiu;

    public Student(int idStudent, String nume, String prenume, int grupa, String facultate, String specializare, int anStudiu) {
        this.idStudent = idStudent;
        this.nume = nume;
        this.prenume = prenume;
        this.grupa = grupa;
        this.facultate = facultate;
        this.specializare = specializare;
        this.anStudiu = anStudiu;
    }

    public int getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(int idStudent) {
        this.idStudent = idStudent;
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

    public int getGrupa() {
        return grupa;
    }

    public void setGrupa(int grupa) {
        this.grupa = grupa;
    }

    public String getFacultate() {
        return facultate;
    }

    public void setFacultate(String facultate) {
        this.facultate = facultate;
    }

    public String getSpecializare() {
        return specializare;
    }

    public void setSpecializare(String specializare) {
        this.specializare = specializare;
    }

    public int getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(int anStudiu) {
        this.anStudiu = anStudiu;
    }

    @Override
    public String toString() {
        return "Student{" +
                "idStudent=" + idStudent +
                ", nume='" + nume + '\'' +
                ", prenume='" + prenume + '\'' +
                ", grupa=" + grupa +
                ", facultate='" + facultate + '\'' +
                ", specializare='" + specializare + '\'' +
                ", anStudiu=" + anStudiu +
                '}';
    }
}
