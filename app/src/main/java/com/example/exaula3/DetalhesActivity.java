package com.example.exaula3;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DetalhesActivity extends AppCompatActivity {
    TextView nomeTextView;
    Spinner estadoSpinner;
    ListView cidadeListView;

    String[] estados = {"SP", "RJ", "MG", "RS"};
    String[] cidades = {"São Paulo", "Rio de Janeiro", "Belo Horizonte", "Porto Alegre"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalhes);

        nomeTextView = findViewById(R.id.textViewNome);
        estadoSpinner = findViewById(R.id.spinnerEstados);
        cidadeListView = findViewById(R.id.listViewCidades);


        String nome = getIntent().getStringExtra("nome");
        nomeTextView.setText("Nome: " + nome);


        ArrayAdapter<String> estadoAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, estados);
        estadoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        estadoSpinner.setAdapter(estadoAdapter);


        ArrayAdapter<String> cidadeAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, cidades);
        cidadeListView.setAdapter(cidadeAdapter);
    }
}