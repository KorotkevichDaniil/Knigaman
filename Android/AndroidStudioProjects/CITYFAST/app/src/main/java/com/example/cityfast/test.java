package com.example.cityfast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class test extends AppCompatActivity {


    HttpsURLConnection connection = null;
    BufferedReader reader = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

    }

    private void downloadResources() {
        try {
            URL url = new URL("https://localhost:44375/api/%D0%9A%D0%BB%D0%B8%D0%B5%D0%BD%D1%82%D1%8B");
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            StringBuilder buf = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            //while ((line = reader.readLine()) != null)
            //    buf.append(line).append("\n");

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}