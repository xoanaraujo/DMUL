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

import io.github.xoanaraujo.marea.R;
import io.github.xoanaraujo.mareas.model.Puerto;

public class PuertoAdapter extends ArrayAdapter<Puerto> {
     TextView tvPuerto;

    public PuertoAdapter(@NonNull List<Puerto> objects, @NonNull Context context) {
        super(context, 0, objects);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_puerto, parent, false);
            tvPuerto = convertView.findViewById(R.id.tvPuerto);
            convertView.setTag(tvPuerto);
        } else{
            Puerto puerto = getItem(position);
            tvPuerto = (TextView) convertView.getTag();
            tvPuerto.setText(puerto.toString());
        }
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
