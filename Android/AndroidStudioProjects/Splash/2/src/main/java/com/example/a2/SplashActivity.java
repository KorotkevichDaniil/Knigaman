package com.example.a2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.Console;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SplashActivity extends AppCompatActivity {

    final String textSource = "http://developer.alexanderklimov.ru/android/apk/realcat.txt";
    String content;
    TextView txtSplash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        getSupportActionBar().hide();
        txtSplash = findViewById(R.id.txt);

        ConnectivityManager myConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = myConnMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            new FileReadTask().execute();
        }else{
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

    private class FileReadTask extends AsyncTask<Void, Void, String>{
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        @Override
        protected void onPreExecute() {
            txtSplash.setText("Идет загрузка...");
        }

        @Override
        protected String doInBackground(Void... params) {
            try{
                URL url = new URL(textSource);
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();
                InputStream inputStream = connection.getInputStream();
                StringBuilder buf = new StringBuilder();
                reader = new BufferedReader(new InputStreamReader(inputStream));
                String line;
                while((line = reader.readLine()) != null){
                    buf.append(line).append("\n");
                    System.out.println(line);
                }
                content = buf.toString();
            }catch(Exception e){
                e.printStackTrace();
            }finally {
                connection.disconnect();
            }
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
            Intent i = new Intent(SplashActivity.this, MainActivity.class);
            i.putExtra("supertext", s);
            startActivity(i);
        }
    }
}