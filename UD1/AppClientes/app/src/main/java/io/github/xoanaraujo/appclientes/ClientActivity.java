package io.github.xoanaraujo.appclientes;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class ClientActivity extends AppCompatActivity {

    List<ClientBase> listClients;

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
            Intent mainActivityIntent = new Intent(this, MainActivity.class);
            startActivity(mainActivityIntent);
        });

        BDAssistant BDAssistant = new BDAssistant(this);
        listClients = BDAssistant.listClients();
    }
}