package io.github.xoanaraujo.dado;

import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity implements DadoFragment.OnDadoListener {

    private static final int MAX_VALUE = 6;
    private static ArrayList<DadoFragment> dados;
    private static HashMap<DadoFragment, Integer> valorDados;
    private Integer lastValue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dados = new ArrayList<>();
        valorDados = new HashMap<>();
        dados.add((DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado1));
        dados.add((DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado2));
        dados.add((DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado3));

        Button btnStart = findViewById(R.id.btnStart);
        btnStart.setOnClickListener(e -> startGame());
    }

    private void startGame() {
        for (int i = 0; i < dados.size(); i++) {
            if (i == 0)
                dados.get(0).setDadoEnable(true);
            else
                dados.get(i).setDadoEnable(false);
        }
    }


    @Override
    public void onRoll(DadoFragment dadoFragment, int value) {
        valorDados.put(dadoFragment, value);
        if (lastValue == 0)
            lastValue = value;
        else if (value == lastValue){
            enableDice(valorDados.size());
        } else {
            valorDados = new HashMap<>();
            startGame();
        }
    }

    private void enableDice(int pos) {
        dados.get(pos).setDadoEnable(true);
    }
}