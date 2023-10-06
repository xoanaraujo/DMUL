package io.github.xoanaraujo.appclientes;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<ClientBase> clientList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fabAdd);

        fab.setOnClickListener(view -> startActivity(new Intent(this, ClientActivity.class)));

        BDAssistant bdAssistant = new BDAssistant(this);
        clientList = bdAssistant.listClients();

    }
}