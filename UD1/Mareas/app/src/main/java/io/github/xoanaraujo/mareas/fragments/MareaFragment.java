package io.github.xoanaraujo.mareas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

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
        String xml = String.valueOf(getContext().getClass().getResource("/xml/mareas.xml"));
        spPuertos.setAdapter(new PuertoAdapter(getContext(), getPuertos(loadXMLFromString(xml))));

        RecyclerView rv = view.findViewById(R.id.fragmentContainerView);
        rv.setAdapter(new MareaAdapter(getMareas(loadXMLFromString(xml)), getContext()));
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

    private Document loadXMLFromString(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            InputSource is = new InputSource(new StringReader(xml));
            return builder.parse(is);
        }
        catch (Exception e) { return null; }
    }

    // private String getXmlMareas(int codPorto) {
    //     StringBuilder xml=new StringBuilder("");
    //     try {
    //         HttpURLConnection con = (HttpURLConnection)new URL("strUrlMareas"+codPorto) .openConnection();
    //         BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
    //         String linea;
    //         while ((linea = in.readLine()) != null)
    //             xml.append(linea);
    //     }
    //     catch (IOException ex) { return ""; }
    //     return xml.toString();
    // }
    // private void poblarListaMareas(int codPuerto) {
    //     new Thread(() -> {
    //         String xml= getXmlMareas(codPuerto);
    //         Document doc=loadXMLFromString(xml);
    //         ArrayList<Marea> mareas=getMareasConHora(getMareas(doc));
    //         runOnUiThread(new Runnable() {
    //             @Override public void run() {
    //                 updateUI(mareas);
    //             }
    //         });
    //     }).start();
    // }
}
