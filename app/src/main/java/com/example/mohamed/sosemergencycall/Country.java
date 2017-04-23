package com.example.mohamed.sosemergencycall;

/**
 * Created by Mohamed on 05/12/2016.
 */

public class Country {

    private int id;
    private String name;
    private int police;
    private int ambulance;
    private int fire;

    @Override
    public String toString() {
        return "Country{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", police=" + police +
                ", ambulance=" + ambulance +
                ", fire=" + fire +
                '}';
    }

    public Country() {
    }

    public Country(String name, int police, int ambulance, int fire) {
        this.fire = fire;
        this.name = name;
        this.police = police;
        this.ambulance = ambulance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPolice() {
        return police;
    }

    public void setPolice(int police) {
        this.police = police;
    }

    public int getAmbulance() {
        return ambulance;
    }

    public void setAmbulance(int ambulance) {
        this.ambulance = ambulance;
    }

    public int getFire() {
        return fire;
    }

    public void setFire(int fire) {
        this.fire = fire;
    }
}
