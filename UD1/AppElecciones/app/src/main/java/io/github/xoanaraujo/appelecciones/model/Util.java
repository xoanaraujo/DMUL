package io.github.xoanaraujo.appelecciones.model;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

    static public boolean NifOk(String nif) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        Pattern pattern = Pattern.compile("(\\d{8})([" + letras + "])",Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(nif);
        if (matcher.matches()) {
            int numero = Integer.parseInt(matcher.group(1));
            String letra = matcher.group(2);
            char letraCorrecta = letras.charAt(numero % 23);
            if (letra.toUpperCase().charAt(0) == letraCorrecta) return true;
        }
        return false;
    }
    public static String generateHash(String text) {
        MessageDigest md;
        byte[] hash;
        try {
            md = MessageDigest.getInstance("SHA-512");
            hash = md.digest(text.getBytes("UTF-8"));
        } catch (NoSuchAlgorithmException e) {
            return "";
        } catch (UnsupportedEncodingException e) {
            return "";
        }
        return convertToHex(hash);
    }

    private static String convertToHex(byte[] raw) {
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < raw.length; i++)
            sb.append(Integer.toString((raw[i] & 0xff) + 0x100, 16).substring(1));
        return sb.toString();
    }
}
