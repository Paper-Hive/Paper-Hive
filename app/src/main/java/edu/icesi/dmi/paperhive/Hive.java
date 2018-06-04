package edu.icesi.dmi.paperhive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Clase usada para mostrar el listado de sillas de un piso.
public class Hive extends AppCompatActivity {
    TextView floor_title_tv;

    //Adaptador (BaseAdapter) que controla la lista que contiene cada una de las sillas.
    private SeatAdapter seatAdapter;

    private ListView lv_seats;
    private FirebaseDatabase db;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hive);
        floor_title_tv = findViewById(R.id.hive_floor_title_tv);

        lv_seats = (ListView) findViewById(R.id.lv_seats);

        seatAdapter = new SeatAdapter(this);
        lv_seats.setAdapter(seatAdapter);

        //Estos strings contienen el nombre y el id del piso al que se ingresó.
        String floor_name = getIntent().getExtras().getString("floor_name");
        String floor_id = getIntent().getExtras().getString("floor_id");

        floor_title_tv.setText(floor_name);

        db = FirebaseDatabase.getInstance();

        //Se usan los strings anteriores para inicializar la referencia de la base de datos.
        reference = db.getReference("Pisos").child(floor_id);

        //Se añade un OnItemClickListener a la lista para ingresar a cada silla.
        lv_seats.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                seatAdapter.goToStatusScreen(i);
            }
        });

        /*
        for(int i = 0; i < 6; i++) {
            DatabaseReference seat_reference = reference.child("Seats");
            String seat_id = seat_reference.push().getKey();
            Seat seat = new Seat("Mesa #" + (i+1) + " - " + floor_name);
            seat.setId(seat_id);
            seat.setFloorId(floor_id);
            seat_reference.child(seat_id).setValue(seat);
        }
        */

        //Se crea una nueva referencia apuntando a una rama del piso en donde se encuentran las sillas que
        //hay en este.
        DatabaseReference seat_reference = reference.child("Seats");
        seat_reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //Se añade una silla a la lista por cada Child.
                Seat seat = dataSnapshot.getValue(Seat.class);
                seatAdapter.addSeat(seat);
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