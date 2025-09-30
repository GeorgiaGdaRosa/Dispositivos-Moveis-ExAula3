package com.example.exaula3;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Aula6Activity extends AppCompatActivity {

    private EditText txtCEP;
    private EditText txtLogradouro;
    private EditText  txtComplemento;
    private EditText txtBairro;
    private EditText txtLocalidade;
    private EditText txtUf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aula6);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button button6Ex2 = findViewById(R.id.btnAula6Ex2);
        button6Ex2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Aula6Activity.this, Aula6Ex2Activity.class);
                startActivity(intent);
            }
        });

        txtCEP = (EditText) findViewById(R.id.txtCEP);
        txtLogradouro = (EditText) findViewById(R.id.txtLogradouro);
        txtComplemento = (EditText) findViewById(R.id.txtComplemento);
        txtBairro = (EditText) findViewById(R.id.txtBairro);
        txtLocalidade = (EditText) findViewById(R.id.txtLocalidade);
        txtUf = (EditText) findViewById(R.id.txtUf);
    }

    public void onClickBuscar(View view){
        String cep = txtCEP.getText().toString();
        new HttpAsyncTask().execute(cep);
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params){
            try{
                URL url = new URL("https://viacep.com.br/ws/" + params[0] + "/json/");
                HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                int status = urlConnection.getResponseCode();

                if(status==200){
                    InputStream stream = new BufferedInputStream(urlConnection.getInputStream());
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
                    StringBuilder builder = new StringBuilder();

                    String inputString;
                    while((inputString = bufferedReader.readLine()) != null){
                        builder.append(inputString);
                    }
                    urlConnection.disconnect();
                    return builder.toString();
                }

            } catch (Exception ex) {
                Log.e("URL", ex.toString());
            }
            return null;
        }

        @Override
        public void onPostExecute(String result){
            dialog.dismiss();
            if(result != null){
                try {
                    JSONObject obj = new JSONObject(result);
                    String logradouro = obj.getString("logradouro");
                    String complemento = obj.getString("complemento");
                    String bairro = obj.getString("bairro");
                    String localidade = obj.getString("localidade");
                    String uf = obj.getString("uf");

                    txtLogradouro.setText(logradouro);
                    txtComplemento.setText(complemento);
                    txtBairro.setText(bairro);
                    txtLocalidade.setText(localidade);
                    txtUf.setText(uf);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPreExecute(){
            dialog = new ProgressDialog(Aula6Activity.this);
            dialog.show();
        }

    }

}


