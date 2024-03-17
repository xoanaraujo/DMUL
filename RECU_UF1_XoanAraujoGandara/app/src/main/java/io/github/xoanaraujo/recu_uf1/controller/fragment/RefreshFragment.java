package io.github.xoanaraujo.recu_uf1.controller.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import io.github.xoanaraujo.recu_uf1.R;

public class RefreshFragment extends Fragment {
    public interface RefreshListener{
        void refresh();
    }
    private RefreshListener listener;
    private ImageButton iBtnRefresh;
    public RefreshFragment() {
    }

    public RefreshFragment(RefreshListener listener) {
        this.listener = listener;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_refresh, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        iBtnRefresh = view.findViewById(R.id.iBtnFrgRefresh);
        iBtnRefresh.setOnClickListener(e -> refresh());
    }

    private void refresh() {
        listener.refresh();
    }
}