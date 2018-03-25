package com.example.widya.nimadepradnya_1202150236_modul5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity  {
    //mendeklarasikan variabel yang digunakan
    EditText name, description, priority;
    dbActivity db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        //Menginisialisasi objek yang digunakan
        name = findViewById(R.id.inputname);
        description = findViewById(R.id.inputdescription);
        priority = findViewById(R.id.inputpriority);

     db = new dbActivity(this);
    }

    //Method jika tombol back ditekan
    @Override
    public void onBackPressed() {
        //berpindah ke mainactivity
        startActivity(new Intent(AddActivity.this, MainActivity.class));
        this.finish();
    }

    //Method jika tombol tambah ditekan
    public void nambahtodo(View view) {
        //jika data telah terisi semua
        if (db.insertdata(new TodoActivity(name.getText().toString(), description.getText().toString(), priority.getText().toString()))) {
            //muncul toast untuk menandakan data berhasil ditambahkan
            Toast.makeText(this, "To Do List berhasil ditambahkan", Toast.LENGTH_SHORT).show();
            //berpindah ke mainactivity
            startActivity(new Intent(AddActivity.this, MainActivity.class));
            this.finish();
        }else{
            //akan menampilkan toast jika data gagal ditambahkan
            Toast.makeText(this, "To Do List gagal ditambahkan", Toast.LENGTH_SHORT).show();
            name.setText(null); description.setText(null); priority.setText(null);
        }
    }
}

