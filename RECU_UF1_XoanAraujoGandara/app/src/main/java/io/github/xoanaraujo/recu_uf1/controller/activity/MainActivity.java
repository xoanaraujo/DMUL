package io.github.xoanaraujo.recu_uf1.controller.activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import io.github.xoanaraujo.recu_uf1.R;

public class MainActivity extends AppCompatActivity {

    private Button btnManagement;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnManagement = findViewById(R.id.btnManagement);

        btnManagement.setOnClickListener(e -> loadActivity(ManagementActivity.class));
    }

    private void loadActivity(Class<? extends AppCompatActivity> activityClass) {
        Intent intent = new Intent(this, activityClass);
        startActivity(intent);
    }
}