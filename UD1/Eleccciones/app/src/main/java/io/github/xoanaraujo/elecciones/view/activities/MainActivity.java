package io.github.xoanaraujo.elecciones.view.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import io.github.xoanaraujo.elecciones.R;
import io.github.xoanaraujo.elecciones.view.fragments.LogRegisFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment fragmentLogReg = LogRegisFragment.newInstance(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer, fragmentLogReg).commit();
    }
}