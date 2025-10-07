package com.example.exaula3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText txtUser, txtPass;
    private TextView txtSessao;
    private Button btnLogin;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtUser = findViewById(R.id.txtUser);
        txtPass = findViewById(R.id.txtPass);
        txtSessao = findViewById(R.id.txtSessao);
        btnLogin = findViewById(R.id.btnLogin);

        prefs = getSharedPreferences("UserInfo", MODE_PRIVATE);

        // Se já existe login salvo → pular direto
        if (prefs.contains("user") && prefs.contains("pass")) {
            int sessoes = prefs.getInt("sessoes", 0) + 1;
            prefs.edit().putInt("sessoes", sessoes).apply();
            txtSessao.setText("Sessão nº " + sessoes);
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }

        btnLogin.setOnClickListener(v -> fazerLogin());
    }

    private void fazerLogin() {
        String user = txtUser.getText().toString();
        String pass = txtPass.getText().toString();

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("user", user);
        editor.putString("pass", pass);
        editor.putInt("sessoes", 1);
        editor.apply();

        Toast.makeText(this, "Login gravado!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }
}
