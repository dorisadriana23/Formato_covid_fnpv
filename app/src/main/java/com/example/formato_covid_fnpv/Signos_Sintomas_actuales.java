package com.example.formato_covid_fnpv;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class Signos_Sintomas_actuales extends AppCompatActivity {
    CheckBox c1, c2, c3, c4, c5, c6,c7,c8,c9,c10,c11,c12;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signos_sintomas_actuales);
        c1 = (CheckBox) findViewById(R.id.check1);
        c2 = (CheckBox) findViewById(R.id.check2);
        c3 = (CheckBox) findViewById(R.id.check3);
        c4 = (CheckBox) findViewById(R.id.check4);
        c5 = (CheckBox) findViewById(R.id.check5);
        c6 = (CheckBox) findViewById(R.id.check6);
        c7=  (CheckBox) findViewById(R.id.check7);
        c8=  (CheckBox)findViewById(R.id.check8);
        c9=  (CheckBox) findViewById(R.id.check9);
        c10= (CheckBox)findViewById(R.id.check10);
        c11= (CheckBox)findViewById(R.id.check11);
        c12 =(CheckBox)  findViewById(R.id.check12);
    }
    public void onclick(View view) {
        if (view.getId() == R.id.btnvalidar){ ;
            validar();
        }

    }
    //validacion de chekbox
    private void validar() {
        String cad="Seleccionado: \n";
        if(c1.isChecked()) {
            cad+="Selecciono  pregunta 1\n";

        }
        if (c2.isChecked()) {
            cad+="Selecciono  pregunta 2\n";
        }
        if(c3.isChecked()) {
            cad+="Selecciono  pregunta 3\n";
        }
        if(c4.isChecked()) {
            cad+="Selecciono  pregunta 4\n";
        }
        if(c5.isChecked()) {
            cad+="Selecciono  pregunta 5\n";
        }
        if(c6.isChecked()) {
            cad+="Selecciono  pregunta 6\n";
        }
        if(c7.isChecked()) {
            cad+="Selecciono  pregunta 7\n";
        }
        if(c8.isChecked()) {
            cad+="Selecciono  pregunta 8\n";
        }
        if(c9.isChecked()) {
            cad+="Selecciono  pregunta 9\n";
        }
        if(c10.isChecked()) {
            cad+="Selecciono  pregunta 10\n";
        }
        if(c11.isChecked()) {
            cad+="Selecciono  pregunta 11\n";
        }
        if(c12.isChecked()) {
            cad+="Selecciono  pregunta 12\n";
        }
        Toast.makeText(getApplicationContext(),cad, Toast.LENGTH_SHORT).show();
    }

    public void regresarlogin(View view) {
        Intent pasar=new Intent(Signos_Sintomas_actuales.this, Login_users.class);
        startActivity(pasar);
    }
}