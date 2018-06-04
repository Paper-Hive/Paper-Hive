

import edu.icesi.dmi.paperhive.Mserial.mensajeSerial;
//import panamahitek.Arduino.PanamaHitek_Arduino;
import processing.core.PApplet;
import processing.serial.*;

public class MainApp extends PApplet {

	public static void main(String[] args) {
		PApplet.main("MainApp");
		
		
		

	}
	
	Serial ino;
	//PanamaHitek_Arduino ino = new PanamaHitek_Arduino();
	Comunicacion com;
	Contador ref;
	boolean empezartiempo;
	
	
	
	@Override
	public void settings() {
		size(600, 600);

	}

	@Override
	public void setup() {
		
		empezartiempo = false;
		
		try {
			//String portName = "Serial.list()";
			//ino = new Serial(this, "portName", 9600);
			System.out.println(Serial.list().length);
			ino = new Serial(this, Serial.list()[3], 9600);
			//ino.arduinoTX("1", 9600);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		com = new Comunicacion(this);
		com.start();
	}

	@Override
	public void draw() {
		background(255);
		
		if (empezartiempo== true) {
			contarTiempo();		 
		}
			
		
		
		
	}

	public void enviarinfo(mensajeSerial ms) {
	
		try {
			
		//	if(ms.getInitialTime()== ) {
		//		
		//	}
			
			
		//	ino.write("1");
			empezartiempo = true;
			int time =30;
			Contador  timer = new Contador(time);
			timer.start();
			ref = timer;
			
			
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void contarTiempo() {
		
		if (ref != null) {
		if (ref.getTime() >27) {
			try {
				ino.write("1");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 1");
		}
		
		else if (ref.getTime() <=27 && ref.getTime() >24) {
			try {
				ino.write('2');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 2");
		}
		
		else if (ref.getTime() <=24 && ref.getTime()>21 ) {
			try {
				ino.write('3');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 3");
		}else if (ref.getTime() <=21 && ref.getTime()>18 ) {
			try {
				ino.write('4');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 4");
		}else if (ref.getTime() <=18 && ref.getTime()>15 ) {
			try {
				ino.write('5');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 5");
		}
		else if (ref.getTime() <=15 && ref.getTime()>12 ) {
			try {
				ino.write('6');
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			System.out.println("SE ENVIO UN 6");
		}	else if (ref.getTime() <=0 ) {
			try {
				ino.write('7');
				empezartiempo = false;
					
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		//	System.out.println("SE ENVIO UN 7");
		}
		
		}
		
		
		
	}

	

}
