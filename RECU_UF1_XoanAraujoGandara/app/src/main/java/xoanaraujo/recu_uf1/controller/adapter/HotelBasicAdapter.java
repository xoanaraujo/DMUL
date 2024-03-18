package xoanaraujo.recu_uf1.controller.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.model.Hotel;

public class HotelBasicAdapter extends ArrayAdapter<Hotel> {
    private final Hotel[] hoteles;
    public static class HotelBasicHolder {
        TextView tvNombre, tvEstrellas;
    }

    public HotelBasicAdapter(@NonNull Context context, @NonNull Hotel[] objects) {
        super(context, 0, objects);
        this.hoteles = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Hotel hotel = getItem(position);
        HotelBasicHolder viewHolder;
        if (convertView == null){
            viewHolder = new HotelBasicHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hotel_basic, parent, false);

            viewHolder.tvNombre = convertView.findViewById(R.id.tvItemHotelBasicNombre);
            viewHolder.tvEstrellas = convertView.findViewById(R.id.tvItemHotelBasicEstrellas);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HotelBasicHolder) convertView.getTag();
        }

        viewHolder.tvNombre.setText(hotel.getNombre());
        viewHolder.tvEstrellas.setText(String.valueOf(hotel.getEstrellas()));
        return convertView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
