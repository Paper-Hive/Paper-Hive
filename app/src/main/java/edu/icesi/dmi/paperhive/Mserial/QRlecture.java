package edu.icesi.dmi.paperhive.Mserial;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.google.zxing.qrcode.encoder.QRCode;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import edu.icesi.dmi.paperhive.Home;
import edu.icesi.dmi.paperhive.R;
import edu.icesi.dmi.paperhive.ReserveSeat;

//Clase usada para la lectura del código QR.
public class QRlecture extends AppCompatActivity {


    private Button btn_scannner;
    private Button btn_volver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qrlecture);

    btn_scannner = (Button) findViewById(R.id.btn_leerQR);
    btn_volver = (Button) findViewById(R.id.btn_volver_a_reserva);


btn_volver.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        Intent i = new Intent(QRlecture.this, ReserveSeat.class);
        startActivity(i);
    }
});




    btn_scannner.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {

            new Thread(new Runnable() {

                //final int initialTime = getIntent().getExtras().getInt("initial_time");
                // final int finishTime = getIntent().getExtras().getInt("finish_time");


                @Override
                public void run() {

                    try {
                        DatagramSocket ds = new DatagramSocket();
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        ObjectOutputStream salida = new ObjectOutputStream(baos);

                        int   tiempoInicialeclipse = 10;
                        int tiempoFinaleclipse =  30;


                        salida.writeObject(new mensajeSerial(10, 30));
                        byte [] buf = baos.toByteArray();
                       InetAddress ip = InetAddress.getByName("10.0.2.2");
                       // InetAddress ip = InetAddress.getByName("172.30.169.105");
                        DatagramPacket p = new DatagramPacket(buf, buf.length, ip, 5000);
                        ds.send(p);
                        ds.close();

                    } catch (SocketException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }




                }
            }).start();



           IntentIntegrator integrator = new IntentIntegrator(QRlecture.this);
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE_TYPES);
            integrator.setPrompt("scan");
           integrator.setCameraId(0);
            integrator.setBeepEnabled(false);
           integrator.setBarcodeImageEnabled(false);
           integrator.initiateScan();

        }
    });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode,data);

        if(result != null) {


            if (result.getContents() ==null) {

                Toast.makeText(this, "Puedes cancelar el scan", Toast.LENGTH_SHORT).show();

            } else {




                Intent i = new Intent(QRlecture.this, Home.class);
                startActivity(i);

            }
        }
            else {

                super.onActivityResult(requestCode,resultCode,data);



        }


    }
}
