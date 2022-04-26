package com.example.galary;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import java.util.ArrayList;

public class FullPicture extends AppCompatActivity {

    Button back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_picture);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        Uri item = Uri.parse(getIntent().getStringExtra("item"));
        ArrayList<Uri> list = (ArrayList<Uri>) getIntent().getSerializableExtra("list");

        Pager adapter = new Pager(this, list);
        ViewPager2 pager = findViewById(R.id.viewPager);
        pager.setAdapter(adapter);
        for(int i = 0; i < list.size(); i++){
            if(list.get(i).equals(item)){
                pager.setCurrentItem(i, false);
            }
        }
    }
}