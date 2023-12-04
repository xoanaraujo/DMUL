package io.github.xoanaraujo.mareas.viewmodel.fragment;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.xoanaraujo.marea.R;
import io.github.xoanaraujo.mareas.model.Marea;

public class MareaAdapter extends RecyclerView.Adapter<MareaHolder> {
    private List<Marea> mareas;
    private Context context;
    private OnItemSelectedListener itemSelectedListener;
    private interface OnItemSelectedListener{
        void onItemSelected(int position);
    }

    public void setItemSelectedListener(OnItemSelectedListener itemSelectedListener) {
        this.itemSelectedListener = itemSelectedListener;
    }

    public MareaAdapter(List<Marea> mareas, Context context) {
        this.mareas = mareas;
        this.context = context;
    }

    @NonNull
    @Override
    public MareaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MareaHolder(LayoutInflater.from(context).inflate(R.layout.item_mareas, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MareaHolder holder, int position) {
        Marea marea = mareas.get(position);
        holder.tvEstado.setText(marea.isPleamar() ? context.getString(R.string.pleamar) :  context.getString(R.string.bajamar));
        holder.tvHora.setText(String.valueOf(marea.getHora()));
        holder.tvAltura.setText(String.valueOf(marea.getAltura()));
    }

    @Override
    public int getItemCount() {
        return mareas.size();
    }
}
