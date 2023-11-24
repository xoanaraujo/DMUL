package io.github.xoanaraujo.dadopro.viewmodel.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import io.github.xoanaraujo.dadopro.R;

public class MainActivity extends AppCompatActivity {

    /**
     * La aplicación diseñará un fragmento independiente denominado FrgDado, que gestionará all lo relacionado con el lanzamiento y gestión de un dado.
     * Un dado se podrá lanzar de tres maneras:
     * manualmente, con un clic en un botón
     * a través de una lista desplegable
     * invocado desde fuera del fragmento a través de un método público
     * en cualquiera de los casos anteriores, se generará un número aleatorio entre 1 y el n.º de caras del dado (6,8,10,12,20 …).
     * El n.º obtenido en el lanzamiento se podrá mostrar en el mismo botón que se utiliza para lanzarlo manualmente.
     * Existirá en el fragmento un modo debug (activado por defecto), que visualizará (entre paréntesis) al lado del número que ha salido en el lanzamiento, la racha actual del dado.
     * La racha del dado es las veces que ha salido el último número de manera consecutiva.
     * Por ejemplo, sale un 6 (racha 1), sale un 3 (racha 1), sale otro 3 (racha 2), sale otro 3 (racha 3), sale un 5 (racha 1), sale un 6 (racha 1), …
     * Dicho fragmento se utilizará para implementar el siguiente ejercicio:
     * Realice un juego cuyo objetivo consistirá en obtener el mismo número en la tirada de varios dados (en principio NUM_DADOS=2(*))
     * NOTA: Para simplificar el ejercicio, se puede asumir que primero se lanzan los dados por orden. Es decir, én el caso de haber dos dados, primero se lanza el 1 y luego se lanza el segundo y último (generando su número aleatorio) y se comparan en ese momento. Si se lanza el segundo sin haber lanzado el primero, pues no se le haría caso.
     * Constará en su actividad principal de:
     * - tantos FrgDado como indique NUM_DADOS (que coincidan, no generar interfaces dinámicos!)
     * - Un botón "Comenzar", que indicará el momento en que se podrá empezar a lanzar los dados (no podremos antes). El texto del botón cambiará en función del idioma ("Start" en inglés).
     * - Un TextView donde se mostrará el n.º de lanzamientos totales efectuados hasta el momento
     * Cuando se obtenga una combinación correcta (los valores de los dos dados son iguales) se mostrará en un Toast el texto "Fin de la partida"/"The End".
     * En ese momento se nos llevará a una nueva actividad en donde se guardarán automáticamente los datos de nuestra partida (fechaHora,NúmeroIntentos,mayorRachaDeLaPartida,dificultad) en una base de datos SQLite.
     * La dificultad es la suma del número de caras de los dados existentes.
     * En dicha actividad, después de guardar nuestra partida, se visualizará una lista (ordenada por número de intentos) con los datos de las partidas almacenadas, para la dificultad actual.
     * Existirá un botón para borrar los records visualizados (para esa dificultad).
     * Independientemente de que hayamos realizado el record o no, podremos comenzar otra partida nueva, cerrando la actividad de records y volviendo a pulsar el botón "Comenzar".
     * (*) El nº de fragments del interfaz principal debe coincidir con el valor de la constante NUM_DADOS. El resto de la lógica de la aplicación debe funcionar independientemente del valor de la constante.
     * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}