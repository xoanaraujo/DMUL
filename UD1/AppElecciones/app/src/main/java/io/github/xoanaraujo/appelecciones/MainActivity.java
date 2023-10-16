package io.github.xoanaraujo.appelecciones;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import io.github.xoanaraujo.appelecciones.model.Util;

public class MainActivity extends AppCompatActivity {
    EditText etNIF;
    ImageButton btnRamen;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etNIF = findViewById(R.id.etNIF);
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
    }
}