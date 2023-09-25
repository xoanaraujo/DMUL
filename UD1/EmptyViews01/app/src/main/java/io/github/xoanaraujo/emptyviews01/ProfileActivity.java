package io.github.xoanaraujo.emptyviews01;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Config options = Config.getInstance();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Button btnSave = findViewById(R.id.btnSave);
        EditText etName = findViewById(R.id.etName);
        EditText etEmail = findViewById(R.id.etEmail);
        EditText etAge = findViewById(R.id.etAge);
        CheckBox cbATerms = findViewById(R.id.cbTerms);

        btnSave.setOnClickListener(view -> {
            options.set(etName.getText().toString(), etEmail.getText().toString(), etAge.getText().toString(), cbATerms.isActivated());
            finish();
        });
    }
}