package edu.icesi.dmi.paperhive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

//Esta clase es usada cuando el usuario hace tab en una silla que él mismo reservò con anterioridad.
public class ReservedSeat extends AppCompatActivity {
    TextView seat_name_tv, seat_start_time_tv, seat_finish_time_tv;
    Button report_btn;

    String seat_name, seat_id, seat_floor_id;
    int seat_initial_time, seat_finish_time;

    FirebaseDatabase database;
    DatabaseReference reference;

    ListView report_list;
    ArrayList<String> reports;

    //Adaptador (ArrayAdapter) que controla la lista que contiene los reportes de daño de la silla.
    ArrayAdapter<String> arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reserved_seat);

        seat_name_tv = findViewById(R.id.reserved_seat_name_tv);
        seat_start_time_tv = findViewById(R.id.reserved_seat_start_time_tv);
        seat_finish_time_tv = findViewById(R.id.reserved_seat_finish_time_tv);
        report_btn = findViewById(R.id.reserved_seat_report_btn);

        seat_name = getIntent().getExtras().getString("seat_name");
        seat_name_tv.setText(seat_name);

        seat_id = getIntent().getExtras().getString("seat_id");
        seat_floor_id = getIntent().getExtras().getString("seat_floor_id");

        seat_initial_time = getIntent().getExtras().getInt("seat_initial_time");
        seat_start_time_tv.setText("" + seat_initial_time);

        seat_finish_time = getIntent().getExtras().getInt("seat_finish_time");
        seat_finish_time_tv.setText("" + seat_finish_time);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Pisos").child(seat_floor_id).child("Seats").child(seat_id);

        report_list = findViewById(R.id.reserved_seat_report_list);
        reports = new ArrayList<>();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, reports);
        report_list.setAdapter(arrayAdapter);

        //Esta referencia apuntará a un Child que contiene todos los reportes de daño hechos a la silla.
        DatabaseReference report_reference = reference.child("Reports");
        report_reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                String report = dataSnapshot.getValue(String.class);

                //Se añaden los reportes al ArrayAdapter para que se muestren en pantalla.
                reports.add(report);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                String report = dataSnapshot.getValue(String.class);

                //Se retiran los reportes de la lista cuando el moderador elimina uno de estos desde la
                //base de datos.
                reports.remove(report);
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToReportReservedSeat();
            }
        });
    }

    public void goToReportReservedSeat() {
        Intent intent = new Intent(this, ReportReservedSeat.class);
        intent.putExtra("seat_name", seat_name);
        startActivityForResult(intent, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1) {
            if(resultCode == RESULT_OK) {
                //Se reciben los Extras y se les asigna un valor nulo a aquellos que tengan como valor
                //un "-" a fin de que al subirlos a la base de datos estos sean borrados automaticamente.
                String scratched = data.getExtras().getString("scratched");
                if(scratched.equals("-") ) {
                    scratched = null;
                }

                String connection_failure = data.getExtras().getString("connection_failure");
                if(connection_failure.equals("-") ) {
                    connection_failure = null;
                }

                String time_indicator_failure = data.getExtras().getString("time_indicator_failure");
                if(time_indicator_failure.equals("-") ) {
                    time_indicator_failure = null;
                }

                String other = data.getExtras().getString("other");
                if(other.equals("-") ) {
                    other = null;
                }

                //Se suben los reportes por daños a la base de datos en un Child dentro de la silla que tiene
                //reservada el usuario.

                //Se crea una referencia para cada uno de los reportes realizados a fin de que no se
                //sobreescriban entre ellos.
                DatabaseReference report_reference1 = reference.child("Reports").push();
                report_reference1.setValue(scratched);

                DatabaseReference report_reference2 = reference.child("Reports").push();
                report_reference2.setValue(connection_failure);

                DatabaseReference report_reference3 = reference.child("Reports").push();
                report_reference3.setValue(time_indicator_failure);

                DatabaseReference report_reference4 = reference.child("Reports").push();
                report_reference4.setValue(other);
            }

            else if(resultCode == RESULT_CANCELED) {
                Log.e("DATA", "Error para nada normalito");
            }
        }
    }
}
