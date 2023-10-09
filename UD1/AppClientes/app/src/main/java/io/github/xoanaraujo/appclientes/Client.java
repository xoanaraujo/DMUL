package io.github.xoanaraujo.appclientes;

public class Client extends ClientBase{

    private String province;

    private  boolean vip;

    private double latitude;

    private double longitude;



    public Client(String name, String apellido1, String NIF, String province, boolean vip, double latitude, double longitude) {
        super(name, apellido1, NIF);
        this.province = province;
        this.vip = vip;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    //GETTERS


    public String getProvince() {
        return province;
    }

    public boolean isVip() {
        return vip;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}
