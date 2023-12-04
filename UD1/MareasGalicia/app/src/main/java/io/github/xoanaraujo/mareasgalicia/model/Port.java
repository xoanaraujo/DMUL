package io.github.xoanaraujo.mareasgalicia.model;

public class Port {
    private int id;
    private String name;

    public Port(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
