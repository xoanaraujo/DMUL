package io.github.xoanaraujo.mareas.fragments;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.xoanaraujo.mareas.model.Marea;

public class MareaAdapter extends RecyclerView.Adapter<MareaHolder> {
    private List<Marea> mareas;
    @NonNull
    @Override
    public MareaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull MareaHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
