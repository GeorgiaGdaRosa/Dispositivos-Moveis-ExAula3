package com.example.exaula3;

import android.os.Bundle;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Aula5Ex2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aula5_ex2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner1);
        String[] nomesCores = {"Vermelho", "Verde", "Azul", "Amarelo", "Roxo", "Ciano"};
        String[] valoresHex = {"#FF0000", "#00FF00", "#0000FF", "#FFFF00", "#800080", "#00FFFF"};
        Spinner spinner = findViewById(R.id.spinner1);
        MyAdapter adapter = new MyAdapter(this, nomesCores, valoresHex);
        spinner.setAdapter(adapter);

    }
}