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

import com.airbnb.lottie.LottieAnimationView;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Regis extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth firebaseAuth;
    private EditText TxtUsuario;
    private EditText TxtPass;
    private Button btncrespo;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regis);

        firebaseAuth = FirebaseAuth.getInstance();

        TxtUsuario = (EditText) findViewById(R.id.TxtUsuario);
        TxtPass = (EditText) findViewById(R.id.TxtPass);

        btncrespo = (Button) findViewById(R.id.btncrespo);


        progressDialog = new ProgressDialog(this);

        btncrespo.setOnClickListener(this);

    }

    private void registrar(){
        String email= TxtUsuario.getText().toString().trim();
        String password = TxtPass.getText().toString().trim();
        final LottieAnimationView animationView = findViewById(R.id.animation_view2);
        animationView.setAnimation("login.json");
        animationView.setVisibility(View.GONE);

        if(TextUtils.isEmpty(email)){
            Toast.makeText(this, "Ingrese email", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)){
            Toast.makeText(this, "falta una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
       // progressDialog.setMessage("REALIZANDO REGISTRO....");
        //progressDialog.show();
        animationView.setVisibility(View.VISIBLE);
        animationView.playAnimation();
        animationView.loop(true);
        firebaseAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()){
                    lum();
                    Toast.makeText(Regis.this, "registrado", Toast.LENGTH_SHORT).show();
                    animationView.setVisibility(View.GONE);
                } else {
                    animationView.setVisibility(View.GONE);
                    Toast.makeText(Regis.this, "No se ha podido registrar", Toast.LENGTH_SHORT).show();
                }
               // progressDialog.dismiss();
            }
        });
    }


    private void lum(){
        TxtUsuario.setText("");
        TxtPass.setText("");
    }
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.btncrespo:
                registrar();
                break;

        }
    }
}
