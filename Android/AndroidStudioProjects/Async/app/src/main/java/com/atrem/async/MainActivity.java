package com.atrem.async;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    Button btn, btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text = findViewById(R.id.text);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DownloadTask().execute();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int result = -1;
                try{
                    result = DownloadTask.get();
                    Toast.makeText(getApplicationContext(), "Полученный результат: " + result, Toast.LENGTH_SHORT).show();
                }catch(Exception e){

                }
            }
        });

    }

    public class DownloadTask extends AsyncTask<String, Integer, Integer>  {
        int i;
        int result = -1;
        @Override
        protected Integer doInBackground(String... strings) {
            SystemClock.sleep(1000);
             i = 2012;
            return i;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            text.setText("Идет загрузка");
            btn.setEnabled(false);

        }

        @Override
        protected void onPostExecute(Integer unused) {
            super.onPostExecute(unused);
            text.setText(Integer.toString(i));
            btn.setEnabled(true);
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
           // text.setText("Vremya: " + values[0]/10+" cek");
        }

    }

}