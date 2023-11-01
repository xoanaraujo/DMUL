package com.example.elecciones.RECURSOS;

import android.graphics.Color;

import com.example.elecciones.DATA.Candidato;
import com.example.elecciones.DATA.Ciudadano;
import com.example.elecciones.DATA.Partido;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Recursos {

    //DATOS DE PARTIDOS DISPONIBLES
     static  public Ciudadano[] ciudadanos = new Ciudadano[]{
      new Ciudadano("1",hash("abc123.")),
            new Ciudadano("2",hash("abc123.")),
            new Ciudadano("3",hash("abc123."))
    };

    private static Partido PSOE = new Partido("PSOE", Color.rgb(255, 0, 0));
    private static Partido SUMAR = new Partido("SUMAR", Color.rgb(229, 28, 85));
    private static Partido PP = new Partido("PP", Color.rgb(30, 75, 143));
    private static Partido VOX = new Partido("VOX", Color.rgb(99, 190, 33));
    static public Partido[] listPartidos = new Partido[]{
            PSOE, SUMAR, PP, VOX
    };

    static public Candidato[] listCandidatos = new Candidato[]{
            new Candidato("Pedro Sanchez Castejón", PSOE),
            new Candidato("María Jesús Montero", PSOE),
            new Candidato("Yolanda Díaz Pérez", SUMAR),
            new Candidato("Iñigo Errejón Galván", SUMAR),
            new Candidato("Alberto Núñez Feijóo", PP),
            new Candidato("Isabel Díaz Ayuso", PP),
            new Candidato("Santiago Abascal Conde ", VOX),
            new Candidato("Ivan Espinosa de los Monteros", VOX)
    };

    //DATOS DE CANDIDATOS DISPONIBLES
    //VALIDACION DNI
    public static boolean esNIF(String nif) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        Pattern pattern = Pattern.compile(
                "(\\d{8})([" + letras + "])", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            int numero = Integer.parseInt(matcher.group(1));
            String letra = matcher.group(2);
            char letraCorrecta = letras.charAt(numero % 23);
            if (letra.toUpperCase().charAt(0) == letraCorrecta) return true;
        }
        return false;
    }

    //HASH DE CONTRASEÑA
    public static String hash(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); // --> clase que proporciona funcionalidad para calcular resúmenes criptográficos
            byte[] digest = md.digest(input.getBytes());// --> e calcula el hash de la cadena de entrada input mediante el método digest del objeto md
            StringBuffer sb = new StringBuffer();
            for (byte b : digest) {
                sb.append(String.format("%02x", b & 0xff));// -->se realiza una operación de formato para convertirlo en una cadena hexadecimal de 2 caracteres (%02x) y se agrega al objeto sb.
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
