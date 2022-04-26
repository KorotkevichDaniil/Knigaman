package com.atrem.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    TextView text1, text2, text3, text4, text5, text6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text1 = findViewById(R.id.text1);
        text2 = findViewById(R.id.text2);
        text3 = findViewById(R.id.text3);
        text4 = findViewById(R.id.text4);
        text5 = findViewById(R.id.text5);
        text6 = findViewById(R.id.text6);

        text1.setText(getString(R.string.simple_json2));

        String json_source = getString(R.string.simple_json2);

        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(json_source);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        text2.setText("jsonObject: \n" + jsonObject.toString());

        JSONObject results = null;
        try {
            results = jsonObject.getJSONObject("results");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        text3.setText("results: \n" + results.toString());

        String mySiteName = null;
        try {
            mySiteName = results.getString("sitename");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String myUrl = null;
        try {
            myUrl = results.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        text4.setText("Имя сайта: \n" + mySiteName);
        text5.setText("Адрес сайта: \n" + myUrl);


        JSONArray jsonArray = null;
        try {
            jsonArray = results.getJSONArray("array");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        String stringArrayElement = "\n";
        for(int i = 0; i < jsonArray.length(); i++){
            JSONObject arrayElement = null;
            try {
                arrayElement = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                stringArrayElement += arrayElement.getString("element") + "\n";
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        text6.setText(stringArrayElement);



    }
}