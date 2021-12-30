package com.example.formato_covid_fnpv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.FirebaseApp;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.HashMap;
import java.util.Map;

public class Composicio_Familiar extends AppCompatActivity {
    public static String user="names";
    TextView txtuser;
    private TextView tv;
    EditText editnameapellido, editidentificacionm, editDescripcionActivity,
            editparentesco, editEps,editmotivovisita,editpcr,editfecha_toma,editAntigeno;
    Button btnGuardarFamiliar;
    public static final String TAG = "DocSnippets";
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_composicio_familiar);
        txtuser=(TextView) findViewById(R.id.form);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        db.setFirestoreSettings(settings);
        editnameapellido = findViewById(R.id.editnameapellido);
        editidentificacionm = findViewById(R.id.editidentificacionm);
        editDescripcionActivity = findViewById(R.id.editDescripcionActivity);
        editparentesco = findViewById(R.id.editparentesco);
        editEps = findViewById(R.id.editEps);
        editmotivovisita = findViewById(R.id.editmotivovisita);
        editpcr = findViewById(R.id.editpcr);
        editfecha_toma = findViewById(R.id.editfecha_toma);
        editAntigeno = findViewById(R.id.editAntigeno);
        btnGuardarFamiliar=findViewById(R.id.btnGuardarFamiliar);


        //noinspection Convert2Lambda

        btnGuardarFamiliar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setmButtonGuardarDatos();
            }
        });

    }


    private void setmButtonGuardarDatos() {
        Map<String, Object> map = new HashMap<>();

        String Nombres = editnameapellido.getText().toString();
        String Identificacion = editidentificacionm.getText().toString();
        String Descripcion_Actividad  = editDescripcionActivity.getText().toString();
        String Parentesco = editparentesco.getText().toString();
        String EPS = editEps.getText().toString();
        String Motivo_visita = editmotivovisita.getText().toString();
        String PCR = editpcr.getText().toString();
        String Fecha_toma = editfecha_toma.getText().toString();
        String Antigeno = editAntigeno.getText().toString();


        map.put("Nombres", Nombres);
        map.put("Identificacion", Identificacion);
        map.put("Descripcion_Actividad", Descripcion_Actividad);
        map.put("Parentesco", Parentesco);
        map.put("EPS", EPS);
        map.put("Motivo_visita", Motivo_visita);
        map.put("PCR", PCR);
        map.put("Fecha_toma", Fecha_toma);
        map.put("Antigeno", Antigeno);


        db.collection("Composicion_Familiar")
                .add(map)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error adding document", e);
                    }
                });

        Intent pasarte = new Intent(Composicio_Familiar.this, Formato_Diagnostico_pacientes_covid19.class);
        startActivity(pasarte);
    }

}




