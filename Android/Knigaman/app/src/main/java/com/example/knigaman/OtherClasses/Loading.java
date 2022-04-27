package com.example.knigaman.OtherClasses;

import android.os.AsyncTask;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import javax.net.ssl.HttpsURLConnection;

public class Loading  extends AsyncTask<String, Integer, ArrayList<Books>> {
    HttpsURLConnection connection = null;
    final LoadingTaskFinishedListener finishedListener;
    final String textSource = "https://localhost:44773/get";
    BufferedReader reader = null;
    String content, da;
    public Loading(LoadingTaskFinishedListener finishedListener) {
        this.finishedListener = finishedListener;
    }

    @Override
    protected ArrayList<Books> doInBackground(String... strings) {
            downloadResources();

        return null;

    }
    private void downloadResources() {
        try {
            URL url = new URL(textSource);
            connection = (HttpsURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            InputStream inputStream = connection.getInputStream();
            StringBuilder buf = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
                buf.append(line).append("\n");
            content = buf.toString();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    interface LoadingTaskFinishedListener {
        void onTaskFinished(ArrayList<String> valutes);

    }
}
