package io.github.xoanaraujo.bvaclients;

public class Client {
    private String name;
    private String apellido1;
    private String getApellido2;
    private String NIF;


    public String getName() {
        return name;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getGetApellido2() {
        return getApellido2;
    }

    public String getNIF() {
        return NIF;
    }

    public Client(String name, String apellido1, String getApellido2, String NIF) {
        this.name = name;
        this.apellido1 = apellido1;
        this.getApellido2 = getApellido2;
        this.NIF = NIF;
    }
}
