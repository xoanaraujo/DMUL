package com.example.elecciones.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.DATABASE.MySqlite;
import com.example.elecciones.R;

import java.util.List;

public class ArrayAdapterCandidatoResultados extends ArrayAdapter<Candidato> {
    public static class ViewHolder{
        TextView txtNombre;

        ProgressBar progressBar;
    }
    MySqlite ms = new MySqlite(this.getContext());
    int votosTotales = 0;

    public ArrayAdapterCandidatoResultados(@NonNull Context context, @NonNull List<Candidato> objects) {
        super(context, 0, objects);
        for (Candidato c : objects) {
            votosTotales += c.getNumVotes();
        }
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Candidato c = getItem(position);
        ViewHolder viewHolder;

        // Si la vista no se ha creado, la inflamos a partir de un diseño personalizado
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_prograso_votacion, parent, false);
            viewHolder.txtNombre = convertView.findViewById(R.id.txtNombre2);
            viewHolder.progressBar = convertView.findViewById(R.id.prBarResultados);
            convertView.setTag(viewHolder);
        }
        viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.txtNombre.setText(c.getName());

        if (c.getNumVotes() != 0) {
            int porcentaje = (int) (c.getNumVotes() * 1.0 / votosTotales * 100);
            viewHolder.progressBar.setProgress(porcentaje);

        } else
            viewHolder.progressBar.setProgress(0);

        viewHolder.progressBar.setBackgroundColor(c.getP().getCorporativeColor());

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}


