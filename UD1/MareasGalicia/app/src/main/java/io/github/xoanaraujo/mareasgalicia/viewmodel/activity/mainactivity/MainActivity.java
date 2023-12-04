package io.github.xoanaraujo.mareasgalicia.viewmodel.activity.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import io.github.xoanaraujo.mareasgalicia.R;

public class MainActivity extends AppCompatActivity {

    private Spinner spPorts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spPorts = findViewById(R.id.spinner);
    }
}