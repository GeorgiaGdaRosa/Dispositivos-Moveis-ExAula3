package com.example.exaula3;

import android.content.ContentValues;
import android.database.Cursor;
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

public class UpdateActivity extends AppCompatActivity {

    String idDados;
    DatabaseHelper helper;
    EditText modelo, valor, ano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update);

        idDados = getIntent().getStringExtra("idDados");
        modelo = (EditText) findViewById(R.id.modelo);
        valor = (EditText) findViewById(R.id.valor);
        ano = (EditText) findViewById(R.id.ano);

        helper = new DatabaseHelper(this);
        preencheCampos();

    }

    private void preencheCampos() {
        SQLiteDatabase db = helper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM CARRO WHERE id = ?", new String[]{idDados});
        cursor.moveToFirst();

        modelo.setText(cursor.getString(1));
        valor.setText(cursor.getString(3));
        ano.setText(cursor.getString(2));

        cursor.close();
        db.close();
    }

    public void atualizarCarro(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("modelo", modelo.getText().toString());
        values.put("ano", Integer.parseInt(ano.getText().toString()));
        values.put("valor", Double.parseDouble(valor.getText().toString()));

        String where[] = new String[] { idDados };
        long resultado = db.update("carro", values, "id = ?", where);

        if (resultado != -1) {
            Toast.makeText(this, "Registro atualizados com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show();
        }
    }

    public void excluirCarro(View view) {
        SQLiteDatabase db = helper.getWritableDatabase();
        String where[] = new String[] { idDados };

        long resultado = db.delete("carro", "id = ?", where);
        if (resultado != -1) {
            Toast.makeText(this, "Registro excluído com sucesso!", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
        } else {
            Toast.makeText(this, "Erro ao excluir!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy(){
        helper.close();
        super.onDestroy();
    }

}