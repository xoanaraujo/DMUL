package xoanaraujo.recu_uf1.model;

import java.io.Serializable;

public class Habitacion implements Serializable {

    private Integer idHotel;
    private Integer numero;
    private Integer precio;

    public Habitacion(Integer idHotel, Integer numero, Integer precio) {
        this.idHotel = idHotel;
        this.numero = numero;
        this.precio = precio;
    }

    public Habitacion(Integer precio) {
        idHotel = -1;
        numero = -1;
        this.precio = precio;
    }

    public Habitacion() {
        idHotel = -1;
        numero = -1;
    }

    public Integer getIdHotel() {
        return idHotel;
    }

    public Integer getNumero() {
        return numero;
    }

    public Integer getPrecio() {
        return precio;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }
}
