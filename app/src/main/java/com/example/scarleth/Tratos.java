package com.example.scarleth;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.scarleth.model.Residente;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Tratos extends AppCompatActivity implements View.OnClickListener{
    private List<Residente> listPerson = new ArrayList<Residente>();
    ArrayAdapter<Residente> arrayAdapterPersona;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText nomP, tratamientoP, horarioP, estatusP;
   Button btnActualizar;
    ListView listV_personas;

    Residente personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratos);

        nomP = findViewById(R.id.txt_nombre);
        tratamientoP = findViewById(R.id.txt_tratamiento);
        horarioP = findViewById(R.id.txt_horario);
        estatusP = findViewById(R.id.txt_estatus);
       btnActualizar = findViewById(R.id.btnActualizar);
        listV_personas = findViewById(R.id.lv_datosPersonas);
        btnActualizar.setOnClickListener(this);
        inicializarFirebase();
        listarDatos();
        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Residente) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                tratamientoP.setText(personaSelected.getTratamiento());
                horarioP.setText(personaSelected.getHorario());
                estatusP.setText(personaSelected.getEstatus());
            }
        });

    }


    private void listarDatos() {
        databaseReference.child("Tratamientos").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Residente p = objSnaptshot.getValue(Residente.class);
                    listPerson.add(p);
                    arrayAdapterPersona = new ArrayAdapter<Residente>(Tratos.this, android.R.layout.simple_list_item_1, listPerson);
                    listV_personas.setAdapter(arrayAdapterPersona);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        //firebaseDatabase.setPersistenceEnabled(true);
        databaseReference = firebaseDatabase.getReference();
    }

    @Override
    public void onClick(View view) {
        String nombre = nomP.getText().toString();
        String tratamiento = tratamientoP.getText().toString();
        String horario = horarioP.getText().toString();
        String estatus = estatusP.getText().toString();

        switch (view.getId()){
            case R.id.btnActualizar:
                if (nombre.equals("")||tratamiento.equals("")||horario.equals("")||estatus.equals("")){
                    validacion();
                }
                else {
                    Residente p = new Residente();
                    p.setUid(personaSelected.getUid());
                    p.setNombre(nomP.getText().toString().trim());
                    p.setTratamiento(tratamientoP.getText().toString().trim());
                    p.setHorario(horarioP.getText().toString().trim());
                    p.setEstatus(estatusP.getText().toString().trim());
                    databaseReference.child("Tratamientos").child(p.getUid()).setValue(p);
                    Toast.makeText(this, "Información actualizada...", Toast.LENGTH_LONG).show();
                    limpiarCajas();
                }
                break;

        }
    }

    private void limpiarCajas() {
        nomP.setText("");
        tratamientoP.setText("");
        horarioP.setText("");
        estatusP.setText("");
    }

    private void validacion(){
        String nombre = nomP.getText().toString();
        String tratamiento = tratamientoP.getText().toString();
        String horario = horarioP.getText().toString();
        String estatus = estatusP.getText().toString();
        if (nombre.equals("")){
            nomP.setError("Required");
        }
        else if (tratamiento.equals("")){
            tratamientoP.setError("Required");
        }
        else if (horario.equals("")){
            horarioP.setError("Required");
        }
        else if (estatus.equals("")){
            estatusP.setError("Required");
        }
    }
}
