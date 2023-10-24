package io.github.xoanaraujo.appelecciones.model;

public class Candidate {
    private int codCandidate;
    private String name;
    private String party;
    private Integer votes;

    public Candidate(int codCandidate, String name, String party, Integer votes) {
        this.codCandidate = codCandidate;
        this.name = name;
        this.party = party;
        this.votes = votes;
    }

    public String getName() {
        return name;
    }

    public String getParty() {
        return party;
    }

    public Integer getVotes() {
        return votes;
    }
}
