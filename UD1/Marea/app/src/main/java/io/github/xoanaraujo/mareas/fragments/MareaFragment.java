package io.github.xoanaraujo.mareas.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import java.io.StringReader;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import io.github.xoanaraujo.marea.R;
import io.github.xoanaraujo.mareas.model.Marea;
import io.github.xoanaraujo.mareas.model.Puerto;

public class MareaFragment extends Fragment {

    RecyclerView rv;
    private Document doc;
    private Spinner spPuertos;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.mareas_fragment, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<rss xmlns:Mareas=\"Mareas\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" xmlns:georss=\"http://www.georss.org/georss\" version=\"2.0\">\n" +
                "    <channel>\n" +
                "        <title>Táboa de mareas</title>\n" +
                "        <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl</link>\n" +
                "        <description>Mareas amosadas en horario local</description>\n" +
                "        <item>\n" +
                "            <title>Mareas en A Coruña para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=1&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;00:03&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:23&lt;/td&gt;&lt;td&gt;3,5&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:42&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:49&lt;/td&gt;&lt;td&gt;3,2&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=1&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.349998474121094 -8.383333206176758</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">A Coruña</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">1</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">1</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Baixamar\" hora=\"00:03\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Preamar\" hora=\"06:23\" altura=\"3,5\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Baixamar\" hora=\"12:42\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"3\" estado=\"Preamar\" hora=\"18:49\" altura=\"3,2\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Xixón para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=2&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;00:14&lt;/td&gt;&lt;td&gt;1,2&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:30&lt;/td&gt;&lt;td&gt;3,9&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:51&lt;/td&gt;&lt;td&gt;1,2&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:58&lt;/td&gt;&lt;td&gt;3,5&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=2&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.54999923706055 -5.683330059051514</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Xixón</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">2</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">2</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Baixamar\" hora=\"00:14\" altura=\"1,2\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Preamar\" hora=\"06:30\" altura=\"3,9\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Baixamar\" hora=\"12:51\" altura=\"1,2\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"3\" estado=\"Preamar\" hora=\"18:58\" altura=\"3,5\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Vigo para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=3&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:00&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:19&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:28&lt;/td&gt;&lt;td&gt;3,0&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=3&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.233333587646484 -8.716667175292969</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Vigo</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">3</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">3</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:00\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:19\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:28\" altura=\"3,0\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Vilagarcía para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=4&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:07&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:26&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:34&lt;/td&gt;&lt;td&gt;3,0&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=4&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.58333206176758 -8.766667366027832</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Vilagarcía</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">4</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:07\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:26\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:34\" altura=\"3,0\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ría de Foz para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=6&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;00:06&lt;/td&gt;&lt;td&gt;1,1&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:26&lt;/td&gt;&lt;td&gt;3,7&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:46&lt;/td&gt;&lt;td&gt;1,1&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:53&lt;/td&gt;&lt;td&gt;3,3&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=6&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.56666564941406 -7.233333110809326</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ría de Foz</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">6</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">1</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Baixamar\" hora=\"00:06\" altura=\"1,1\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Preamar\" hora=\"06:26\" altura=\"3,7\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Baixamar\" hora=\"12:46\" altura=\"1,1\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"3\" estado=\"Preamar\" hora=\"18:53\" altura=\"3,3\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Corcubión para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=7&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:13&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:33&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:40&lt;/td&gt;&lt;td&gt;3,1&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=7&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.95000076293945 -9.199999809265137</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Corcubión</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">7</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:13\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:33\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:40\" altura=\"3,1\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ría de Camariñas para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=8&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:13&lt;/td&gt;&lt;td&gt;3,5&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:33&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:40&lt;/td&gt;&lt;td&gt;3,1&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=8&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.133331298828125 -9.183333396911621</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ría de Camariñas</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">8</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:13\" altura=\"3,5\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:33\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:40\" altura=\"3,1\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ría de Corme para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=9&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:18&lt;/td&gt;&lt;td&gt;3,5&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:35&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:45&lt;/td&gt;&lt;td&gt;3,1&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=9&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.233333587646484 -9.0</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ría de Corme</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">9</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:18\" altura=\"3,5\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:35\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:45\" altura=\"3,1\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en A Guarda para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=10&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:09&lt;/td&gt;&lt;td&gt;3,1&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:33&lt;/td&gt;&lt;td&gt;0,8&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:38&lt;/td&gt;&lt;td&gt;2,7&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=10&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>41.900001525878906 -8.883333206176758</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">A Guarda</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">10</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">10</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:09\" altura=\"3,1\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:33\" altura=\"0,8\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:38\" altura=\"2,7\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ribeira para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=11&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:09&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:27&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:36&lt;/td&gt;&lt;td&gt;3,0&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=11&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.54999923706055 -8.983332633972168</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ribeira</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">11</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:09\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:27\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:36\" altura=\"3,0\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Muros para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=12&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:09&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:29&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:38&lt;/td&gt;&lt;td&gt;3,1&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=12&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.766666412353516 -9.050000190734863</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Muros</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">12</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:09\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:29\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:38\" altura=\"3,1\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Pontevedra para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=13&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:04&lt;/td&gt;&lt;td&gt;3,4&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:20&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:32&lt;/td&gt;&lt;td&gt;3,0&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=13&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.43333435058594 -8.649999618530273</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Pontevedra</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">13</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">4</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:04\" altura=\"3,4\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:20\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:32\" altura=\"3,0\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ferrol Porto Exterior para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=14&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;00:04&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:20&lt;/td&gt;&lt;td&gt;3,5&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:43&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:48&lt;/td&gt;&lt;td&gt;3,2&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=14&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.46277618408203 -8.325555801391602</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ferrol Porto Exterior</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">14</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">14</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Baixamar\" hora=\"00:04\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Preamar\" hora=\"06:20\" altura=\"3,5\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Baixamar\" hora=\"12:43\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"3\" estado=\"Preamar\" hora=\"18:48\" altura=\"3,2\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Marín para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=15&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:03&lt;/td&gt;&lt;td&gt;3,3&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:22&lt;/td&gt;&lt;td&gt;0,9&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:31&lt;/td&gt;&lt;td&gt;2,9&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=15&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>42.40611267089844 -8.691110610961914</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Marín</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">15</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">15</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Preamar\" hora=\"06:03\" altura=\"3,3\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Baixamar\" hora=\"12:22\" altura=\"0,9\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Preamar\" hora=\"18:31\" altura=\"2,9\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "        <item>\n" +
                "            <title>Mareas en Ferrol para  venres 01 de decembro</title>\n" +
                "            <link>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=16&amp;data=01/12/2023</link>\n" +
                "            <description>&lt;div  id=\"divestActual\" class=\"datos\"&gt;&lt;table class=\"tableloc\"  width=250 border=1 bordercolor=\"#cfcfcf\"&gt;&lt;thead&gt;&lt;th&gt;Estado&lt;/th&gt;&lt;th&gt;Hora&lt;/th&gt;&lt;th&gt;Altura&lt;/th&gt;&lt;/thead&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;00:06&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;06:25&lt;/td&gt;&lt;td&gt;3,6&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Baixamar&lt;/td&gt;&lt;td&gt;12:45&lt;/td&gt;&lt;td&gt;1,0&lt;/td&gt;&lt;/tr&gt;&lt;tr&gt;&lt;td&gt;Preamar&lt;/td&gt;&lt;td&gt;18:53&lt;/td&gt;&lt;td&gt;3,2&lt;/td&gt;&lt;/tr&gt;&lt;/table&gt;&lt;/div&gt;&lt;br/&gt;</description>\n" +
                "            <pubDate>Thu, 30 Nov 2023 23:00:00 GMT</pubDate>\n" +
                "            <guid>https://www.meteogalicia.gal/web/predicion/maritima/mareasIndex.action?request_locale=gl&amp;idPorto=16&amp;data=01/12/2023</guid>\n" +
                "            <dc:date>2023-11-30T23:00:00Z</dc:date>\n" +
                "            <georss:point>43.476112365722656 -8.248332977294922</georss:point>\n" +
                "            <Mareas:nomePorto descricion=\"Porto\">Ferrol</Mareas:nomePorto>\n" +
                "            <Mareas:idPorto descricion=\"IdPorto\">16</Mareas:idPorto>\n" +
                "            <Mareas:idPortoRef descricion=\"IdPortoRef\">16</Mareas:idPortoRef>\n" +
                "            <Mareas:dataPredicion formato=\"dd/MM/yyyy\">01/12/2023</Mareas:dataPredicion>\n" +
                "            <Mareas:mareas id=\"0\" estado=\"Baixamar\" hora=\"00:06\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"1\" estado=\"Preamar\" hora=\"06:25\" altura=\"3,6\" idTipoMarea=\"1\" />\n" +
                "            <Mareas:mareas id=\"2\" estado=\"Baixamar\" hora=\"12:45\" altura=\"1,0\" idTipoMarea=\"0\" />\n" +
                "            <Mareas:mareas id=\"3\" estado=\"Preamar\" hora=\"18:53\" altura=\"3,2\" idTipoMarea=\"1\" />\n" +
                "        </item>\n" +
                "    </channel>\n" +
                "</rss>\n" +
                "\n"; // getContext().getResources().getXml(R.xml.mareas);
        doc = loadXMLFromString(xml);
        rv = view.findViewById(R.id.rViewMareas);
        spPuertos = view.findViewById(R.id.spPuerto);
        //spPuertos.setAdapter(new PuertoAdapter(getPuertos(loadXMLFromString(xml)), getContext()));
        spPuertos.setAdapter(new ArrayAdapter(getContext(),android.R.layout.simple_list_item_1, getPuertos(doc)));
        spPuertos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                updateUI();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
        rv.setAdapter(new MareaAdapter(getMareas(doc, ((Puerto) spPuertos.getSelectedItem()).getNombre()), getContext()));
    }

    private void updateUI() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                rv.setAdapter(new MareaAdapter(getMareas(doc, (()spPuertos.getSelectedItem()))));
            }
        });
    }

    private ArrayList<Marea> getMareas(Document doc, String nomePorto) {
        ArrayList<Marea> mareas=new ArrayList<>();
        NodeList idPuertoNl = doc.getElementsByTagName("Mareas:nomePorto");
        for (int i = 0; i < idPuertoNl.getLength(); i++) {
            Element idPuertoEl = (Element) idPuertoNl.item(i);
            if(idPuertoEl.getFirstChild().getNodeValue().equals(nomePorto)){
                Element parent = (Element) idPuertoEl.getParentNode();
                NodeList mareasNl = parent.getElementsByTagName("Mareas:mareas");
                for (int j = 0; j < mareasNl.getLength(); j++) {
                    Element mareaEl = (Element) mareasNl.item(i);
                    int id = Integer.parseInt(mareaEl.getAttribute("id"));
                    int estado = Integer.parseInt(mareaEl.getAttribute("idTipoMarea"));
                    String hora = mareaEl.getAttribute("hora");
                    double altura=Float.parseFloat(mareaEl.getAttribute("altura").replace(",","."));
                    mareas.add(new Marea(id, estado == 1, hora, altura));
                }
                break;
            }
        }
        return mareas;
    }

    private ArrayList<Puerto> getPuertos(Document doc){
        ArrayList<Puerto> puertos = new ArrayList<>();
        NodeList nlNombrePuerto = doc.getElementsByTagName("Mareas:idPorto");
        for (int i = 0; i < nlNombrePuerto.getLength(); i++){
            Element element = (Element) nlNombrePuerto.item(i);
            puertos.add(new Puerto(Integer.parseInt(element.getFirstChild().getNodeValue()), element.getPreviousSibling().getPreviousSibling().getFirstChild().getNodeValue()));

        }
        return puertos;
    }

    private Document loadXMLFromString(String xml) {
        try {
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);
            factory.setNamespaceAware(true);
            factory.setIgnoringComments(true);
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
