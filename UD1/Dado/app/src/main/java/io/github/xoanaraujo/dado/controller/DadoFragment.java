package io.github.xoanaraujo.dado.controller;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.GradientDrawable;
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

import io.github.xoanaraujo.dado.R;

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


    public void setOnDadoListener(OnDadoListener listener) {
        this.listener = listener;
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
        // Define la animación de escala (disminuir y aumentar)
        ObjectAnimator scaleDownX = ObjectAnimator.ofFloat(btnDado, "scaleX", 0.5f);
        ObjectAnimator scaleDownY = ObjectAnimator.ofFloat(btnDado, "scaleY", 0.5f);

        ObjectAnimator scaleUpX = ObjectAnimator.ofFloat(btnDado, "scaleX", 1f);
        ObjectAnimator scaleUpY = ObjectAnimator.ofFloat(btnDado, "scaleY", 1f);

        int duration = 100;
        scaleDownX.setDuration(duration);
        scaleDownY.setDuration(duration);
        scaleUpX.setDuration(duration);
        scaleUpY.setDuration(duration);

        // Define la animación de rotación
        ObjectAnimator rotation = ObjectAnimator.ofFloat(btnDado, "rotation", 0f, 360f);
        rotation.setDuration(duration * 2);

        final AnimatorSet scaleDownSet = new AnimatorSet();
        scaleDownSet.playTogether(scaleDownX, scaleDownY);

        final AnimatorSet scaleUpSet = new AnimatorSet();
        scaleUpSet.playTogether(scaleUpX, scaleUpY);

        final AnimatorSet combinedSet = new AnimatorSet();
        combinedSet.playSequentially(scaleDownSet, rotation, scaleUpSet);
        btnDado.setOnClickListener(v -> {
            rollDice();
            animateRoll(combinedSet);
        });
        btnDado.setEnabled(false);
        tvDebug = view.findViewById(R.id.tvDebug);
        tvDebug.setEnabled(debugActivated);
    }

    private void animateRoll(AnimatorSet combinedSet) {
        combinedSet.start();
    }

    private void rollDice() {
        int value = new Random().nextInt(maxValue) + 1;
        if (debugActivated) {
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
        btnDado.setTypeface(b ? Typeface.DEFAULT_BOLD : Typeface.DEFAULT);
        if (debugActivated)
            tvDebug.setEnabled(true);
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public Button getBtnDado() {
        return btnDado;
    }

}
