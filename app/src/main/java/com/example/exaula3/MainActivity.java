package com.example.exaula3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button buttonEx1 = findViewById(R.id.buttonEx1);
        buttonEx1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Ex1Activity.class);
                startActivity(intent);
            }
        });

        Button buttonEx2 = findViewById(R.id.buttonEx2);
        buttonEx2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Ex2Activity.class);
                startActivity(intent);
            }
        });

        Button buttonEx3 = findViewById(R.id.buttonEx3);
        buttonEx3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Ex3Activity.class);
                startActivity(intent);
            }
        });

        Button buttonEx4 = findViewById(R.id.buttonEx4);
        buttonEx4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Ex4Activity.class);
                startActivity(intent);
            }
        });

        Button buttonAula4 = findViewById(R.id.buttonAula4);
        buttonAula4.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Aula4Activity.class);
                startActivity(intent);
            }
        });

        Button buttonAula5 = findViewById(R.id.buttonAula5);
        buttonAula5.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Aula5Activity.class);
                startActivity(intent);
            }
        });

        Button buttonAula5Ex2 = findViewById(R.id.buttonAula5Ex2);
        buttonAula5Ex2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Aula5Ex2Activity.class);
                startActivity(intent);
            }
        });

        Button buttonAula6 = findViewById(R.id.buttonAula6);
        buttonAula6.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(MainActivity.this, Aula6Activity.class);
                startActivity(intent);
            }
        });

    }
}