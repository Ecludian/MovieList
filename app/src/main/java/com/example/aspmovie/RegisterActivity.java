package com.example.aspmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText username, password, passwordConf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        username = (EditText)findViewById(R.id.edtTextUsernameRegister);
        password = (EditText)findViewById(R.id.edtTextPasswordRegister);
        passwordConf = (EditText)findViewById(R.id.edtTextPasswordConfirmRegister);
        login = (Button)findViewById(R.id.btnLoginRegist);
        register = (Button)findViewById(R.id.btnRegisterRegist);


        //pindah ke halam login
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

        //register
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                String strPasswordConf = passwordConf.getText().toString();

                if (strPassword.equals(strPasswordConf)) {
                    Boolean daftar = db.insertUser(strUsername, strPassword);
                    if(daftar == true){
                        Toast.makeText(getApplicationContext(), "Register Successful", Toast.LENGTH_SHORT).show();
                        Intent loginIntent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(loginIntent);
                        finish();
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Register Failed", Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Password doesn't match", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}