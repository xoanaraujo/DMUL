package io.github.xoanaraujo.recu_uf1.model;

import java.io.Serializable;

public class Grupo implements Serializable {
    private int id;
    private String nombre;

    public Grupo() {
    }

    public Grupo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Grupo(String nombre) {
        id = -1;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
