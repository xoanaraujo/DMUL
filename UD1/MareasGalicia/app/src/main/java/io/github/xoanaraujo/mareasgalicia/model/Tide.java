package io.github.xoanaraujo.mareasgalicia.model;

public class Tide {
    private boolean highTide;
    private double height;
    private String date;

    public Tide(boolean highTide, double height, String date) {
        this.highTide = highTide;
        this.height = height;
        this.date = date;
    }

    public boolean isHighTide() {
        return highTide;
    }

    public double getHeight() {
        return height;
    }

    public String getDate() {
        return date;
    }
}
