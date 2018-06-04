/*
* Ultrasonic Sensor HC-SR04 and Arduino Tutorial
*
* by Dejan Nedelkovski,
* www.HowToMechatronics.com
*
*/
// defines pins numbers

 int input;


const int PledRed1 = 2;
const int PledRed2 = 3;
const int PledYel1 = 4;
const int PledYel2 = 5;
const int PledGre1 = 6;
const int PledGre2 = 7;

const int pBuzz = 13;
boolean encender;
const int trigPin = 9;
const int echoPin = 10;
// defines variables
long duration;
int distance;
void setup() {

pinMode(pBuzz, OUTPUT);
encender = false;
pinMode(PledRed1 , OUTPUT); 
pinMode(PledRed2 , OUTPUT); 
pinMode(PledYel1 , OUTPUT); 
pinMode(PledYel2 , OUTPUT); 
pinMode(PledGre1 , OUTPUT);
pinMode(PledGre2 , OUTPUT);

pinMode(trigPin, OUTPUT); // Sets the trigPin as an Output
pinMode(echoPin, INPUT); // Sets the echoPin as an Input
Serial.begin(9600); // Starts the serial communication
}
void loop() {

if (encender == true)  {
   

  }else {
    
    encender = false;
    }
 

if (Serial.available() > 0) { 
  

    input = Serial.read();
 
    if (input == '1') {
     digitalWrite(PledRed1 , HIGH);
digitalWrite(PledRed2 , HIGH);  
digitalWrite(PledYel1 , HIGH);  
digitalWrite(PledYel2 , HIGH);
       digitalWrite(PledGre1, HIGH);
       digitalWrite(PledGre2 , HIGH);
      Serial.println("LLEGO EL MENSAJE");
    } else if (input == '2') {
        digitalWrite(PledRed1 , HIGH);
        digitalWrite(PledRed2 , HIGH);  
        digitalWrite(PledYel1 , HIGH);  
        digitalWrite(PledYel2 , HIGH);
       digitalWrite(PledGre1, HIGH);
       digitalWrite(PledGre2 , LOW);
      Serial.println("LLEGO EL MENSAJE");
      
      
      }else if (input == '3') {
         digitalWrite(PledRed1 , HIGH);
        digitalWrite(PledRed2 , HIGH);  
        digitalWrite(PledYel1 , HIGH);  
        digitalWrite(PledYel2 , HIGH);
       digitalWrite(PledGre1, LOW);
       digitalWrite(PledGre2 , LOW);
      Serial.println("LLEGO EL MENSAJE");
        }else if (input == '4') {
         digitalWrite(PledRed1 , HIGH);
        digitalWrite(PledRed2 , HIGH);  
        digitalWrite(PledYel1 , HIGH);  
        digitalWrite(PledYel2 , LOW);
       digitalWrite(PledGre1, LOW);
       digitalWrite(PledGre2 , LOW);
      Serial.println("LLEGO EL MENSAJE");
        }else if (input == '5') {
         digitalWrite(PledRed1 , HIGH);
        digitalWrite(PledRed2 , HIGH);  
        digitalWrite(PledYel1 , LOW);  
        digitalWrite(PledYel2 , LOW);
       digitalWrite(PledGre1, LOW);
       digitalWrite(PledGre2 , LOW);
      Serial.println("LLEGO EL MENSAJE");
        }else if (input == '6') {
         digitalWrite(PledRed1 , HIGH);
        digitalWrite(PledRed2 , LOW);  
        digitalWrite(PledYel1 , LOW);  
        digitalWrite(PledYel2 , LOW);
       digitalWrite(PledGre1, LOW);
       digitalWrite(PledGre2 , LOW);
      Serial.println("LLEGO EL MENSAJE");
        }else if (input == '7') {
         digitalWrite(PledRed1 , LOW);
        digitalWrite(PledRed2 , LOW);  
        digitalWrite(PledYel1 , LOW);  
        digitalWrite(PledYel2 , LOW);
       digitalWrite(PledGre1, LOW);
       digitalWrite(PledGre2 , LOW);
   Serial.println("LLEGO EL MENSAJE"); 
      
        }
 

 
     
    }


  

int val = analogRead(1);
//Serial.print("Audio: ");
//Serial.println(val,DEC);
delay(100);
if(val>120){
  digitalWrite(pBuzz, HIGH);
delay(100);
 digitalWrite(pBuzz, LOW);
delay(100);
  }else {
     digitalWrite(pBuzz, LOW);
    }
// Clears the trigPin
digitalWrite(trigPin, LOW);
delayMicroseconds(2);
// Sets the trigPin on HIGH state for 10 micro seconds
digitalWrite(trigPin, HIGH);
delayMicroseconds(10);
digitalWrite(trigPin, LOW);
// Reads the echoPin, returns the sound wave travel time in microseconds
duration = pulseIn(echoPin, HIGH);
// Calculating the distance
distance= duration*0.034/2;
if(distance <10) {
 // digitalWrite(ledPIN, HIGH);
  //  digitalWrite(ledPIN2, HIGH);
  }else {
 //     digitalWrite(ledPIN, LOW);
 //         digitalWrite(ledPIN2, LOW);
    }
// Prints the distance on the Serial Monitor
//Serial.print("Distance: ");
//Serial.println(distance);

}
  
