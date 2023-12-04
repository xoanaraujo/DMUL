package io.github.xoanaraujo.mareas.model;

public class Puerto {
    private int puertoId;
    private String nombre;

    public Puerto(int puertoId, String nombre) {
        this.puertoId = puertoId;
        this.nombre = nombre;
    }

    public int getPuertoId() {
        return puertoId;
    }

    public String getNombre() {
        return nombre;
    }
}
