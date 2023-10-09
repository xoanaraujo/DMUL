package io.github.xoanaraujo.appclientes;

public class Provincia {
    private int codProvincia;
    private String nombre;
    public Provincia(int codProvincia, String nombre) {
        this.codProvincia=codProvincia;
        this.nombre=nombre;
    }
    public int getCodProvincia() { return codProvincia; }
    @Override public String toString() { return nombre; }
}
