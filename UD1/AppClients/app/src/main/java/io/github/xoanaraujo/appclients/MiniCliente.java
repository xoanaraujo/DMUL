package io.github.xoanaraujo.appclients;

public class MiniCliente {
    int codCliente;
    String nombre, apellidos;
    boolean VIP;
    public MiniCliente(int codCliente,String nombre,String apellidos, boolean VIP){
        this.codCliente=codCliente;
        this.nombre=nombre;
        this.apellidos=apellidos;
        this.VIP=VIP;
    }
    public int getCodCliente () { return codCliente; }
    public boolean isVIP() { return VIP; }
    @Override public String toString() { return apellidos + ", " + nombre; }
}
