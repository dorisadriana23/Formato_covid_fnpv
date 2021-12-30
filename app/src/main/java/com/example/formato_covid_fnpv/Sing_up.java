package com.example.formato_covid_fnpv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

public class Sing_up extends AppCompatActivity  implements View.OnClickListener{
    private EditText mNombres, midentificacion, mtelefono;
    private EditText email;
    private EditText password;
    private Button btnregistrar,btnLogin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sing_up);
        firebaseAuth = FirebaseAuth.getInstance();
        mNombres = (EditText) findViewById(R.id.nombreCompleto);
        midentificacion = (EditText) findViewById(R.id.id);
        mtelefono = (EditText) findViewById(R.id.telefono);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.contraseña);
        btnregistrar = (Button) findViewById(R.id.btnregistrar);
        btnLogin = (Button) findViewById(R.id.logueo);
        progressDialog = new ProgressDialog(this);
        btnregistrar.setOnClickListener(this);
        btnLogin.setOnClickListener(this);
    }

    private void registrarUsuario() {
        String Nombres = mNombres.getText().toString().trim();
        String identificacion = midentificacion.getText().toString().trim();
        String telefono = mtelefono.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Nombres)) {
            Toast.makeText(this, "Ingrese nombres completos", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(identificacion)) {
            Toast.makeText(this, "Se debe ingresar un numero de id", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(this, "Se debe ingresar un numero de telefono", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Es necesaria una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando Registro,Espere Un Momento.....");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Sing_up.this, "se ha registrado el email", Toast.LENGTH_LONG).show();
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Sing_up.this, "Ese Usuario ya Existe", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Sing_up.this, "no se ha registrado el email", Toast.LENGTH_LONG).show();

                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }

    private void LoguearUsuario(){
        String Nombres = mNombres.getText().toString().trim();
        String identificacion = midentificacion.getText().toString().trim();
        String telefono = mtelefono.getText().toString().trim();
        String Email = email.getText().toString().trim();
        String Password = password.getText().toString().trim();
        if (TextUtils.isEmpty(Nombres)) {
            Toast.makeText(this, "Ingrese nombres completos", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(identificacion)) {
            Toast.makeText(this, "Se debe ingresar un numero de id", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(telefono)) {
            Toast.makeText(this, "Se debe ingresar un numero de telefono", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "Se debe ingresar un email", Toast.LENGTH_LONG).show();
            return;
        }

        if (TextUtils.isEmpty(Password)) {
            Toast.makeText(this, "Es necesaria una contraseña", Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Realizando Consulta,Espere Un Momento.....");
        progressDialog.show();
//consultar  si el usuario existe
        firebaseAuth.signInWithEmailAndPassword(Email, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Sing_up.this, "Bienvenido"+email.getText(), Toast.LENGTH_LONG).show();
                            Intent intent=new Intent(getApplication(),Composicio_Familiar.class);
                            intent.putExtra(Composicio_Familiar.user, Email);
                            startActivity(intent);
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(Sing_up.this, "Ese Usuario ya Existe", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Sing_up.this, "no se ha registrado el email", Toast.LENGTH_LONG).show();

                            }
                        }
                        progressDialog.dismiss();
                    }
                });
    }




    @Override
    public void onClick(View view) {

        switch (view.getId()){
            case R.id.btnregistrar:
                //invocando metodo9
                registrarUsuario();
                break;
            case R.id.logueo:
                LoguearUsuario();
        }

    }
}






