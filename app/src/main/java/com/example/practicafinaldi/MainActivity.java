package com.example.practicafinaldi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static SQLiteDatabase db;
    Adaptador adaptador;

    public static ArrayList<String> listaCompra = new ArrayList<>();
    public static ArrayList<String> listaItems = new ArrayList<>();
    public static ArrayList<Integer> cantidad = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = openOrCreateDatabase("items", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS listaitems(nombre VARCHAR)");

        db.execSQL("CREATE TABLE IF NOT EXISTS listacompra(nombre VARCHAR, cantidad int)");
        if (listaItems.isEmpty()) {
            mostrar();
        }
        RecyclerView recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adaptador = new Adaptador(this, listaItems);

        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(), 1);
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.setAdapter(adaptador);

    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {

            case R.id.Productos:
                Intent i1 = new Intent(this, MainActivity.class);
                startActivity(i1);
                break;
            case R.id.Anyadir:
                Intent i2 = new Intent(this, AddItems.class);
                startActivity(i2);
                break;

            default:
                break;
        }
        return true;
    }

    public static void mostrar() {
        listaCompra.clear();
        listaItems.clear();
        cantidad.clear();

        String result = "";
        Cursor c = db.rawQuery("SELECT * FROM listaitems", null);
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
