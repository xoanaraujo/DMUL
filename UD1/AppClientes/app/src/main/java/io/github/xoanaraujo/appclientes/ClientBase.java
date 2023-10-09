package io.github.xoanaraujo.appclientes;

public class ClientBase {
    private Integer codClient;
    private String name;
    private String surname;
    private String NIF;

    private boolean VIP;

    public ClientBase(Integer codClient, String name, String apellido1, String NIF, boolean VIP) {
        this.codClient = codClient;
        this.name = name;
        this.surname = apellido1;
        this.NIF = NIF;
        this.VIP = VIP;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getNIF() {
        return NIF;
    }

    public int getCodClient () { return codClient; }
    public boolean isVIP() { return VIP; }
    @Override public String toString() { return name + ", " + surname +  "( NIF: " + NIF + ") "; }
}
