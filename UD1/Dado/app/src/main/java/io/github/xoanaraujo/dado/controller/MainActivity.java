package io.github.xoanaraujo.dado.controller;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import java.util.ArrayList;
import java.util.HashMap;

import io.github.xoanaraujo.dado.R;

public class MainActivity extends AppCompatActivity implements DadoFragment.OnDadoListener {

    private static final String[] N_FACES = {"4", "6", "8", "10", "12", "20", "100"};
    private static  int maxValue = 4;
    private static ArrayList<DadoFragment> dados;
    private static HashMap<DadoFragment, Integer> valorDados;
    private Integer lastValue;
    private Spinner spFaces;

    private Button btnStart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dados = new ArrayList<>();
        valorDados = new HashMap<>();

        FragmentManager fragmentManager = getSupportFragmentManager();

        dados.add((DadoFragment)fragmentManager.findFragmentById(R.id.fragmentDado1));
        dados.add((DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado2));
        dados.add((DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado3));

        for (DadoFragment dado : dados) {
            dado.setOnDadoListener(this);
            dado.setMaxValue(maxValue);
        }

        btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(e -> startGame());

        spFaces = findViewById(R.id.spFaces);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, N_FACES);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner
        spFaces.setAdapter(adapter);
        spFaces.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedOption = (String) parentView.getItemAtPosition(position);
                maxValue = Integer.parseInt(selectedOption);
                for (DadoFragment dado: dados) {
                    dado.getBtnDado().setText(String.valueOf(maxValue));
                    dado.setMaxValue(maxValue);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }





    @Override
    public void onRoll(DadoFragment dadoFragment, int value) {
        valorDados.put(dadoFragment, value);

        if (lastValue == null){
            lastValue = value;
            enableDice(valorDados.size());
        } else if (lastValue == value) {
            if (valorDados.size() == dados.size()) {
                Toast.makeText(this, "GANASTE!!!", Toast.LENGTH_SHORT).show();
                valorDados = new HashMap<>();
                lastValue = null;
                endGame();
            } else
                enableDice(valorDados.size());
        } else {
            valorDados = new HashMap<>();
            lastValue = null;
            enableDice(0);
        }
    }
    private void startGame() {
        enableDice(0);
        spFaces.setEnabled(false);
        btnStart.setEnabled(false);
    }

    private void endGame() {
        enableDice(-1);
        spFaces.setEnabled(true);
        btnStart.setEnabled(true);
    }

    private void enableDice(int pos) {
        for (int i = 0; i < dados.size(); i++)
            dados.get(i).setDadoEnable(i == pos);
    }
}