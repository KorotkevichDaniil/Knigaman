package com.atrem.arrayjson;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.list);

        //String rawJSON = "[{\"MemberID\":\"1\",\"Name\":\"Владимир\",\"Tel\":\"8-495-4876-107\"}" + ",{\"MemberID\":\"2\",\"Name\":\"Игорь\",\"Tel\":\"8-495-4876-107\"}" + ", {\"MemberID\":\"3\",\"Name\":\"Никита\",\"Tel\":\"8-495-4876-107\"}]";

        ArrayList<HashMap<String, String>>arrayList = new ArrayList<>();

        JSONArray data = null;
        try {
            data = createJSON();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HashMap<String, String> map;

        for(int i = 0; i < data.length(); i++){
            JSONObject jsonObject = null;
            try {
                jsonObject = data.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
            }
            map = new HashMap<>();
            try {
                map.put("MemberID", jsonObject.getString("MemberID"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                map.put("Name", jsonObject.getString("Name"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            try {
                map.put("Tel", jsonObject.getString("Tel"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            arrayList.add(map);

        }
        SimpleAdapter simpleAdapter;
        simpleAdapter = new SimpleAdapter(this, arrayList, R.layout.list_item, new String[]{"MemberID", "Name", "Tel"}, new int[]{R.id.text1, R.id.text2, R.id.text3});
        listView.setAdapter(simpleAdapter);
    }

    private JSONArray createJSON() throws JSONException{
       ArrayList<JSONObject> jsonArrayList = new ArrayList<>();
       JSONObject object;

       object = new JSONObject();
       object.put("MemberID", 1);
       object.put("Name", "Константин");
       object.put("Tel", "8-495-4876-107");
       jsonArrayList.add(object);

        object = new JSONObject();
        object.put("MemberID", 2);
        object.put("Name", "Владимир");
        object.put("Tel", "8-764-4876-107");
        jsonArrayList.add(object);

        object = new JSONObject();
        object.put("MemberID", 3);
        object.put("Name", "Никита");
        object.put("Tel", "8-495-5776-107");
        jsonArrayList.add(object);


       JSONArray jsonArray = new JSONArray(jsonArrayList);
       return jsonArray;
    }
}