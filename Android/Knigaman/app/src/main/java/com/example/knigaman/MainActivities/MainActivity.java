package com.example.knigaman.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.knigaman.LoginActivities.RegistrationActivity;
import com.example.knigaman.R;

public class MainActivity extends AppCompatActivity {

    Button basket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        basket = findViewById(R.id.btnBasket);

        basket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), BasketActivity.class);
                startActivity(i);
            }
        });
    }
}