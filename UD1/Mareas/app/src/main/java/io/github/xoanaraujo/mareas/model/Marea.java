package io.github.xoanaraujo.mareas.model;

import java.time.LocalTime;

public class Marea {
    private int mareaId;
    private boolean pleamar;
    private String hora;
    private double altura;

    public Marea(int mareaId, boolean pleamar, String hora, double altura) {
        this.mareaId = mareaId;
        this.pleamar = pleamar;
        this.hora = hora;
        this.altura = altura;
    }

    public int getMareaId() {
        return mareaId;
    }

    public boolean isPleamar() {
        return pleamar;
    }

    public String getHora() {
        return hora;
    }

    public double getAltura() {
        return altura;
    }
}
