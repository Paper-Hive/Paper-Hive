package edu.icesi.dmi.paperhive;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

//Adaptador del listado de sillas.
public class SeatAdapter extends BaseAdapter {

    private ArrayList<Seat> seats;
    private Activity activity;

    TextView tv_desk_number, tv_desk_state, tv_desk_start_time, tv_desk_finish_time;

    FirebaseAuth auth;

    public SeatAdapter(Activity activity){
        this.activity = activity;
        seats = new ArrayList<>();
        auth = FirebaseAuth.getInstance();
    }

    public void addSeat(Seat seat){
        seats.add(seat);
        notifyDataSetChanged();
    }

    //Este método se ejecuta cada vez que el usuario hace Tab sobre un item de la lista (Hive).
    public void goToStatusScreen(int position) {
        //El usuario es dirigido a la actividad AvaibleSeat en el caso de que la silla que seleccionò
        // esté disponible.
        if(seats.get(position).isOccupied() == false ) {
            goToAvaibleSeat(position);
        }

        else if(seats.get(position).isOccupied() ) {
            //El usuario es dirigido a la actividad de ReservedSeat en el caso de que la silla que seleccionò
            //halla sido reservada por él mismo.
            if(seats.get(position).getUser().equals(auth.getCurrentUser().getUid() ) ) {
                goToReservedSeat(position);
            }

            //El usuario es dirigido a la actividad de OccupiedSeat en el caso de que la silla que seleccionò
            //halla sido reservada por otra persona.
            else {
                goToOccupiedSeat(position);
            }
        }
    }

    public void goToAvaibleSeat(int position) {
        Intent intent = new Intent(activity, AvailableSeat.class);

        //Se envian los datos necesarios de la silla en Extras
        intent.putExtra("seat_name", seats.get(position).getName() );
        intent.putExtra("seat_id", seats.get(position).getId() );
        intent.putExtra("seat_floor_id", seats.get(position).getFloorId() );

        activity.startActivity(intent);
    }

    public void goToReservedSeat(int position) {
        Intent intent = new Intent(activity, ReservedSeat.class);

        //Se envian los datos necesarios de la silla en Extras
        intent.putExtra("seat_name", seats.get(position).getName() );
        intent.putExtra("seat_id", seats.get(position).getId() );
        intent.putExtra("seat_floor_id", seats.get(position).getFloorId() );
        intent.putExtra("seat_initial_time", seats.get(position).getInitialTime() );
        intent.putExtra("seat_finish_time", seats.get(position).getFinishTime() );

        activity.startActivity(intent);
    }

    public void goToOccupiedSeat(int position) {
        Intent intent = new Intent(activity, OccupiedSeat.class);

        //Se envian los datos necesarios de la silla en Extras
        intent.putExtra("seat_name", seats.get(position).getName() );
        intent.putExtra("seat_id", seats.get(position).getId() );
        intent.putExtra("seat_floor_id", seats.get(position).getFloorId() );
        intent.putExtra("seat_initial_time", seats.get(position).getInitialTime() );
        intent.putExtra("seat_finish_time", seats.get(position).getFinishTime() );
        intent.putExtra("seat_user", seats.get(position).getUser() );

        activity.startActivity(intent);
    }

    @Override
    public int getCount() {
        return seats.size();
    }

    @Override
    public Object getItem(int position) {
        return seats.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater li = activity.getLayoutInflater();
        View v = li.inflate(R.layout.seat,parent, false);

        tv_desk_number = (TextView) v.findViewById(R.id.tv_desk_number);
        tv_desk_number.setText(seats.get(position).getName() );

        tv_desk_state = (TextView) v.findViewById(R.id.tv_desk_state);
        if(seats.get(position).isOccupied() ) {
            if(seats.get(position).getUser().equals(auth.getCurrentUser().getUid() ) ) {
                tv_desk_state.setText("Reservado");
            }

            else {
                tv_desk_state.setText("Ocupado");
            }
        }

        tv_desk_start_time = (TextView) v.findViewById(R.id.tv_desk_start_time);
        if(seats.get(position).getInitialTime() == 0) {
            tv_desk_start_time.setText("-");
        }

        else {
            tv_desk_start_time.setText("" + seats.get(position).getInitialTime() );
        }

        tv_desk_finish_time = (TextView) v.findViewById(R.id.tv_desk_finish_time);
        if(seats.get(position).getFinishTime() == 0) {
            tv_desk_finish_time.setText("-");
        }

        else {
            tv_desk_finish_time.setText("" + seats.get(position).getFinishTime() );
        }

        notifyDataSetChanged();

        return v;
    }
}

