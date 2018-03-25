package com.example.widya.nimadepradnya_1202150236_modul5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class TodoActivity {
    //mendeklarasikan variabel
    String nama, deskripsi, prioritas;


    //konstruktor
    public TodoActivity(String namaa, String deskripsii, String prioritass) {
        this.nama = namaa;
        this.deskripsi = deskripsii;
        this.prioritas = prioritass;
    }

    /*Setter and Getter*/

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getPrioritas() {
        return prioritas;
    }

    public void setPrioritas(String prioritas) {
        this.prioritas = prioritas;
    }
}

