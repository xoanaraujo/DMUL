package io.github.xoanaraujo.mareasgalicia.viewmodel.activity.mainactivity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.xoanaraujo.mareasgalicia.R;
import io.github.xoanaraujo.mareasgalicia.model.Tide;

public class TideAdapter extends RecyclerView.Adapter<TideAdapter.TideHolder> {
    protected class TideHolder extends RecyclerView.ViewHolder {
        public TideHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
    private List<Tide> tides;
    private Context context;

    public TideAdapter(List<Tide> tides, Context context) {
        this.tides = tides;
        this.context = context;
    }

    @NonNull
    @Override
    public TideHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TideHolder(LayoutInflater.from(context).inflate(R.layout.item_tide_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TideHolder holder, int position) {
        Tide tide = tides.get(position);
        holder.tv
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
