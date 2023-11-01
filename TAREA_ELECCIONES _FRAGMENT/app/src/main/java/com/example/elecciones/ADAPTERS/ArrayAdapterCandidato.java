package com.example.elecciones.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.R;

import java.util.List;

public class ArrayAdapterCandidato extends ArrayAdapter<Candidato> {

    public static class  ViewHolder{
        TextView txtNombre;
        TextView txtPartido;
    }

    public ArrayAdapterCandidato(@NonNull Context context, @NonNull List<Candidato> objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Candidato c = getItem(position);
        ViewHolder viewHolder;

        // Si la vista no se ha creado, la inflamos a partir de un diseño personalizado
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_candidato, parent, false);
            viewHolder.txtNombre = convertView.findViewById(R.id.txtNombre);
            viewHolder.txtPartido = convertView.findViewById(R.id.txtPartido);
            convertView.setTag(viewHolder);
        } else
            viewHolder = (ViewHolder) convertView.getTag();

        viewHolder.txtNombre.setText(c.getName());


        if(c.getP() != null){
            viewHolder.txtPartido.setText(c.getP().getAcronym());
            viewHolder.txtPartido.setTextColor(c.getP().getCorporativeColor());
        }else{
            viewHolder.txtPartido.setText("");
        }

        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
