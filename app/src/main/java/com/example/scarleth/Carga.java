package com.example.scarleth;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Carga extends AppCompatActivity {
public final static String user="names";
TextView txtUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga);

        txtUser =(TextView) findViewById(R.id.textUser);
        String user = getIntent().getStringExtra("names");
        txtUser.setText("Bienvenido: "+user+"");

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Carga.this,Navigation.class);
                startActivity(intent);
                finish();
            }
        },2000);

    }
}
