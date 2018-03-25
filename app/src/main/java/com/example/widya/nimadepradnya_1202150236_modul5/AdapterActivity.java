package com.example.widya.nimadepradnya_1202150236_modul5;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class AdapterActivity extends RecyclerView.Adapter<AdapterActivity.holder> {
    //deklarasikan variabel yang akan digunakan
    private Context con;
    private List<TodoActivity> item_list;
    int idwarna;


    //membuat konstruktor
    public AdapterActivity(Context con, List<TodoActivity> item, int id) {
        this.con = con;
        this.item_list = item;
        this.idwarna = id;
    }

    //Method untuk menentukan viewholder untuk recyclerview
    @Override
    public holder onCreateViewHolder(ViewGroup parent, int viewType) {
        //membuat view baru
        View v = LayoutInflater.from(con).inflate(R.layout.activity_adapter, parent, false);
        holder hold = new holder(v);
        return hold;
    }

    //untuk mengambil nilai dengan objek pada viewholder
    @Override
    public void onBindViewHolder(holder holder, int position) {
        TodoActivity td = item_list.get(position);
        holder.name.setText(td.getNama());
        holder.description.setText(td.getDeskripsi());
        holder.priority.setText(td.getPrioritas());
        holder.cd.setCardBackgroundColor(con.getResources().getColor(this.idwarna));
    }

    //Method untuk mendapatkan item dari adapter
    public TodoActivity getItem(int position){
        return item_list.get(position);
    }

    //Method untuk mendapatkan jumlah item
    @Override
    public int getItemCount() {
        return item_list.size();
    }


    //Method untuk menghapus item
    public void removeitem(int i){
        item_list.remove(i);
        //memberikan pemberitahuan item telah dihapus
        notifyItemRemoved(i);
        notifyItemRangeChanged(i, item_list.size());
    }

    //Subclass sebagai viewholder
    class holder extends RecyclerView.ViewHolder{
        //mendeklarasikan variabel yang digunakan
        public TextView name, description, priority;
        public CardView cd;
        public holder(View itemView) {
            super(itemView);

            //Menginisialisasikan objek
            name = itemView.findViewById(R.id.namatodo);
            description = itemView.findViewById(R.id.deskripsitodo);
            priority = itemView.findViewById(R.id.prioritastodo);
            cd = itemView.findViewById(R.id.cardview);
        }
    }
}
