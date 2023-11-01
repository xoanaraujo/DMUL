package com.example.elecciones.DATA;

public class Candidato {
    private String name ="";

    private int id;

    private long numVotes;

    private Partido p;

    public Candidato(String name, Partido p) {
        this.name = name;
        this.p = p;
    }
    public Candidato(){

    }
    //GETTERS

    public void setNumVotes(long numVotes) {
        this.numVotes = numVotes;
    }

    public String getName() {
        return name;
    }

    public Partido getP() {
        return p;
    }

    public long getNumVotes() {
        return numVotes;
    }
}
