package com.example.exaula3;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.exaula3.models.DatabaseHelper;

import java.util.List;
import java.util.Map;

public class Aula7Activity extends AppCompatActivity {

    private DatabaseHelper helper;
    private EditText txtAno;
    private EditText txtModelo;
    private ListView lista;
    List<Map<String, Object>> carros;
    String[] de = {"id", "modelo", "ano", "valor"};
    int[] para = {R.id.id, R.id.modelo, R.id.ano, R.id.valor};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aula7);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtAno = (EditText) findViewById(R.id.txtAno);
        lista - (ListView) findViewById(R.id.Lista);
        helper = new DatabaseHelper(this);
    }
}