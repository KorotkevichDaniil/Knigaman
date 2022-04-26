package com.example.bank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<Valute> valutes;
    Spinner rezim;
    RecyclerView recy;
    TextView bal, selectedValue, txtValute;
    EditText editSum;
    Button btnBuy;
    double balance = 3000;
    double CURS;
    String r;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bal = findViewById(R.id.balance);
        rezim = findViewById(R.id.spinner);
        txtValute = findViewById(R.id.txtValute);
        selectedValue = findViewById(R.id.selectedValute);
        editSum = findViewById(R.id.editSum);
        btnBuy = findViewById(R.id.btnBuy);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double sum = Double.parseDouble(editSum.getText().toString());
                balance = balance - sum;
                bal.setText("Текущий баланс: " + balance);
                double finalSum = sum / CURS;
                txtValute.setText("Будет куплено: " + finalSum);
            }
        });
        bal.setText("Текущий баланс: " + balance);
        Bundle qs = getIntent().getExtras();
        recy = findViewById(R.id.rec);
        ValutesAdapter.OnValuteClickListener valuteClickListener = new ValutesAdapter.OnValuteClickListener() {
            @Override
            public void onValuteClick(Valute val, int position) {
                selectedValue.setText("Выбранная валюта: " + val.getCharCode());
                CURS = convertToDouble(val.getValue());
            }
        };

        if(qs!= null){
            valutes = (ArrayList<Valute>) qs.getSerializable("valutes");
        }
        selectedValue.setText("Выбранная валюта: " + valutes.get(0).getCharCode());
        CURS = convertToDouble(valutes.get(0).getValue());

        ValutesAdapter adapter = new ValutesAdapter(this, valutes, valuteClickListener);
        recy.setAdapter(adapter);


        r = rezim.getSelectedItem().toString();
        switch(r){
            case "Покупка":

                break;
            case "Продажа":
                
                break;
        }
    }


    public double convertToDouble(String s){
        s = s.replace(',','.');
        return Double.parseDouble(s);
    }

}