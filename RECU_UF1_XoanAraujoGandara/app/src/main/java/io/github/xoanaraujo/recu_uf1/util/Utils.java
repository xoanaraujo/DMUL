package io.github.xoanaraujo.recu_uf1.util;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void launchToast(Context context, String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
