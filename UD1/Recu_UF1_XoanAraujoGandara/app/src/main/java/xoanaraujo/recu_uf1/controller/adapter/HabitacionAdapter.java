package xoanaraujo.recu_uf1.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;

public class HabitacionAdapter extends ArrayAdapter<Habitacion> {
    public static class HabitacionHolder {
        TextView tvNumero;
        TextView tvPrecio;

    }

    public HabitacionAdapter(@NonNull Context context, @NonNull Habitacion[] objects) {
        super(context, 0, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Habitacion habitacion = getItem(position);
        HabitacionHolder viewHolder;
        if (convertView == null){
            viewHolder = new HabitacionHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_habitacion, parent, false);

            viewHolder.tvNumero = convertView.findViewById(R.id.tvItemHabitacionNumero);
            viewHolder.tvPrecio = convertView.findViewById(R.id.tvItemHabitacionPrecio);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HabitacionHolder) convertView.getTag();
        }

        viewHolder.tvNumero.setText(habitacion.getNumero().toString());
        viewHolder.tvPrecio.setText(habitacion.getPrecio().toString());
        return convertView;
    }
}
