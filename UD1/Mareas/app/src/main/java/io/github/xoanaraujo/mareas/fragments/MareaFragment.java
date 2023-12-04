package io.github.xoanaraujo.mareas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import io.github.xoanaraujo.mareas.R;
import io.github.xoanaraujo.mareas.model.Marea;
import io.github.xoanaraujo.mareas.model.Puerto;

public class MareaFragment extends Fragment {



    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mareas_fragment, container);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Spinner spPuertos = view.findViewById(R.id.spPuerto);
        spPuertos.
    }

    private ArrayList<Marea> getMareas(Document doc) {
        ArrayList<Marea> mareas = new ArrayList<>();
        NodeList nodeList = doc.getElementsByTagName("Mareas:mareas");
        for (int i = 0; i < nodeList.getLength(); i++) {
            Element e = (Element) nodeList.item(i);
            int mareaId = Integer.parseInt(e.getAttribute("id"));
            boolean pleamar = e.getAttribute("idTipoMarea").equals("1");
            String txtEstado = getString(pleamar ? R.string.pleamar : R.string.bajamar);
            float altura = Float.parseFloat(e.getAttribute("altura").replace(",", "."));
            String horaCompleta = e.getAttribute("hora");
            mareas.add(new Marea(mareaId, pleamar, horaCompleta, altura));
        }
        return mareas;
    }

    private ArrayList<String> getPuertos(Document doc){
        ArrayList<String> puertos = new ArrayList<>();
        NodeList nlNombrePuerto = doc.getElementsByTagName("Mareas:nomePorto");
        for (int i = 0; i < nlNombrePuerto.getLength(); i++)
            puertos.add(nlNombrePuerto.item(i).getNodeValue());
        return puertos;
    }
}
