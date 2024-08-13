package com.example.proiectandroid_recuperare.DataModels;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

//@Entity(tableName = "cursuri")
public class Curs implements Serializable {
    //@PrimaryKey(autoGenerate = true)
    private int idCurs;
    private String denumire;
    private String codCurs;
    private Profesor profesorResponsabil;
    private String anUniversitar;
    private int numarCredite;
    private List<Student> studentiInrolati;

    public Curs(String denumire, String codCurs, Profesor profesorResponsabil, String anUniversitar, int numarCredite) {
        this.denumire = denumire;
        this.codCurs = codCurs;
        this.profesorResponsabil = profesorResponsabil;
        this.anUniversitar = anUniversitar;
        this.numarCredite = numarCredite;
        this.studentiInrolati = new ArrayList<>();
    }

    public int getIdCurs() {
        return idCurs;
    }

    public void setIdCurs(int idCurs) {
        this.idCurs = idCurs;
    }

    public String getDenumire() {
        return denumire;
    }

    public void setDenumire(String denumire) {
        this.denumire = denumire;
    }

    public String getCodCurs() {
        return codCurs;
    }

    public void setCodCurs(String codCurs) {
        this.codCurs = codCurs;
    }

    public Profesor getProfesorResponsabil() {
        return profesorResponsabil;
    }

    public void setProfesorResponsabil(Profesor profesorResponsabil) {
        this.profesorResponsabil = profesorResponsabil;
    }

    public String getAnUniversitar() {
        return anUniversitar;
    }

    public void setAnUniversitar(String anUniversitar) {
        this.anUniversitar = anUniversitar;
    }

    public int getNumarCredite() {
        return numarCredite;
    }

    public void setNumarCredite(int numarCredite) {
        this.numarCredite = numarCredite;
    }

    public List<Student> getStudentiInrolati() {
        return studentiInrolati;
    }

    public void setStudentiInrolati(List<Student> studentiInrolati) {
        this.studentiInrolati = studentiInrolati;
    }

    public void addStudent(Student student) {
        this.studentiInrolati.add(student);
    }

    @Override
    public String toString() {
        return "Curs{" +
                "idCurs=" + idCurs +
                ", denumire='" + denumire + '\'' +
                ", codCurs='" + codCurs + '\'' +
                ", profesorResponsabil=" + profesorResponsabil +
                ", anUniversitar='" + anUniversitar + '\'' +
                ", numarCredite=" + numarCredite +
                ", studentiInrolati=" + studentiInrolati +
                '}';
    }
}
