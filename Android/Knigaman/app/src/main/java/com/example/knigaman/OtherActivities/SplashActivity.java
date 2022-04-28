package com.example.knigaman.OtherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Toast;

import com.example.knigaman.LoginActivities.AuthorizationActivity;
import com.example.knigaman.MainActivities.MainActivity;
import com.example.knigaman.OtherClasses.Loading;
import com.example.knigaman.R;

import java.util.ArrayList;

public class SplashActivity extends AppCompatActivity implements Loading.LoadingTaskFinishedListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(getApplicationContext(), AuthorizationActivity.class);
                startActivity(i);
            }
        }, 5000);


        ConnectivityManager myConnMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = myConnMgr.getActiveNetworkInfo();
        if(networkInfo != null && networkInfo.isConnected()){
            new Loading(this).execute();
        }else{
            Toast.makeText(this, "Нет интернета", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onTaskFinished(ArrayList<String> valutes) {

    }
}