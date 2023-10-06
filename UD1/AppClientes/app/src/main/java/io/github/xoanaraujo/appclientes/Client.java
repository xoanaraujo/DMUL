package io.github.xoanaraujo.appclientes;

public class Client extends ClientBase{

    private String provincia;

    private  boolean vip;

    private double latitud;

    private double altitud;



    public Client(String name, String apellido1, String getApellido2, String NIF, String provincia, boolean vip, double latitud, double altitud) {
        super(name, apellido1, getApellido2, NIF);
        this.provincia = provincia;
        this.vip = vip;
        this.latitud = latitud;
        this.altitud = altitud;
    }

    //GETTERS


    public String getProvincia() {
        return provincia;
    }

    public boolean isVip() {
        return vip;
    }

    public double getLatitud() {
        return latitud;
    }

    public double getAltitud() {
        return altitud;
    }
}
