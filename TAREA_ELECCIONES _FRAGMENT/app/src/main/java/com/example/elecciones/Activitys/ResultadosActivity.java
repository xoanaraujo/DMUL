package com.example.elecciones.Activitys;

import android.os.Bundle;
import android.os.Handler;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elecciones.ADAPTERS.ArrayAdapterCandidatoResultados;
import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.DATABASE.MySqlite;
import com.example.elecciones.R;

import java.util.ArrayList;

public class ResultadosActivity extends AppCompatActivity {

    private ListView listResultados;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultados);

        listResultados = findViewById(R.id.listViewResultados);

        MySqlite ms = new MySqlite(this);

        ArrayList<Candidato> candidatos = ms.listarCandidatos(false);

        listResultados.setAdapter(new ArrayAdapterCandidatoResultados(this, candidatos));

        final Handler handler = new Handler();
        //Runnable que se ejecutará después de 5 segundos (5000 milisegundos)
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                finish();
            }
        };
        // Programacion el Runnable para que se ejecute después de 5 segundos
        handler.postDelayed(runnable, 5000);
    }
}
