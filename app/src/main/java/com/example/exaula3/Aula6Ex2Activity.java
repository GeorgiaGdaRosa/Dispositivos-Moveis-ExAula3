package com.example.exaula3;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

public class Aula6Ex2Activity extends AppCompatActivity {

    private EditText temperatura;
    private EditText umidade;
    private EditText pontoOrvalho;
    private EditText pressao;

    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_aula6_ex2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        temperatura = (EditText) findViewById(R.id.mediaTemp);
        umidade = (EditText) findViewById(R.id.mediaUmid);
        pontoOrvalho = (EditText) findViewById(R.id.mediaOrvalho);
        pressao = (EditText) findViewById(R.id.mediaPressao);

        new HttpAsyncTask().execute();

        listview = findViewById(R.id.weatherInfo);
    }

    class HttpAsyncTask extends AsyncTask<String, Void, String> {
        ProgressDialog dialog;

        @Override
        protected String doInBackground(String... params){
            try{
                URL url = new URL("https://ghelfer.net/la/weather.json");
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
            if(dialog != null && dialog.isShowing()){
                dialog.dismiss();
            }
            if(result != null){
                try {
                    JSONObject obj = new JSONObject(result);
                    JSONArray jsonArray = obj.getJSONArray("weather");
                    double somaTemp = 0, somaUmid = 0, somaOrvalho = 0, somaPressao = 0;
                    int count = jsonArray.length();

                    for(int i = 0; i < count; i++){
                        JSONObject item = jsonArray.getJSONObject(i);
                        somaTemp +=Double.parseDouble(item.optString("temperature", "0"));
                        somaUmid +=Double.parseDouble(item.optString("humidity", "0"));
                        somaOrvalho +=Double.parseDouble(item.optString("dewPoint", "0"));
                        somaPressao +=Double.parseDouble(item.optString("pressure", "0"));

                    }

                    double mediaTemp = somaTemp/count;
                    double mediaUmid = somaUmid/count;
                    double mediaOrvalho = somaOrvalho/count;
                    double mediaPressao = somaPressao/count;

                    temperatura.setText(String.format(Locale.getDefault(), "%.2f C", mediaTemp));
                    umidade.setText(String.format(Locale.getDefault(), "%.1f %%", mediaUmid));
                    pontoOrvalho.setText(String.format(Locale.getDefault(), "%.2f C", mediaOrvalho));
                    pressao.setText(String.format(Locale.getDefault(), "%.2f hPa", mediaPressao));

                    List<Map<String, String>> data = new ArrayList<>();

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject item = jsonArray.getJSONObject(i);
                        Map<String, String> map = new HashMap<>();
                        map.put("temperature", "Temperatura: " + item.optString("temperature", "N/A") + " °C");
                        map.put("humidity", "Umidade: " + item.optString("humidity", "N/A") + " %");
                        map.put("dewPoint", "Ponto de Orvalho: " + item.optString("dewPoint", "N/A") + " °C");
                        map.put("pressure", "Pressão: " + item.optString("pressure", "N/A") + " hPa");
                        data.add(map);
                    }

                    String[] from = {"temperature", "humidity", "dewPoint", "pressure"};
                    int[] to = {R.id.temperatura, R.id.umidade, R.id.orvalho, R.id.pressao};

                    SimpleAdapter adapter = new SimpleAdapter(Aula6Ex2Activity.this, data, R.layout.line_item, from, to);
                    listview.setAdapter(adapter);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

        protected void onPreExecute() {
            dialog = new ProgressDialog(Aula6Ex2Activity.this);
            dialog.show();
        }
    }
}

