package com.example.elecciones.DATA;

import java.util.ArrayList;

public class Partido {
    private String acronym;

    private int corporativeColor;

    private int id;
    private ArrayList<Candidato> listCandidate;


    public Partido(String acronym, int corporativeColor) {
        this.acronym = acronym;
        this.corporativeColor = corporativeColor;
    }

    //GETTERS
    public String getAcronym() {
        return acronym;
    }

    public int getCorporativeColor() {
        return corporativeColor;
    }
}
