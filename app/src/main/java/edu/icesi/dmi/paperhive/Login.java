package edu.icesi.dmi.paperhive;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    EditText email_et, password_et;
    Button login_btn, go_to_sign_up_screen_btn;

    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        email_et = findViewById(R.id.login_email_et);
        password_et = findViewById(R.id.login_password_et);
        login_btn = findViewById(R.id.login_btn);
        go_to_sign_up_screen_btn = findViewById(R.id.go_to_sign_up_screen_btn);

        auth = FirebaseAuth.getInstance();

        //En caso de que haya una sesión iniciada, esta se cierra.
        if(auth.getCurrentUser() != null) {
            auth.signOut();
        }

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });

        go_to_sign_up_screen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_sign_up_screen();
            }
        });
    }

    public void login() {
        //Se realizan todas las validaciones antes de iniciar la sesión.
        if(email_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_LONG).show();
            return;
        }

        if(password_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        //Una vez hechas todas las validaciones se inicia la sesión.
        auth.signInWithEmailAndPassword(email_et.getText().toString(), password_et.getText().toString() )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() ) {
                            go_to_home_screen();
                        }

                        else {
                            Toast.makeText(Login.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void go_to_sign_up_screen() {
        Intent intent = new Intent(this, SignUp.class);
        startActivity(intent);
    }

    public void go_to_home_screen() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
