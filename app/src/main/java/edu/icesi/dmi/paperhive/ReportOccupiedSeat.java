package edu.icesi.dmi.paperhive;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Clase que permite reportar a un usuario por hacer mal uso de una de las sillas.
public class ReportOccupiedSeat extends AppCompatActivity {
    TextView seat_name_tv;
    CheckBox loud_cb, damage_cb, bad_use_cb;
    EditText other_et;
    Button report_btn;

    String seat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_occupied_seat);

        seat_name_tv = findViewById(R.id.report_occupied_seat_seat_name_tv);
        loud_cb = findViewById(R.id.report_occupied_seat_loud_cb);
        damage_cb = findViewById(R.id.report_occupied_seat_damage_cb);
        bad_use_cb = findViewById(R.id.report_occupied_seat_bad_use_cb);
        other_et = findViewById(R.id.report_occupied_seat_other_et);
        report_btn = findViewById(R.id.report_occupied_seat_report_btn);

        seat_name = getIntent().getExtras().getString("seat_name");
        seat_name_tv.setText(seat_name);

        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                report();
            }
        });
    }

    public void report() {
        Intent intent = new Intent();

        //Si el usuario no marca ningùn checkbox y tampoco ingresa un valor en el campo de "otra", se finaliza
        //la actividad
        if(!loud_cb.isChecked() && !damage_cb.isChecked() && !bad_use_cb.isChecked() && other_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "No se hizo ningún reporte de daño", Toast.LENGTH_LONG).show();
            finish();
        }

        else {
            //Se añaden Extras.
            if(loud_cb.isChecked() ) {
                intent.putExtra("loud", "Hace mucho ruido");
            }

            else {
                intent.putExtra("loud", "-");
            }

            if(damage_cb.isChecked() ) {
                intent.putExtra("damage", "Hizo un daño");
            }

            else {
                intent.putExtra("damage", "-");
            }

            if(bad_use_cb.isChecked() ) {
                intent.putExtra("bad_use", "No usa la mesa como debería");
            }

            else {
                intent.putExtra("bad_use", "-");
            }

            if(!other_et.getText().toString().isEmpty() ) {
                intent.putExtra("other", other_et.getText().toString() );
            }

            else {
                intent.putExtra("other", "-");
            }

            //Se finaliza la actividad.
            Toast.makeText(this, "Se hizo el reporte al usuario", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
