package io.github.xoanaraujo.mareasgalicia.viewmodel.activity.mainactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;

import io.github.xoanaraujo.mareasgalicia.R;
import io.github.xoanaraujo.mareasgalicia.model.Port;

public class MainActivity extends AppCompatActivity {

    private Spinner spPorts;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Port[] port = new Port[3];
        port[0] = new Port(0, "Mariconeo");
        port[1] = new Port(1, "Coonio");
        port[2] = new Port(2, "abba");
        spPorts = findViewById(R.id.spinner);
        spPorts.setAdapter(new PortSpinnerAdapter(this, port));
    }
}