package com.example.widya.nimadepradnya_1202150236_modul5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //mendeklarasikan variabel yang akan digunakan
    dbActivity db;
    RecyclerView rc;
    AdapterActivity adapter;
    ArrayList<TodoActivity> arraylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Menginisialisasi objek yang akan digunakan
        rc = findViewById(R.id.list_todo);
        arraylist = new ArrayList<>();

        //membuat database
        db = new dbActivity(this);
        //mengambil data dari method ambildata
        db.ambildata(arraylist);

        //menginisiasi SharedPreference
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.putih);

        //Menentukan adapter untuk recyclerview
        adapter = new AdapterActivity(this, arraylist, warna);
        //untuk pengaturan ukuran
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        //menginisiasi adapter untuk recyclerview
        rc.setAdapter(adapter);

        //Menjalankan method deleteswipe
        deleteswipe();
    }

    //membuat method deleteswipe untuk menghapus item jika di swipe
    public void deleteswipe(){
        //menambahkan ItemTouchHelper pada RecyclerView
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ketika recyclerview digeser
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                TodoActivity cur = adapter.getItem(pos);

                //jika item di swipe ke kanan atau kekiri
                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if(db.delete(cur.getNama())){
                        //item akan terhapus
                        adapter.removeitem(pos);
                        //menampilkan pemberitahuan bahwa item berhasil dihapus
                        Snackbar.make(findViewById(R.id.roothome), "Item berhasil di hapus", 1500).show();
                    }
                }
            }
        };
        //menginisiasi itemtouchhelper untuk recyclerview
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rc);
    }

    //Method ketika menu dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Method ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //menginisiasi id
        int id = item.getItemId();
        //jika yang dipilih setting
        if(id==R.id.menusetting){
            //berpindah ke settingactivity
            startActivity(new Intent(MainActivity.this, SettingActivity.class));
            finish();
        }
        return true;
    }

    //method jika tombol add ditekan
    public void tambahtodo(View view) {
        //akan berpindah ke addactivity
        startActivity(new Intent(MainActivity.this, AddActivity.class));
        finish();
    }
}

