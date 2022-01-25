package com.example.aspmovie;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button login, register;
    EditText  username, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);

        username = (EditText)findViewById(R.id.edtTextUsername);
        password = (EditText)findViewById(R.id.edtTextPassword);
        login = (Button)findViewById(R.id.btnLogin);
        register = (Button)findViewById(R.id.btnRegister);

        //Register
        register.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent registerIntent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(registerIntent);
                finish();
            }
        });

        //Login
        login.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                String strUsername = username.getText().toString();
                String strPassword = password.getText().toString();
                boolean masuk = db.checkLogin(strUsername, strPassword);
                if (masuk == true){
                    Boolean updateSession = db.updgradeSession("Exist", 1);
                    if (updateSession == true) {
                        Toast.makeText(getApplicationContext(), "Login Success", Toast.LENGTH_SHORT).show();
                        Intent mainIntent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(mainIntent);
                        finish();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }

        });
    }
}