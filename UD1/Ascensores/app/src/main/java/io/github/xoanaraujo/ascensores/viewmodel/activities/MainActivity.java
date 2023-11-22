package io.github.xoanaraujo.ascensores.viewmodel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.xoanaraujo.ascensores.R;

/**
 * Simulador de ascensores en una comunidad. Se puede cambiar de piso en cada ascensor (visualizando siempre el piso actual).
 * Se puede pulsar el botón de SOS. Un gestor que controla todos los ascensores visualizará y almacenará toda la información
 * de cada ascensor, incluyendo los gastos a pasarle a la comunidad incluyendo COSTE_VIAJE, COSTE_PLANTA y COSTE_SOS. La
 * información se almacenará/recuperará de una BD.
 * @author Xoan Araujo
 * @version 1.0*/
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}