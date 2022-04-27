package com.example.knigaman.OtherActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.knigaman.LoginActivities.AuthorizationActivity;
import com.example.knigaman.R;

import javax.security.auth.login.LoginException;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent i = new Intent(getApplicationContext(), AuthorizationActivity.class);
        startActivity(i);
    }
}