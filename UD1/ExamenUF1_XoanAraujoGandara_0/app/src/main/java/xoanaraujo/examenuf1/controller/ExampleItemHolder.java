package xoanaraujo.examenuf1.controller;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import xoanaraujo.examenuf1.R;

public class ExampleItemHolder extends RecyclerView.ViewHolder{
    TextView tvName, tvIncId;

    public ExampleItemHolder(@NonNull View itemView) {
        super(itemView);
        tvIncId = itemView.findViewById(R.id.tvIncId);
        tvName = itemView.findViewById(R.id.tvName);
    }
}