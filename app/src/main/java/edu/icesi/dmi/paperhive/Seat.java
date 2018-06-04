package edu.icesi.dmi.paperhive;

/**
 * Created by Andres Paredes on 19/05/2018.
 */

//Clase modelo usada por SeatAdapter
public class Seat {

    private int initialTime, finishTime;
    private boolean isOccupied;
    String name, id, floorId;
    private String user;
    public Seat() {

    }

    public Seat(String name){
        isOccupied = false;
        this.name = name;
        user = "-";
    }

    public int getInitialTime() {
        return initialTime;
    }

    public void setInitialTime(int initialTime) {
        this.initialTime = initialTime;
    }

    public int getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(int finishTime) {
        this.finishTime = finishTime;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFloorId() {
        return floorId;
    }

    public void setFloorId(String floorId) {
        this.floorId = floorId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
