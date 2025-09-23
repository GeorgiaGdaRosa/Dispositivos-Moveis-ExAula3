package com.example.exaula3;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    String[] nomes = {"Alice", "Bruno", "Carlos", "Diana"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        listView = findViewById(R.id.lista_itens);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, nomes);
        listView.setAdapter(adapter);


        listView.setOnItemClickListener((adapterView, view, position, id) -> {
            String nomeSelecionado = nomes[position];
            Intent intent = new Intent(ListActivity.this, DetalhesActivity.class);
            intent.putExtra("nome", nomeSelecionado);
            startActivity(intent);
        });
    }
}