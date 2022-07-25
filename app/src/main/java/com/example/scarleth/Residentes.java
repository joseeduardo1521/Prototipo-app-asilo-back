package com.example.scarleth;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class Residentes extends AppCompatActivity {
    private EditText namesp;
    private EditText emailp;
    private EditText passwordp;
    private EditText edadp;
    private EditText sexop;
    private EditText sangrep;
    private EditText familiap;
    private EditText tratamientop;
    private EditText horariop;
    private EditText estatusp;
    private EditText casap;
    private EditText fechp;
    private EditText alergiap;
    private EditText ingresop;
    private EditText egresop;
    private EditText romsp;


    private Button btnp;
    //Variables a registrar
    private String name ="";
    private String email ="";
    private String password ="";
    private String edad="";
    private String sexo="";
    private String Sangre="";
    private String Telefono="";
    private String Tratamiento="";
    private String Horario="";
    private String Estatus="";
    private String Domicilio="";
    private String Fecha="";
    private String Alergias="";
    private String Ingreso="";
    private String Egreso="";
    private String Habitacion="";
    FirebaseAuth mAuth;
    DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_residentes);
        mAuth = FirebaseAuth.getInstance();
        mDatabase = FirebaseDatabase.getInstance().getReference();
        namesp = (EditText) findViewById(R.id.names);
        emailp = (EditText) findViewById(R.id.email);
        passwordp = (EditText) findViewById(R.id.password);
        edadp =(EditText) findViewById(R.id.edad);
        sexop = (EditText) findViewById(R.id.sex);
        sangrep = (EditText) findViewById(R.id.sangre);
        familiap = (EditText) findViewById(R.id.txt_telefono);
        tratamientop = (EditText) findViewById(R.id.txt_tratamiento);
        horariop = (EditText) findViewById(R.id.txt_horario);
        estatusp = (EditText) findViewById(R.id.txt_estatus);
        casap = (EditText) findViewById(R.id.txt_domicilio);
        fechp = (EditText) findViewById(R.id.txt_fecha);
        alergiap = (EditText) findViewById(R.id.txt_alergias);
        ingresop = (EditText) findViewById(R.id.notauno);
        egresop = (EditText) findViewById(R.id.nota);
        romsp =(EditText) findViewById(R.id.roms);



                btnp = (Button) findViewById(R.id.btnRegistrar);
        btnp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = namesp.getText().toString();
                email = emailp.getText().toString();
                password = passwordp.getText().toString();
                edad = edadp.getText().toString();
                sexo = sexop.getText().toString();
                Sangre = sangrep.getText().toString();
                Telefono = familiap.getText().toString();
                Tratamiento = tratamientop.getText().toString();
                Horario = horariop.getText().toString();
                Estatus = estatusp.getText().toString();
                Domicilio = casap.getText().toString();
                Fecha = fechp.getText().toString();
                Alergias = alergiap.getText().toString();
                Ingreso = ingresop.getText().toString();
                Egreso = egresop.getText().toString();
                Habitacion = romsp.getText().toString();


                if (!name.isEmpty()&&!email.isEmpty()&&!password.isEmpty()&&!edad.isEmpty()&&!sexo.isEmpty()&&!Sangre.isEmpty()&&!Telefono.isEmpty()
                        &&!Tratamiento.isEmpty()&&!Horario.isEmpty()&&!Estatus.isEmpty()&&!Domicilio.isEmpty()&&!Fecha.isEmpty()&&!Alergias.isEmpty()&&!Ingreso.isEmpty()
                        &&!Egreso.isEmpty()&&!Habitacion.isEmpty()){

                    if (password.length()>=6){
                        registerUser();
                    }
                    else{
                        Toast.makeText(Residentes.this, "La contraseña debe ser mayor a 6 caracteres", Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(Residentes.this, "Complete todos los datos", Toast.LENGTH_SHORT).show();
                }
            }
        });


            }

    private void registerUser() {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){

                    final Map<String, Object> map = new HashMap<>();
                    map.put("name", name);
                    map.put("email",email);
                    map.put("password", password);
                    map.put("edad",edad);
                    map.put("sexo",sexo);
                    map.put("sangre",Sangre);
                    map.put("telefono",Telefono);
                    map.put("tratamiento",Tratamiento);
                    map.put("horario",Horario);
                    map.put("estatus",Estatus);
                    map.put("domicilio",Domicilio);
                    map.put("alergia",Alergias);
                    map.put("ingreso", Ingreso);
                    map.put("egreso",Egreso);
                    map.put("habitacion",Habitacion);


                    final String id = mAuth.getCurrentUser().getUid();

                    mDatabase.child("Usuario").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task2) {
                            if(task2.isSuccessful()){
                                mDatabase.child("nuevo").child(id).setValue(map).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task3) {
                                        if (task3.isSuccessful()){
                                            Toast.makeText(Residentes.this, "Exito", Toast.LENGTH_SHORT).show();
                                        }
                                        else{
                                            Toast.makeText(Residentes.this, "No", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                                Toast.makeText(Residentes.this, "Registrado", Toast.LENGTH_SHORT).show();
                            }
                            else{
                                Toast.makeText(Residentes.this, "Error al registrar datos", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });

                }
                else{
                    Toast.makeText(Residentes.this, "Error al registrar", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }


}

