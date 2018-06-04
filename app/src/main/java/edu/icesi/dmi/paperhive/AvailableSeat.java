package edu.icesi.dmi.paperhive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

//Esta clase es usada cuando el usuario hace tab en una silla disponible.
public class AvailableSeat extends AppCompatActivity {
    TextView seat_name_tv, seat_state_tv, start_time_tv, finish_time_tv;
    Button reserve_btn;

    String seat_name, seat_id, seat_floor_id;

    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.available_seat);

        seat_name_tv = findViewById(R.id.available_seat_name_tv);
        seat_state_tv = findViewById(R.id.available_seat_state_tv);
        start_time_tv = findViewById(R.id.available_seat_start_time_tv);
        finish_time_tv = findViewById(R.id.available_seat_finish_time_tv);
        reserve_btn = findViewById(R.id.available_seat_reserve_btn);

        //Estos strings contienen la informaciòn de la silla.
        seat_name = getIntent().getExtras().getString("seat_name");
        seat_id = getIntent().getExtras().getString("seat_id");
        seat_floor_id = getIntent().getExtras().getString("seat_floor_id");

        database = FirebaseDatabase.getInstance();

        //Se usan los strings para inicializar la referencia de la base de datos.
        reference = database.getReference("Pisos").child(seat_floor_id).child("Seats").child(seat_id);

        reserve_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToReserveSeat();
            }
        });

        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Seat seat = dataSnapshot.getValue(Seat.class);
                seat_name_tv.setText(seat.getName() );
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.e("DATA", "Error normalito");
            }
        });
    }

    public void goToReserveSeat() {
        Intent intent = new Intent(this, ReserveSeat.class);
        intent.putExtra("seat_id", seat_id);
        intent.putExtra("seat_floor_id", seat_floor_id);
        startActivity(intent);
    }
}
