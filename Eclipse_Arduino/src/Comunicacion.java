import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

import edu.icesi.dmi.paperhive.Mserial.mensajeSerial;



public class Comunicacion extends Thread {

	DatagramSocket ds;
	MainApp main;

	public Comunicacion(MainApp m) {

		this.main = m;
		try {
			ds = new DatagramSocket(5000);
		} catch (Exception e) {

			e.printStackTrace();
		}

	}

	@Override
	public void run() {
		while (true) {
			try {
				System.out.println("EsperandoMensaje...");
				recibirMensaje();
				sleep(100);
			} catch (Exception e) {

			}

		}

	}

	private void recibirMensaje()  {

		byte[] buf = new byte[1024];
		DatagramPacket p = new DatagramPacket(buf, buf.length);
		try {
			
			ds.receive(p);
			System.out.println("Mensajee Recibido");
			ByteArrayInputStream bais = new ByteArrayInputStream(p.getData());
			ObjectInputStream entrada = new ObjectInputStream(bais);
			mensajeSerial ms = (mensajeSerial) entrada.readObject();
			
			main.enviarinfo(ms);
			
			

		} catch (IOException e) {

			e.printStackTrace();
		} //catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
 catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
