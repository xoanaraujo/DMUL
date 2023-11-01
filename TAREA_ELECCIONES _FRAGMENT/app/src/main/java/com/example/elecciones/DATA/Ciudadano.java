package com.example.elecciones.DATA;

import java.io.Serializable;
import java.util.ArrayList;

public class Ciudadano implements Serializable {
    private String NIF;
    private String password;

    private boolean haVotado;

    public boolean HaVotado() {
        return haVotado;
    }

    public void votado(boolean haVotado) {
        this.haVotado = haVotado;
    }

    private int id;
    private int numVotes; //VECES QUE HA VOTADO EN UNAS MISMAS ELECCIONES

    private ArrayList<Candidato> candidatos = new ArrayList<>();

    public Ciudadano(String dni, String hashPassword) {
        NIF = dni;
        password = hashPassword;
        numVotes = 0;

    }

    //GETTERS
    public String getNIF() {
        return NIF;
    }

    public String getPassword() {
        return password;
    }

    public int getNumVotes() {
        return numVotes;
    }

    public ArrayList<Candidato> getCandidatos() {
        return candidatos;
    }


    public void addCandidato(Candidato c) {
        candidatos.add(c);
    }


    public int sizeListCandidatos() {
        return candidatos.size();
    }


}
