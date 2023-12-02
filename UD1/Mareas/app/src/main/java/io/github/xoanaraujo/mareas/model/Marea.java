package io.github.xoanaraujo.mareas.model;

import java.time.LocalTime;

public class Marea {
    private String puerto;
    private int mareaId;
    private boolean pleamar;
    private LocalTime hora;

    public Marea(String puerto, int mareaId, boolean pleamar, LocalTime hora) {
        this.puerto = puerto;
        this.mareaId = mareaId;
        this.pleamar = pleamar;
        this.hora = hora;
    }

    public String getPuerto() {
        return puerto;
    }

    public int getMareaId() {
        return mareaId;
    }

    public boolean isPleamar() {
        return pleamar;
    }

    public LocalTime getHora() {
        return hora;
    }
}
