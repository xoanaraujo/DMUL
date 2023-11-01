package com.example.elecciones.Activitys;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.elecciones.ADAPTERS.ArrayAdapterCandidato;
import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.DATA.Ciudadano;
import com.example.elecciones.DATABASE.MySqlite;
import com.example.elecciones.FRAGMENTS.BtnLimitadoFragment;
import com.example.elecciones.INTERFACES.ComunicationFragments;
import com.example.elecciones.R;

import java.util.ArrayList;

public class VotacionActivity extends AppCompatActivity implements ComunicationFragments {

    private Spinner spnCadidatos;


    private ArrayAdapterCandidato arrayAdapter;
    private TextView txtVotosRes;

    private BtnLimitadoFragment btnLimitadoFragment;
    private Ciudadano c;

    private ArrayList<Candidato> listCandidatos;

    private MySqlite ms = new MySqlite(this);


    private final int VOTOS_MAX = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_votacion);

        //ASIGNACIONES DE ID
        spnCadidatos = findViewById(R.id.spnCandidatos);
        txtVotosRes = findViewById(R.id.txtVotosRestantes);

        //Creamos el fragmento utilizando nuestro constructor y posteriormente hacemos la llamada al fragmento
        btnLimitadoFragment = new BtnLimitadoFragment("VOTAR",VOTOS_MAX);

        getSupportFragmentManager().beginTransaction().replace(R.id.LayoutFragmentBtnLimitado,btnLimitadoFragment).commit();


        //RECUPERAR VALORES PUT EXTRA
        Intent i = getIntent();
        c = (Ciudadano) i.getSerializableExtra("Ciudadano");
        txtVotosRes.setText("NUMERO DE VOTOS RESTANTES: " + (VOTOS_MAX - c.getNumVotes()));

        //CARGAR SPINNER

        listCandidatos = ms.listarCandidatos(true);
        cargarAdaptador(listCandidatos);


    }

    private void cargarAdaptador(ArrayList<Candidato> listCandidatos) {
        spnCadidatos.clearAnimation();
        arrayAdapter = new ArrayAdapterCandidato(this, listCandidatos);
        spnCadidatos.setAdapter(arrayAdapter);
    }

    private void votar(Candidato candidato, Ciudadano ciudadano) {
        if (ciudadano.sizeListCandidatos() < VOTOS_MAX ) {
            ciudadano.addCandidato(candidato);
            txtVotosRes.setText("NUMERO DE VOTOS RESTANTES: " + (VOTOS_MAX - ciudadano.sizeListCandidatos()));
            //--> ACTUALIZAR SPINNER
            actualizarSpinner(ciudadano);
        }

    }

    private void mostrarResultados() {
        Intent i = new Intent(this, ResultadosActivity.class);
        startActivity(i);
    }

    private void transaccionVotos(Ciudadano c) {
        c.votado(true);
        if (ms.updateVotos(c)) {
            launchToast("VOTACION REALIZADA CON EXITO");

        } else {
            launchToast("ERROR AL COMPLETAR SU VOTACIÓN");
            c.votado(false);
        }
    }

    //ACTUALIZAR SPINNER CADA VEZ QUE SE VOTA
    private void actualizarSpinner(Ciudadano c) {
        
        Candidato candidato = c.getCandidatos().get(c.sizeListCandidatos() - 1);
        listCandidatos.remove(candidato);
        arrayAdapter.notifyDataSetChanged();
/*        for (Candidato candidatoVotado : c.getCandidatos()) {
            if (listCandidatos.contains(candidatoVotado)) {
                listCandidatos.remove(candidatoVotado);
            }
            cargarAdaptador(listCandidatos);
        }*/
    }

    private void launchToast(String s) {
        Toast.makeText(this, s, Toast.LENGTH_LONG).show();
    }


    //METODO INTERFAZ COMUNICACIÓN
    @Override
    public void clickButton() {
        if(spnCadidatos.getSelectedItemPosition()!=0){
            Candidato candidato = listCandidatos.get(spnCadidatos.getSelectedItemPosition());
            votar(candidato, c);
        }else{
            btnLimitadoFragment.deletedLatestClick();
        }
    }

    @Override
    public void finalClick() {
        transaccionVotos(c);
        mostrarResultados();
        finish();
    }


}