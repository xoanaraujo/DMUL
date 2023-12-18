package xoanaraujo.examenuf1;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

import xoanaraujo.examenuf1.model.ExampleItem;

public class MainActivity extends AppCompatActivity {

    private ActivityResultLauncher<Intent> resultLauncher;
    private EditText etErrores;
    private Button btnERC, btnNEB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), null);

        etErrores = findViewById(R.id.etErrores);
        btnERC = findViewById(R.id.btnERC);
        btnNEB = findViewById(R.id.btnNEB);

        etErrores.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                SharedPreferences sp = getSharedPreferences("shared_pref", MODE_PRIVATE);
            }
        });

        btnERC.setOnClickListener(e -> {
            String textErrores = etErrores.getText().toString();
            if (!textErrores.isEmpty()){ // TODO  && textErrores.matches("+[0-9]")
                Intent intent = new Intent(this, JuegoERCActivity.class);
                int errores = Integer.parseInt(textErrores);
                intent.putExtra("errores", errores);
                resultLauncher.launch(intent);
            } else {
                Toast.makeText(this, "Campo de errores vacio", Toast.LENGTH_SHORT).show();
            }
        });

        btnNEB.setOnClickListener(e -> {
            Intent intent = new Intent(this, AplicacionNEB.class);
            resultLauncher.launch(intent);
        });
    }
}