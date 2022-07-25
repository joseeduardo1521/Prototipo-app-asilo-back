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


import com.example.scarleth.model.Cass;
import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Recordatorio extends AppCompatActivity implements View.OnClickListener{
    private List<Cass> listPerson = new ArrayList<Cass>();
    ArrayAdapter<Cass> arrayAdapterPersona;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    EditText nomP, fechaP, horarioP;
    ListView listV_personas;
    Button btndelete;
    Cass personaSelected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recordatorio);
        nomP = findViewById(R.id.txt_nombre);
        fechaP = findViewById(R.id.txt_fecha);
        horarioP = findViewById(R.id.txt_horario);
        btndelete = findViewById(R.id.btndelete);
        btndelete.setOnClickListener(this);
        listV_personas = findViewById(R.id.lv_datosPersonas);

        inicializarFirebase();
        listarDatos();
        listV_personas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                personaSelected = (Cass) parent.getItemAtPosition(position);
                nomP.setText(personaSelected.getNombre());
                horarioP.setText(personaSelected.getHorario());
                fechaP.setText(personaSelected.getFecha());
            }
        });

    }

    private void listarDatos() {
        databaseReference.child("Visita").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listPerson.clear();
                for (DataSnapshot objSnaptshot : dataSnapshot.getChildren()){
                    Cass p = objSnaptshot.getValue(Cass.class);
                    listPerson.add(p);
                    arrayAdapterPersona = new ArrayAdapter<Cass>(Recordatorio.this, android.R.layout.simple_list_item_1, listPerson);
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
        databaseReference = firebaseDatabase.getReference();
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btndelete:
                Cass p = new Cass();
                p.setUid(personaSelected.getUid());
                databaseReference.child("Visita").child(p.getUid()).removeValue();
                Toast.makeText(this,"Visita eliminada", Toast.LENGTH_LONG).show();
                limpiarCajas();
                break;
        }
    }

    public void limpiarCajas() {
     nomP.setText("");
     fechaP.setText("");
     horarioP.setText("");
    }
}
