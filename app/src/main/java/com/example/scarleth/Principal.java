package com.example.scarleth;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.scarleth.model.Residente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.UUID;

public class Principal extends AppCompatActivity implements View.OnClickListener {
    EditText nomP, edadP, sexoP, sangreP, teleP, tratamientoP, horarioP, estatusP, domicilioP, fechap, alergiasp, ingresop, egresop, habi;
    Button btnres;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        nomP = findViewById(R.id.txt_nombre);
        edadP = findViewById(R.id.txt_edad);
        sexoP = findViewById(R.id.sexoss);
        sangreP = findViewById(R.id.sangre);
        teleP = findViewById(R.id.txt_telefono);
        tratamientoP = findViewById(R.id.txt_tratamiento);
        horarioP = findViewById(R.id.txt_horario);
        estatusP = findViewById(R.id.txt_estatus);
        domicilioP = findViewById(R.id.txt_domicilio);
        fechap = findViewById(R.id.txt_fecha);
        alergiasp = findViewById(R.id.txt_alergias);
        ingresop = findViewById(R.id.notauno);
        egresop = findViewById(R.id.nota);
        habi = findViewById(R.id.roms);
        btnres = findViewById(R.id.btnres);

        btnres.setOnClickListener(this);

        inicializarFirebase();
    }

    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
    }

    private void limpiarCajas() {
        nomP.setText("");
        edadP.setText("");
        sexoP.setText("");
        sangreP.setText("");
        teleP.setText("");
        tratamientoP.setText("");
        horarioP.setText("");
        estatusP.setText("");
        domicilioP.setText("");
        fechap.setText("");
        alergiasp.setText("");
        ingresop.setText("");
        egresop.setText("");
        habi.setText("");

    }

    private void validacion() {
        String nombre = nomP.getText().toString();
        String edad = edadP.getText().toString();
        String sexo = sexoP.getText().toString();
        String sangre = sangreP.getText().toString();
        String telefono = teleP.getText().toString();
        String tratamiento = tratamientoP.getText().toString();
        String horario = horarioP.getText().toString();
        String estatus = estatusP.getText().toString();
        String domicilio = domicilioP.getText().toString();
        String Fecha = fechap.getText().toString();
        String Alergias = alergiasp.getText().toString();
        String Ingreso = ingresop.getText().toString();
        String Egreso = egresop.getText().toString();
        String Habitacion = habi.getText().toString();

        if (nombre.equals("")){
            nomP.setError("Required");
        }
        else if (edad.equals("")){
            edadP.setError("Required");
        }
        else if (sexo.equals("")){
            sexoP.setError("Required");
        }
        else if (sangre.equals("")){
            sangreP.setError("Required");
        }
        else if(telefono.equals("")){
            teleP.setError("Required");
        }
        else if(tratamiento.equals("")){
            tratamientoP.setError("Required");
        }

        else if(horario.equals("")){
            horarioP.setError("Required");
        }
        else if(estatus.equals("")){
            estatusP.setError("Required");
        }
        else if(domicilio.equals("")){
            domicilioP.setError("Required");
        }
        else if(Fecha.equals("")){
            fechap.setError("Required");
        }
        else if(Alergias.equals("")){
            alergiasp.setError("Required");
        }
        else if(Ingreso.equals("")){
            ingresop.setError("Required");
        }
        else if(Egreso.equals("")){
            egresop.setError("Required");
        }
        else if(Habitacion.equals("")){
            habi.setError("Required");
        }
    }


    @Override
    public void onClick(View v) {
        String nombre = nomP.getText().toString();
        String edad = edadP.getText().toString();
        String sexo = sexoP.getText().toString();
        String sangre = sangreP.getText().toString();
        String telefono = teleP.getText().toString();
        String tratamiento = tratamientoP.getText().toString();
        String horario = horarioP.getText().toString();
        String estatus = estatusP.getText().toString();
        String domicilio = domicilioP.getText().toString();
        String fecha = fechap.getText().toString();
        String alergia = alergiasp.getText().toString();
        String ingreso = ingresop.getText().toString();
        String egreso = egresop.getText().toString();
        String habitacion = habi.getText().toString();

        switch (v.getId()){
            case R.id.btnres:
                if(nombre.equals("")||edad.equals("")||sexo.equals("")||sangre.equals("")||telefono.equals("")||tratamiento.equals("")||horario.equals("")||estatus.equals("")
                ||domicilio.equals("")||fecha.equals("")||alergia.equals("")||ingreso.equals("")||egreso.equals("")||habitacion.equals("")){
                    validacion();
                }
                else{
                    Residente p = new Residente();
                    p.setUid(UUID.randomUUID().toString());
                    p.setNombre(nombre);
                    p.setEdad(edad);
                    p.setSexo(sexo);
                    p.setSangre(sangre);
                    p.setTelefono(telefono);
                    p.setTratamiento(tratamiento);
                    p.setHorario(horario);
                    p.setEstatus(estatus);
                    p.setDomicilio(domicilio);
                    p.setFecha(fecha);
                    p.setAlergias(alergia);
                    p.setIngreso(alergia);
                    p.setEgreso(egreso);
                    p.setHabitacion(habitacion);
                    databaseReference.child("Residente").child(p.getUid()).setValue(p);
                    databaseReference.child("Tratamientos").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Residente Agregado", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
            break;



    }
}
    }
