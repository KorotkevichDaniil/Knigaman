package com.example.a3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class SplashSActivity extends AppCompatActivity implements LoadingTask.LoadingTaskFinishedListener {

    TextView txtSpalsh, tv_progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_splash);

        getSupportActionBar().hide();
        txtSpalsh = findViewById(R.id.txt);
        tv_progress = findViewById(R.id.tv_progress_horizontal);

        ProgressBar progressBar = findViewById(R.id.progress);

        ConnectivityManager myConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = myConnMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            new LoadingTask(progressBar,this,txtSpalsh, tv_progress).execute();
        }else{
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onTaskFinished(String s) {
        Intent i = new Intent(SplashSActivity.this, MainActivity.class);
        i.putExtra("supertext", s);
        startActivity(i);
    }



}