package com.example.exaula3;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exaula3.models.DatabaseHelper;

public class AddActivity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText modelo, valor, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        modelo = (EditText) findViewById(R.id.editTextModelo);
        valor = (EditText) findViewById(R.id.editTextValor);
        ano = (EditText) findViewById(R.id.editTextAno);

        helper = new DatabaseHelper(this);
    }

    public void salvarCarro(View view){
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        long resultado = db.insert("carro", null, values);
        if(resultado != -1){
            Toast.makeText(this, "Registro salvo com sucesso!", Toast.LENGTH_SHORT).show();
            limpar();
        } else {
            Toast.makeText(this, "Erro ao salvar!", Toast.LENGTH_SHORT).show();

        }

    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }
}