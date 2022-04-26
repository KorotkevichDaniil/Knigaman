package com.example.cityfast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Spinner spin = findViewById(R.id.loginType);
        EditText name = findViewById(R.id.name);
        EditText lastName = findViewById(R.id.lastName);
        EditText secName = findViewById(R.id.secName);
        EditText phone = findViewById(R.id.phone);
        EditText password = findViewById(R.id.password);
        EditText repeatPassword = findViewById(R.id.repeatPassword);
        Button register = findViewById(R.id.register);
        Button login = findViewById(R.id.login);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(password.getText().equals(repeatPassword.getText()))
                    if(name.getText()!=null && lastName.getText()!=null && secName.getText()!=null & phone.getText()!=null && password.getText()!=null && repeatPassword.getText()!=null){
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
}