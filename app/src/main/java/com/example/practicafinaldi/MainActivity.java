package com.example.practicafinaldi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase db;
    //Adaptador adaptador;

    public static ArrayList<String> listaCompra = new ArrayList<>();
    public static ArrayList<String> listaItems = new ArrayList<>();
    public static ArrayList<Integer> cantidad = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("items", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS items(nombre VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS listaCompra(nombre VARCHAR, cantidad int)");


    }
    public static void mostrar() {
        listaCompra.clear();
        listaItems.clear();
        cantidad.clear();

        String result = "";
        Cursor c = db.rawQuery("SELECT * FROM musicos", null);
        if (c.getCount() == 0) {
            result = "No hay datos en la bd";

        } else {
            while (c.moveToNext()) {
                listaCompra.add(c.getString(0));
                listaItems.add(c.getString(1));
                cantidad.add(c.getInt(2));
            }
        }
        c.close();
    }
}
