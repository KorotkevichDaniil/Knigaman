package com.atrem.morze;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText inputText, txtOutput;
    Button dash, point, space, translate;
    RadioButton rus, eng, txt, morz;
    RadioGroup gLanguage, gMode;
    String language, file;
    Boolean fMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputText = findViewById(R.id.editText);
        txtOutput = findViewById(R.id.txtOutput);
        dash = findViewById(R.id.dash);
        point = findViewById(R.id.point);
        space = findViewById(R.id.space);
        translate = findViewById(R.id.translate);
        rus = findViewById(R.id.rus);
        eng = findViewById(R.id.eng);
        txt = findViewById(R.id.txt);
        morz = findViewById(R.id.morz);
        gLanguage = findViewById(R.id.gLanguage);
        gMode = findViewById(R.id.gMode);

        dash.setOnClickListener(this);
        point.setOnClickListener(this);
        space.setOnClickListener(this);
        translate.setOnClickListener(this);

        gLanguage.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch(i){
                    case R.id.rus:
                        language = "rus";
                        inputText.setText("");
                        break;
                    case R.id.eng:
                        language = "eng";
                        inputText.setText("");
                        break;
                    default:
                        break;
                }
            }
        });

        gMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.txt:
                        fMode = true;
                        dash.setVisibility(View.INVISIBLE);
                        point.setVisibility(View.INVISIBLE);
                        space.setVisibility(View.INVISIBLE);
                        inputText.setText("");
                        break;
                    case R.id.morz:
                        fMode = false;
                        dash.setVisibility(View.VISIBLE);
                        point.setVisibility(View.VISIBLE);
                        space.setVisibility(View.VISIBLE);
                        inputText.setText("");
                        break;
                    default:
                        break;
                }
            }
        });

        rus.setChecked(true);
        txt.setChecked(true);

        readFile();
    }

    public void readFile(){
        InputStream resourceReader = getResources().openRawResource(R.raw.morze);
        Writer writer = new StringWriter();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(resourceReader, "UTF-8"));
            String line = reader.readLine();
            while (line != null) {
                writer.write(line);
                line = reader.readLine();
            }
        } catch (Exception e) {
        } finally {
            try {
                resourceReader.close();
            } catch (Exception e) {

            }
        }
        file = writer.toString();
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()){
            case R.id.dash:
                inputText.append("-");
                break;
            case R.id.point:
                inputText.append("•");
                break;
            case R.id.space:
                inputText.append(" ");
                break;
            case R.id.translate:
                try {
                    JSONObject json = new JSONObject(file);
                    JSONObject lang = json.getJSONObject(language);
                    String text = "";

                    if(fMode){
                        char chars[] = inputText.getText().toString().toCharArray();
                        for(int i = 0; i < chars.length; i++){
                            String bukva = String.valueOf(chars[i]);
                            String sym = lang.getString(bukva.toUpperCase());
                            text += sym + " ";
                        }
                        txtOutput.setText(text);

                    }else{
                        String sym = "";
                        char chars[] = inputText.getText().toString().toCharArray();
                        for(int i = 0; i < chars.length; i++){
                            if(chars[i] == ' '){
                                text += lang.getString(sym);
                                sym = "";
                            }else if(i == chars.length-1){
                                sym += chars[i];
                                text += lang.getString(sym);
                                sym = "";
                            }else{
                                sym += chars[i];
                            }
                        }
                        txtOutput.setText(text);
                    }

                } catch (JSONException e) {

                }
                break;
            default:
                break;
        }
    }
}