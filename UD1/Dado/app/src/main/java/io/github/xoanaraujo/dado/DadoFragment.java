package io.github.xoanaraujo.dado;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.Random;

public class DadoFragment extends Fragment {


    public interface OnDadoListener {
        void onRoll(DadoFragment dadoFragment, int maxValue);
    }
    private OnDadoListener listener;
    private Button btnDado;
    private TextView tvDebug;
    private int maxValue;

    private final boolean debugActivated = false;
    private int lastResult;


    public void setOnDadoListener(int maxValue, OnDadoListener listener){
        this.listener = listener;
        if (maxValue < 2)
            this.maxValue = 6;
        else
            this.maxValue = maxValue;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_dado, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        btnDado = view.findViewById(R.id.button);
        btnDado.setOnClickListener( v -> rollDice());
        btnDado.setEnabled(false);
        tvDebug = view.findViewById(R.id.tvDebug);
        tvDebug.setEnabled(debugActivated);
    }

    private void rollDice() {
        int value = new Random().nextInt(maxValue) + 1;
        if (debugActivated){
            if (lastResult == value)
                tvDebug.setText(String.valueOf(Integer.parseInt(tvDebug.getText().toString()) + 1));
            else
                tvDebug.setText("1");
            lastResult = value;
        }
        listener.onRoll(this, value);
        btnDado.setText(String.valueOf(value));
    }
    public void setDadoEnable(boolean b) {
        btnDado.setEnabled(b);
        if (debugActivated)
            tvDebug.setEnabled(true);
    }

}
