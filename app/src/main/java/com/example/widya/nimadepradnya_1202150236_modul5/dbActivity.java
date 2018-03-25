package com.example.widya.nimadepradnya_1202150236_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.provider.BaseColumns;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class dbActivity extends SQLiteOpenHelper {
    //deklarasi variabel yang akan digunakan
    Context mContext;
    SQLiteDatabase db;

    public static final String DB_NAME = "listtodo.db";
    public static final String TABLE_NAME = "daftartodo";
    public static final String col_1 = "todo";
    public static final String col_2 = "description";
    public static final String col_3 = "priority";

    //Concstructore
    public dbActivity(Context context) {
        super(context, DB_NAME, null, 1);
        this.mContext = context;
        db = this.getWritableDatabase();
        db.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    //ketika database dibuat
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " (todo varchar(35) primary key, description varchar(50), priority varchar(3))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean insertdata (TodoActivity list) {
        //mencocokkan kolom beserta nilainya
        ContentValues val = new ContentValues();
        val.put(col_1, list.getNama());
        val.put(col_2, list.getDeskripsi());
        val.put(col_3, list.getPrioritas());
        long hasil = db.insert(TABLE_NAME, null, val);
        if (hasil == -1) {
            return false;
        } else {
            return true;
        }
    }

    //method untuk menghapus data pada database
    public boolean delete(String ToDo) {
        return db.delete(TABLE_NAME, col_1 + "=\"" + ToDo + "\"", null) > 0;
    }

    //method untuk mengakses dan membaca data dari database
    public void ambildata (ArrayList<TodoActivity> daftar) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT todo, description, priority FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            daftar.add(new TodoActivity(cursor.getString(0), cursor.getString(1), cursor.getString(2)));
        }
    }
}
