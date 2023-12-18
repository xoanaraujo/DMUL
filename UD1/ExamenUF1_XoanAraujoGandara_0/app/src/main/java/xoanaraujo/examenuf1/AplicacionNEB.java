package xoanaraujo.examenuf1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class AplicacionNEB extends AppCompatActivity {

    private ControlesFragment controles;
    private static int[] vector = new int[2];
    private TextView tv00, tv01, tv02, tv10, tv11, tv12, tv20, tv21, tv22, tvDesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aplicacion_neb);
        vector[0] = 0;
        vector[1] = 0;
        tv00 = findViewById(R.id.tv00);
        tv01 = findViewById(R.id.tv01);
        tv02 = findViewById(R.id.tv02);
        tv10 = findViewById(R.id.tv10);
        tv11 = findViewById(R.id.tv11);
        tv12 = findViewById(R.id.tv12);
        tv20 = findViewById(R.id.tv20);
        tv21 = findViewById(R.id.tv21);
        tv22 = findViewById(R.id.tv22);

        tvDesc = findViewById(R.id.tvDesc);
        controles = (ControlesFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentContainerView2);

        controles.setListener(ch -> {
            switch (ch){
                case 'w': {
                    vector[1] --;
                } break;
                case 'a': {
                    vector[0] --;
                } break;
                case 's': {
                    vector[1] ++;
                } break;
                case 'd': {
                    vector[0] ++;
                } break;
            }
            if (vector[0] > 2){
                vector[0] = 2;
            } else if( vector[0] < 0){
                vector[0] = 0;
            }
            if (vector[1] > 2){
                vector[1] = 2;
            } else if( vector[1] < 0){
                vector[1] = 0;
            }
            String text = "";
            switch (vector[1]){
                case 0:{
                    switch (vector[0]){
                        case 0:{
                            text = tv00.getText().toString();
                        } break;
                        case 1:{
                            text = tv01.getText().toString();
                        } break;
                        case 2:{
                            text = tv02.getText().toString();
                        } break;
                    }
                } break;
                case 1:{
                    switch (vector[0]){
                        case 0:{
                            text = tv10.getText().toString();
                        } break;
                        case 1:{
                            text = tv11.getText().toString();
                        } break;
                        case 2:{
                            text = tv12.getText().toString();
                        } break;
                    }
                } break;
                case 2:{
                    switch (vector[0]){
                        case 0:{
                            text = tv20.getText().toString();
                        } break;
                        case 1:{
                            text = tv21.getText().toString();
                        } break;
                        case 2:{
                            text = tv22.getText().toString();
                        } break;
                    }
                } break;
            }
            tvDesc.setText(vector[0] + " " + vector[1]+ " "+ text);
        }, false);


    }
}