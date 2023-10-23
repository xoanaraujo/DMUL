package io.github.xoanaraujo.appelecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import io.github.xoanaraujo.appelecciones.model.Util;
import io.github.xoanaraujo.appelecciones.model.Voter;

public class MainActivity extends AppCompatActivity {
    EditText etNIF;
    EditText etPassword;
    ImageButton btnRamen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNIF = findViewById(R.id.etNIF);
        etPassword = findViewById(R.id.etPassword);
        btnRamen = findViewById(R.id.btnRamen);
        etNIF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(Util.NifOk(etNIF.getText().toString())){
                    etNIF.setTextColor(Color.BLACK);
                } else {
                    etNIF.setTextColor(Color.RED);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        btnRamen.setOnClickListener(v ->{
            String nif = etNIF.getText().toString().trim().toUpperCase();
            Voter voter;
            try(DBHelper dbHelper = new DBHelper(this)){
                voter = dbHelper.getVoter(nif, Util.generateHash(etPassword.getText().toString().trim().toUpperCase()));
            }
            if (voter == null){
                Util.launchToast(this, "Voter not registered");
            } else {
                if (Util.NifOk(nif)){
                    Intent intent = new Intent(this, VoteActivity.class);
                    intent.putExtra("nif", nif);
                    startActivity(intent);
                } else{
                    Util.launchToast(this, "Nif format error");
                }
            }
        });
    }
}