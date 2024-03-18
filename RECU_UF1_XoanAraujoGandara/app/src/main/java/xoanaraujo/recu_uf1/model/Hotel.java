package xoanaraujo.recu_uf1.model;

import java.io.Serializable;

public class Hotel implements Serializable {
    private Integer id;
    private String nombre;
    private Integer estrellas;

    public Hotel(Integer id, String nombre, Integer estrellas) {
        this.id = id;
        this.nombre = nombre;
        this.estrellas = estrellas;
    }

    public Hotel(String nombre, Integer estrellas) {
        id = -1;
        this.nombre = nombre;
        this.estrellas = estrellas;
    }

    public Hotel() {
        id = -1;
    }

    public Integer getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEstrellas() {
        return estrellas;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setEstrellas(Integer estrellas) {
        this.estrellas = estrellas;
    }
}
