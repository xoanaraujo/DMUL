package io.github.xoanaraujo.elecciones.view.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import io.github.xoanaraujo.elecciones.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LogRegisFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LogRegisFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String IS_LOGIN = "isLogin";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters

    public LogRegisFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     * @return A new instance of fragment LogRegisFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LogRegisFragment newInstance(boolean isLogin) {
        LogRegisFragment fragment = new LogRegisFragment();
        Bundle args = new Bundle();
        args.putBoolean(IS_LOGIN, isLogin);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_log_regis, container, false);

        boolean isLogin = getArguments().getBoolean(IS_LOGIN, true);

        Button btnLogRes = view.findViewById(R.id.btnLogReg);

        btnLogRes.setText(isLogin ? getString(R.string.log) : getString(R.string.regis));

        return view;
    }


}