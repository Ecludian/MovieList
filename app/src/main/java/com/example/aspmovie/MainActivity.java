package com.example.aspmovie;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper db;
    Button logout;

    private Button alertButton;
    private TextView alertTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        db = new DatabaseHelper(this);

        logout = (Button)findViewById(R.id.btnLogout);
        Boolean checkSession = db.checkSession("Exist");
        if(checkSession =false){
            Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }

        logout.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Boolean updtSession = db.updgradeSession("Empty", 1);
                if(updtSession == true){
                    Toast.makeText(getApplicationContext(), "Logout Successful", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MainActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        alertButton = (Button) findViewById(R.id.AllertButton);
        alertTextView = (TextView) findViewById(R.id.AlertTextView);

        alertButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);

                builder.setCancelable(true);
                builder.setTitle("About");
                builder.setMessage("Charel Adhi Nugroho\n" +
                        "19.61.0155\n" +
                        "19 BCI 01\n" +
                        "Universitas Amikom Yogyakarta");

                builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        alertTextView.setVisibility(View.VISIBLE);
                    }
                });
                     builder.show();
            }
        });
    }


    public void showMovies(View view){
        Intent intent = new Intent(this, ShowMovies.class);
        startActivity(intent);
    }


}