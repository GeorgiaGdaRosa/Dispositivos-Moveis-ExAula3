package com.example.exaula3;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exaula3.models.DatabaseHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Aula7Activity extends AppCompatActivity {
    private EditText txtBusca;
    private Button btnBuscar, btnAdicionar;
    private ListView listView;
    private DatabaseHelper helper;
    private ArrayAdapter<String> adapter;
    private ArrayList<String> listaCarros;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aula7);

        txtBusca = findViewById(R.id.txtBusca);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnAdicionar = findViewById(R.id.btnAdicionar);
        listView = findViewById(R.id.listView);
        helper = new DatabaseHelper(this);

        btnBuscar.setOnClickListener(v -> listarCarros(txtBusca.getText().toString()));
        btnAdicionar.setOnClickListener(v -> startActivity(new Intent(this, AddActivity.class)));
    }

    private void listarCarros(String modeloBusca) {
        listaCarros = new ArrayList<>();
        SQLiteDatabase db = helper.getReadableDatabase();

        SQLiteQueryBuilder qb = new SQLiteQueryBuilder();
        qb.setTables("carros");

        Cursor cursor = qb.query(db, null,
                "modelo LIKE ?", new String[]{"%" + modeloBusca + "%"},
                null, null, "ano DESC");

        if (cursor.moveToFirst()) {
            do {
                String modelo = cursor.getString(cursor.getColumnIndexOrThrow("modelo"));
                int ano = cursor.getInt(cursor.getColumnIndexOrThrow("ano"));
                double valor = cursor.getDouble(cursor.getColumnIndexOrThrow("valor"));
                listaCarros.add(modelo + " - " + ano + " - R$ " + valor);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listaCarros);
        listView.setAdapter(adapter);
    }
}