package io.github.xoanaraujo.mareas.fragments;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import io.github.xoanaraujo.marea.R;

public class MareaHolder extends RecyclerView.ViewHolder {
    TextView tvEstado, tvHora, tvAltura;
    public MareaHolder(@NonNull View itemView) {
        super(itemView);
        tvEstado = itemView.findViewById(R.id.tvEstado);
        tvHora = itemView.findViewById(R.id.tvHora);
        tvAltura = itemView.findViewById(R.id.tvAltura);
    }
}
