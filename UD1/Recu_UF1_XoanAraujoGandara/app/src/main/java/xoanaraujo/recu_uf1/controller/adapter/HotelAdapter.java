package xoanaraujo.recu_uf1.controller.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import xoanaraujo.recu_uf1.R;
import xoanaraujo.recu_uf1.db.CRUD;
import xoanaraujo.recu_uf1.db.MySQLiteHelper;
import xoanaraujo.recu_uf1.model.Habitacion;
import xoanaraujo.recu_uf1.model.Hotel;
import xoanaraujo.recu_uf1.util.Utils;

public class HotelAdapter extends ArrayAdapter<Hotel> {
    private final Hotel[] hoteles;
    public static class HotelHolder {
        TextView tvNombre, tvEstrellas;
        ImageButton iBtnDelete;
    }

    public HotelAdapter(@NonNull Context context, @NonNull Hotel[] objects) {
        super(context, 0, objects);
        this.hoteles = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Hotel hotel = getItem(position);
        HotelHolder viewHolder;
        if (convertView == null){
            viewHolder = new HotelHolder();
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_hotel, parent, false);

            viewHolder.tvNombre = convertView.findViewById(R.id.tvItemHotelNombre);
            viewHolder.tvEstrellas = convertView.findViewById(R.id.tvItemHotelEstrellas);
            viewHolder.iBtnDelete = convertView.findViewById(R.id.iBtnItemHotelBorrar);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (HotelHolder) convertView.getTag();
        }

        viewHolder.tvNombre.setText(hotel.getNombre());
        viewHolder.tvEstrellas.setText(String.valueOf(hotel.getEstrellas()));
        viewHolder.iBtnDelete.setVisibility(View.INVISIBLE);
        viewHolder.iBtnDelete.setActivated(false);
        return convertView;
    }

    private void deleteHotel(Hotel hotel) {
        SQLiteDatabase wdb = new MySQLiteHelper(getContext()).getWritableDatabase();
        Habitacion[] habitacions = CRUD.selectHabitacionesByHotel(wdb, hotel.getId());
        if(habitacions.length != 0){
            Utils.launchToast(getContext(), getContext().getString(R.string.hotel_ocupado));
            return;
        }
        if (CRUD.selectHoteles(wdb).length == 1){
            Utils.launchToast(getContext(), getContext().getString(R.string.ultimo_hotel));
            return;
        }
        CRUD.deleteHotelById(wdb, hotel.getId());
        Utils.launchToast(getContext(), hotel.getNombre() + getContext().getString(R.string.borrado));
        notifyDataSetChanged();
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return getView(position, convertView, parent);
    }
}
