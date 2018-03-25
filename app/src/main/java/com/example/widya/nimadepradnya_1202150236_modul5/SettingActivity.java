package com.example.widya.nimadepradnya_1202150236_modul5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingActivity extends AppCompatActivity {
    //mendeklarasikan variabel
    int idwarna;
    TextView warna;
    AlertDialog.Builder bld;
    SharedPreferences.Editor edit;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        //membuat alertdialogbaru
        bld = new AlertDialog.Builder(this);

        //Mendapatkan SharedPreference dan menentukan editor untuk SharedPreference
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
        edit = pref.edit();
        idwarna = pref.getInt("background", R.color.putih);
        //mendapatkan id pada layut
        warna = findViewById(R.id.warnanya);
        //mengeset warna sesuai id warna yang dipilih
        warna.setText(getwarna(idwarna));
    }

    //method ketika item dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            //menjalankan method onbackpressed
            this.onBackPressed();
        }
        return true;
    }

    //method ketika tombol back ditekan
    @Override
    public void onBackPressed() {
        //berpindah ke mainactivity
        startActivity(new Intent(SettingActivity.this, MainActivity.class));
        finish();
    }

    //Method untuk menampilkan dialog memilih warna
    public void pilihwarna(View view) {
        //menentukan title dari alert dialog
        bld.setTitle("Choose Color");
        //membuat view dari layout activity_color
        View v = getLayoutInflater().inflate(R.layout.activity_color, null);
        //menampilkan view yang telah dibuat
        bld.setView(v);

        //Menentukan radiobutton yang dipilih
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntCOlor(idwarna));

        //Method ketika menekan OK
        bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //mendapatkan id dari radiobutton yang dipilih
                int check = rg.getCheckedRadioButtonId();
                switch (check){
                    case R.id.warnabiru:
                        idwarna = R.color.biru;
                        break;
                    case R.id.warnaungu:
                        idwarna = R.color.ungu;
                        break;
                    case R.id.warnapink:
                        idwarna = R.color.pink;
                        break;
                    case R.id.warnaputih:
                        idwarna = R.color.putih;
                        break;
                }

                //Mengatur SharedPreference dan mengubah text
                warna.setText(getwarna(idwarna));
                edit.putInt("background", idwarna);
                edit.commit();
            }
        });

        //Method ketika menekan Cancel
        bld.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //Menampilkan dialog
        bld.create().show();
    }

    //Method untuk mendapatkan String warna
    public String getwarna(int i){
        if(i==R.color.ungu){
            return "Ungu";
        }else if(i==R.color.biru){
            return "Blue";
        }else if(i==R.color.pink){
            return "Merah Muda";
        }else{
            return "White";
        }
    }
    //Method untuk mendapatkan id warna
    public int getIntCOlor(int i){
        if(i==R.color.ungu){
            return R.id.warnaungu;
        }else if(i==R.color.biru){
            return R.id.warnabiru;
        }else if(i==R.color.pink){
            return R.id.warnapink;
        }else{
            return R.id.warnaputih;
        }
    }
}

