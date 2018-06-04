package edu.icesi.dmi.paperhive;

/**
 * Created by estudiante on 17/05/18.
 */

//Clase modelo usada por FloorAdapter
public class Floor {
    int available_seats;
    String name, description, id;

    public Floor(){

    }

    public Floor(String name, String description, String id){
        this.name = name;
        this.description = description;
        this.id = id;
        available_seats = 0;
    }

    public int getAvailableSeats() {
        return available_seats;
    }

    public void setAvailableSeats(int available_seats) {
        this.available_seats = available_seats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
