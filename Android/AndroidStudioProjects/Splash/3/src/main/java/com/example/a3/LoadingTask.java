package com.example.a3;

import android.os.AsyncTask;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class LoadingTask extends AsyncTask<String, Integer, String> {

    private final ProgressBar progressBar;
    private final LoadingTaskFinishedListener finishedListener;
    HttpURLConnection connection = null;
    BufferedReader reader = null;
    final String textSource = "http://developer.alexanderklimov.ru/android/apk/realcat.txt";
    String content;
    TextView txtSplash, tv_progress;

    public LoadingTask(ProgressBar progressBar, LoadingTaskFinishedListener finishedListener, TextView text, TextView tv){
        this.progressBar = progressBar;
        this.finishedListener = finishedListener;
        txtSplash = text;
        tv_progress = tv;
    }

    public interface LoadingTaskFinishedListener{
        void onTaskFinished(String s);
    }

    @Override
    protected String doInBackground(String... params) {
        if(resourceDontAlreadyExist()){
            downloadResources();
        }
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
            }
            content = buf.toString();
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            connection.disconnect();
        }
        return content;
    }

    private boolean resourceDontAlreadyExist(){
        return true;
    }

    private void downloadResources(){
        int count = 10;
        for(int i = 0; i < count; i++){
            int progress = (int) ((i/(float) count) * 100);
            publishProgress(progress);
            try{
                Thread.sleep(1000);
            }catch(InterruptedException ignore){
            }
        }
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressBar.setProgress(values[0]);
        tv_progress.setText("Идет загрузка: " + values[0] + "%");
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        finishedListener.onTaskFinished(s);
    }

    @Override
    protected void onPreExecute() {
        txtSplash.setText("Ожидание...");
    }
}
