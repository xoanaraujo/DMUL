package io.github.xoanaraujo.appelecciones;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;

import io.github.xoanaraujo.appelecciones.model.Candidate;

public class VoteActivity extends AppCompatActivity {

    private ArrayList<Candidate> candidates;
    private Spinner spCandidates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote);

        try(DBHelper dbHelper = new DBHelper(this)){
            candidates = dbHelper.getCandidates();
        } catch (Exception ignore) {}
        spCandidates = findViewById(R.id.spCandidates);
        Intent intent = getIntent();
        if (intent.hasExtra("nif")){
            String nif = intent.getStringExtra("nif");
        }
    }

    class CandidateAdapter extends ArrayAdapter<Candidate> {
        public CandidateAdapter(@NonNull Context context, @NonNull ArrayList<Candidate> candidates) {
            super(context, 0, candidates);
        }
        public void populateList() {
            spCandidates.setAdapter(new CandidateAdapter(getContext(), getCandidatesData()));
        }

        private ArrayList<Candidate> getCandidatesData() {
            ArrayList<Candidate> arr = new ArrayList<>();
            return arr;
        }
    }
}