package io.github.xoanaraujo.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.security.Key;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText etName = findViewById(R.id.etName);
        Button btnName = findViewById(R.id.btnName);

        btnName.setOnClickListener(view -> {
            callToast(etName);
        });

        etName.setOnEditorActionListener((textView, actionId, keyEvent) -> {
            if(actionId == EditorInfo.IME_ACTION_DONE || actionId == EditorInfo.IME_ACTION_NEXT){
                callToast(etName);
                return true;
            }
            return false;
        });
    }

    private void callToast(EditText etName){
        if(!etName.getText().toString().equals("")){
            Toast.makeText(this, "Hola, " + etName.getText().toString(), Toast.LENGTH_LONG).show();
            etName.setText("");
        }
    }
}