package com.example.formato_covid_fnpv;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseUser;

public class Login_users extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "";
    EditText memail, mcontrasena;
    private Button btniniciarsesion;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_users);
        firebaseAuth = firebaseAuth.getInstance();


        memail = (EditText) findViewById(R.id.emailedit);
        mcontrasena = (EditText) findViewById(R.id.password);
        btniniciarsesion=(Button)findViewById(R.id.btniniciarsesion);
        btniniciarsesion.setOnClickListener(this);
    }

    public void pasarregistrosusuarios(View view) {
        Intent pasar=new Intent(Login_users.this, Sing_up.class);
        startActivity(pasar);
    }

    public void pasarregistrosusuariosu(View view) {
        Intent pasar=new Intent(Login_users.this, Composicio_Familiar.class);
        startActivity(pasar);
    }

    private void inicialize() {
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseAuth.AuthStateListener authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
                if (firebaseUser != null) {
                    Log.w(TAG, "onAuthStateChanged - Logueado");

                } else {
                    Log.w(TAG, "onAuthStateChanged - Cerro sesion");
                }
            }
        };
    }

    private void loguearUsuario() {


        String Email = memail.getText().toString().trim();
        String contrasena = mcontrasena.getText().toString().trim();

        if (TextUtils.isEmpty(Email)) {
            Toast.makeText(this, "se debe ingresar un email", Toast.LENGTH_SHORT).show();
            return;

        }
        if (TextUtils.isEmpty(contrasena)) {
            Toast.makeText(this, "se debe ingresar una contraseña", Toast.LENGTH_SHORT).show();
            return;
        }
        //loguear un nuevo usuario
        //noinspection Convert2Lambda
        firebaseAuth.signInWithEmailAndPassword(Email, contrasena)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login_users.this, "Bienvenido", Toast.LENGTH_SHORT).show();
                            Intent intencion = new Intent(getApplication(), Composicio_Familiar.class);
                            intencion.putExtra(Composicio_Familiar.user, Email);
                            startActivity(intencion);

                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {//se presenta una colision

                                Toast.makeText(Login_users.this, "Ese usuario ya existe", Toast.LENGTH_SHORT).show();

                            } else {
                                Toast.makeText(Login_users.this, "No se pudo registrar el usuari", Toast.LENGTH_LONG).show();

                            }
                        }

                    }

                });
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.btnregistrar:
                setBtnregistrar();
                break;
            case R.id.btniniciarsesion:
                loguearUsuario();
                break;

        }
    }

    private void setBtnregistrar() {
    }


}

