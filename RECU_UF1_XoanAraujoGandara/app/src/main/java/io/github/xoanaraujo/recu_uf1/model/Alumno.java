package io.github.xoanaraujo.recu_uf1.model;

import java.io.Serializable;

public class Alumno implements Serializable {
    private int id;
    private String dni;
    private String nombre;
    private int idGrupo;

    public Alumno() {
    }

    public Alumno(String dni, String nombre, int idGrupo) {
        id = -1;
        this.dni = dni;
        this.nombre = nombre;
        this.idGrupo = idGrupo;
    }

    public Alumno(int id, String dni, String nombre, int idGrupo) {
        this.id = id;
        this.dni = dni;
        this.nombre = nombre;
        this.idGrupo = idGrupo;
    }

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(int idGrupo) {
        this.idGrupo = idGrupo;
    }

    @Override
    public String toString() {
        return id + " " + dni + " " + nombre + " " + idGrupo;
    }
}
