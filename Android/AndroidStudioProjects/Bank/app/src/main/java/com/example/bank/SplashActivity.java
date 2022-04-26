package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity  implements Loading.LoadingTaskFinishedListener{

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        textView = findViewById(R.id.de);
        ConnectivityManager myConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = myConnMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            new Loading(this).execute();
        }else{
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onTaskFinished(ArrayList<Valute> valutes) {
        Intent i = new Intent(this, MainActivity.class);
        i.putExtra("valutes", valutes);
        startActivity(i);
    }
}