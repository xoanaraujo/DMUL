package io.github.xoanaraujo.appelecciones.model;

public class Voter {
    private String nif;
    private String password;
    private boolean voted;

    public Voter(String nif, String password) {
        this.nif = nif;
        this.password = password;
    }

    public String getNif() {
        return nif;
    }

    public String getPassword() {
        return password;
    }

    public boolean hasVoted() {
        return voted;
    }
}
