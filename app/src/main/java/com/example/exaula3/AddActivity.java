package com.example.exaula3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.exaula3.models.DatabaseHelper;

public class AddActivity extends AppCompatActivity {

    private EditText txtModelo, txtAno, txtValor;
    private Button btnSalvar;
    private DatabaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        txtModelo = findViewById(R.id.txtModelo);
        txtAno = findViewById(R.id.txtAno);
        txtValor = findViewById(R.id.txtValor);
        btnSalvar = findViewById(R.id.btnSalvar);
        helper = new DatabaseHelper(this);

        btnSalvar.setOnClickListener(v -> salvarCarro());
    }

    private void salvarCarro() {
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("modelo", txtModelo.getText().toString());
        values.put("ano", Integer.parseInt(txtAno.getText().toString()));
        values.put("valor", Double.parseDouble(txtValor.getText().toString()));

        long result = db.insert("carros", null, values);
        if (result != -1) {
            Toast.makeText(this, "Carro salvo!", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Erro ao salvar", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
}