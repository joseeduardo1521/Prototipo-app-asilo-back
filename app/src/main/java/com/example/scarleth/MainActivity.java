package com.example.scarleth;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;
    private EditText TxtUsuario;
    private EditText TxtPass;
    private Button btnnews;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        TxtUsuario = (EditText) findViewById(R.id.TxtUsuario);
        TxtPass = (EditText) findViewById(R.id.TxtPass);
        btnnews = (Button) findViewById(R.id.btnnews);


        progressDialog = new ProgressDialog(this);

        btnnews.setOnClickListener(this);



    }


    private void registrar() {
        String email = TxtUsuario.getText().toString().trim();
        String password = TxtPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Ingrese email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "falta una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        progressDialog.setMessage("REALIZANDO REGISTRO....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(MainActivity.this, "registrado", Toast.LENGTH_SHORT).show();
                    lum();
                } else {
                    Toast.makeText(MainActivity.this, "No se pudo", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void entre() {
        final String email = TxtUsuario.getText().toString().trim();
        String password = TxtPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Ingrese email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "Falta ingresar password", Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Realizando consulta...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    int pos = email.indexOf("@");
                    String user = email.substring(0, pos);
                    Toast.makeText(MainActivity.this, "Bienvenido:" + TxtUsuario.getText(), Toast.LENGTH_SHORT).show();
                    Intent intencion = new Intent(getApplication(), Carga.class);
                    intencion.putExtra(Carga.user, user);
                    startActivity(intencion);
                    lum();
                } else {
                    Toast.makeText(MainActivity.this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }
                progressDialog.dismiss();
            }
        });
    }

    private void lum() {
        TxtUsuario.setText("");
        TxtPass.setText("");
    }

public void sc(){
        Intent ns = new Intent(this,Residentes.class);
        startActivity(ns);
}

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btnnews:
                entre();
                break;



        }

    }
}
