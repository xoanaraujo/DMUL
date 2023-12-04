package io.github.xoanaraujo.mareas.fragments;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

import io.github.xoanaraujo.mareas.R;

public class PuertoAdapter extends ArrayAdapter<String> {
    private TextView tvPuerto;

    public PuertoAdapter(@NonNull Context context, int resource) {
        super(context, 0, resource);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        String puerto = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_puerto, parent, false);
            tvPuerto.setText(convertView.findViewById(R.id.tvPuerto));
            convertView.setTag(tvPuerto);
        } else
            tvPuerto = (TextView) convertView.getTag();
        tvPuerto.setText(puerto);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return super.getDropDownView(position, convertView, parent);
    }
}
