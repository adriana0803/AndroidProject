package com.example.proiectandroid_recuperare.DataModels;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "cursuri")
public class AdaptedCurs {

    @PrimaryKey(autoGenerate = true)
    private int idCurs;
    private String denumire;
    private String codCurs;
    private int idProfesorResponsabil;
    private String anUniversitar;
    private int numarCredite;
    private String studentiInrolati;

    public AdaptedCurs(String denumire, String codCurs, int idProfesorResponsabil, String anUniversitar, int numarCredite, String studentiInrolati) {
        this.denumire = denumire;
        this.codCurs = codCurs;
        this.idProfesorResponsabil = idProfesorResponsabil;
        this.anUniversitar = anUniversitar;
        this.numarCredite = numarCredite;
        this.studentiInrolati = studentiInrolati;
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

    public int getIdProfesorResponsabil() {
        return idProfesorResponsabil;
    }

    public void setIdProfesorResponsabil(int idProfesorResponsabil) {
        this.idProfesorResponsabil = idProfesorResponsabil;
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

    public String getStudentiInrolati() {
        return studentiInrolati;
    }

    public void setStudentiInrolati(String studentiInrolati) {
        this.studentiInrolati = studentiInrolati;
    }
}
