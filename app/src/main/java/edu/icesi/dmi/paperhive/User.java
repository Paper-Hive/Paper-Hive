package edu.icesi.dmi.paperhive;

/**
 * Created by estudiante on 24/05/18.
 */

//Clase modelo usada para guardar la información de cada usuario tomada en la actividad SignUp.
public class User {
    int reputation;

    String name, id, email, uid;

    public User() {

    }

    public User(int reputation, String name, String id, String email, String uid) {
        this.reputation = reputation;
        this.name = name;
        this.id = id;
        this.email = email;
        this.uid = uid;
    }

    public int getReputation() {
        return reputation;
    }

    public void setReputation(int reputation) {
        this.reputation = reputation;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
