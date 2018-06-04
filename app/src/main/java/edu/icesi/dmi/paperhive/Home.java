package edu.icesi.dmi.paperhive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Home de la aplicación. Aqui se muestran el listado de pisos.
public class Home extends AppCompatActivity {
    ListView floors_list;

    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    //Adaptador (BaseAdapter) que controla la lista que contiene cada uno de los pisos.
    FloorAdapter floorAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        floors_list = findViewById(R.id.home_floors_list);

        firebaseDatabase = firebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference().child("Pisos");

        floorAdapter = new FloorAdapter(this);
        floors_list.setAdapter(floorAdapter);

        //Se añade un OnItemClickListener a la lista para ingresar a cada piso.
        floors_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                floorAdapter.goToHive(position);
            }
        });

        /*
        String id1 = reference.push().getKey();
        Floor floor2 = new Floor("Piso 2", "Espacio de trabajo individual", id1);
        reference.child(id1).setValue(floor2);

        String id2 = reference.push().getKey();
        Floor floor3 = new Floor("Piso 3", "Espacio de trabajo grupal", id2);
        reference.child(id2).setValue(floor3);

        String id3 = reference.push().getKey();
        Floor floor4 = new Floor("Piso 4", "Espacio de trabajo mixto", id3);
        reference.child(id3).setValue(floor4);
        */

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Añade un nuevo piso por cada Child en la base de datos de Firebase.
                Floor floor = dataSnapshot.getValue(Floor.class);
                floorAdapter.addFloor(floor);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
