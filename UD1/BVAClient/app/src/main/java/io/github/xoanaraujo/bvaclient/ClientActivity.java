package io.github.xoanaraujo.bvaclient;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ClientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        EditText etName = findViewById(R.id.etName);
        EditText etSurname = findViewById(R.id.etSurname);
        EditText etLat = findViewById(R.id.etLat);
        EditText etLog = findViewById(R.id.etLog);
        CheckBox cbVIP = findViewById(R.id.cbVIP);
        Button btnSave = findViewById(R.id.btnSave);

        btnSave.setOnClickListener(view -> {

        });
    }
}