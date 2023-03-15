package com.example.practicafinaldi;

import static com.example.practicafinaldi.MainActivity.mostrar;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class AddItems extends AppCompatActivity {

    SQLiteDatabase db;
    EditText etItem;


    protected void onCreate(Bundle SavedInstaceState) {
        super.onCreate(SavedInstaceState);
        setContentView(R.layout.add_items);
        db = openOrCreateDatabase("items", Context.MODE_PRIVATE, null);
        etItem = findViewById(R.id.etNombreItem);
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

    public void anyadir(View view) {
        try {
            db.execSQL("INSERT INTO items values ('"+ etItem.getText().toString() + "');");
            Toast.makeText(getApplicationContext(), etItem.getText().toString() + " añadido con éxito", Toast.LENGTH_SHORT).show();
            etItem.setText("");
            mostrar();
        } catch (Exception e){
            Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_SHORT).show();
        }

    }
}
