package io.github.xoanaraujo.dado;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

    public class MainActivity extends AppCompatActivity{

    private static final int MAX_VALUE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DadoFragment dadoFragment = (DadoFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentDado);
        TextView tvValue = findViewById(R.id.tvVal);

        dadoFragment.setDadoInit(MAX_VALUE, value -> {
            if (tvValue.getText().toString().equals("")){
                tvValue.setText(String.valueOf(value));
            } else {
                tvValue.setText(String.valueOf(Integer.parseInt(tvValue.getText().toString())+ value));
            }
        });
    }
}