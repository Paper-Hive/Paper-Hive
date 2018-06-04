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

//Clase que permite reportar un daño presente en una silla.
public class ReportReservedSeat extends AppCompatActivity {
    TextView seat_name_tv;
    CheckBox scratched_cb, connection_failure_cb, time_indicator_failure_cb;
    EditText other_et;
    Button report_btn;

    String seat_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.report_reserved_seat);

        seat_name_tv = findViewById(R.id.report_reserved_name_tv);
        scratched_cb = findViewById(R.id.report_reserved_scratched_cb);
        connection_failure_cb = findViewById(R.id.report_reserved_connection_failure_cb);
        time_indicator_failure_cb = findViewById(R.id.report_reserved_time_indicator_failure_cb);
        other_et = findViewById(R.id.report_reserved_other_et);
        report_btn = findViewById(R.id.report_reserved_report_btn);

        seat_name = getIntent().getStringExtra("seat_name");
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
        if(!scratched_cb.isChecked() && !connection_failure_cb.isChecked() && !time_indicator_failure_cb.isChecked() && other_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "No se hizo ningún reporte de daño", Toast.LENGTH_LONG).show();
            finish();
        }

        else {
            //Se añaden Extras.
            if(scratched_cb.isChecked() ) {
                intent.putExtra("scratched", "Está rayado");
            }

            else {
                intent.putExtra("scratched", "-");
            }

            if(connection_failure_cb.isChecked() ) {
                intent.putExtra("connection_failure", "La conexión con otras mesas no funciona");
            }

            else {
                intent.putExtra("connection_failure", "-");
            }

            if(time_indicator_failure_cb.isChecked() ) {
                intent.putExtra("time_indicator_failure", "El indicador de tiempo no funciona");
            }

            else {
                intent.putExtra("time_indicator_failure", "-");
            }

            if(!other_et.getText().toString().isEmpty() ) {
                intent.putExtra("other", other_et.getText().toString() );
            }

            else {
                intent.putExtra("other", "-");
            }

            //Se finaliza la actividad.
            Toast.makeText(this, "Se reportó el daño", Toast.LENGTH_LONG).show();
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
