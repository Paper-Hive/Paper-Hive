package edu.icesi.dmi.paperhive.Mserial;

import java.io.Serializable;

public class mensajeSerial implements Serializable {

    private int initialTime;
    private int finalTime;


    public mensajeSerial  (int initialTime, int finalTime){

        this.initialTime = initialTime;
        this.finalTime = finalTime;


    }


    public int getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    public int getFinalTime() {
        return finalTime; 
    }

    public void setFinalTime(int finalTime) {
        this.finalTime = finalTime;
    }
}
