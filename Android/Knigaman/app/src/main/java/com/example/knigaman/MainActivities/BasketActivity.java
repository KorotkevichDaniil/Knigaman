package com.example.knigaman.MainActivities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.knigaman.OtherActivities.SuccessActivity;
import com.example.knigaman.R;

public class BasketActivity extends AppCompatActivity {

    Button buy, clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket);
        buy = findViewById(R.id.btnBuy);
        clear = findViewById(R.id.btnClear);
        buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), SuccessActivity.class);
                startActivity(i);
            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}