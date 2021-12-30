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

public class Formato_Diagnostico_pacientes_covid19 extends AppCompatActivity {
    public static String user="names";
    TextView txtuser;
    EditText editfecha, edithora, editnombre_visitante, editcargo, editname_apellido,editdescrp,
            editTipoidentificacion,editnumber_document,editlugar_residencia,editocupation_paciente,edittele;
    Button btnGuardarFormato19;
    public static final String TAG = "DocSnippets";
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formato_diagnostico_pacientes_covid19);
        txtuser=(TextView) findViewById(R.id.form);

        FirebaseApp.initializeApp(this);
        db = FirebaseFirestore.getInstance();

        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().setPersistenceEnabled(true).build();

        db.setFirestoreSettings(settings);
        editfecha = (EditText) findViewById(R.id.editfecha);
        edithora =(EditText) findViewById(R.id.edithora);
        editnombre_visitante =(EditText) findViewById(R.id.editnombre_visitante);
        editcargo = (EditText)findViewById(R.id.editcargo);
        editname_apellido =(EditText) findViewById(R.id.editname_apellido);
        editdescrp = (EditText)findViewById(R.id.editdescrp);
        editTipoidentificacion =(EditText) findViewById(R.id.editTipoidentificacion);
        editnumber_document =(EditText) findViewById(R.id.editnumber_document);
        editlugar_residencia =(EditText) findViewById(R.id.editlugar_residencia);
        editocupation_paciente =(EditText) findViewById(R.id.editocupation_paciente);
        edittele =(EditText) findViewById(R.id.edittele);

        btnGuardarFormato19=findViewById(R.id.btnGuardarFormato19);
        //noinspection Convert2Lambda
        btnGuardarFormato19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                setmButtonGuardarDatos();
            }
        });

    }
    private void setmButtonGuardarDatos() {
        Map<String, Object> map = new HashMap<>();
        String Fecha = editfecha.getText().toString();
        String Hora = edithora.getText().toString();
        String Nombre_visitante  = editnombre_visitante.getText().toString();
        String Cargo = editcargo.getText().toString();
        String Nombres_Apellidos = editname_apellido.getText().toString();
        String Descripcion_Actividad = editdescrp.getText().toString();
        String TipoId= editTipoidentificacion.getText().toString();
        String Numero_id= editnumber_document.getText().toString();
        String Lugar_Residencia= editlugar_residencia.getText().toString();
        String Ocupacion_paciente= editocupation_paciente.getText().toString();
        String Telefono= edittele.getText().toString();

        map.put("Fecha", Fecha);
        map.put("Hora", Hora);
        map.put("Nombre_visitante", Nombre_visitante);
        map.put("Cargo", Cargo);
        map.put("Nombres_Apellidos", Nombres_Apellidos);
        map.put("Descripcion_Actividad", Descripcion_Actividad);
        map.put("TipoId", TipoId);
        map.put("Numero_id", Numero_id);
        map.put("Lugar_Residencia", Lugar_Residencia);
        map.put("Ocupacion_paciente", Ocupacion_paciente);
        map.put("Telefono", Telefono);


        //db.collection("formulario_brigadas").document("2").set(map);

        db.collection("Formato_covid")
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
        Intent pasarte = new Intent(Formato_Diagnostico_pacientes_covid19.this, Signos_Sintomas_actuales.class);
        startActivity(pasarte);

    }

}
