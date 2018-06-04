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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

//Clase usada para el inicio de sesión.
public class SignUp extends AppCompatActivity {

    EditText name_et, id_et, email_et, password_et, confirm_password_et;
    Button sign_up_btn, go_to_login_screen_btn;

    FirebaseDatabase database;
    DatabaseReference reference;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        name_et = findViewById(R.id.sign_up_name_et);
        id_et = findViewById(R.id.sign_up_id_et);
        email_et = findViewById(R.id.sign_up_email_et);
        password_et = findViewById(R.id.sign_up_password_et);
        confirm_password_et = findViewById(R.id.sign_up_confirm_password_et);
        sign_up_btn = findViewById(R.id.sign_up_btn);
        go_to_login_screen_btn = findViewById(R.id.go_to_login_screen_btn);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("Usuarios");
        auth = FirebaseAuth.getInstance();

        //En caso de que haya una sesión iniciada, esta se cierra.
        if(auth.getCurrentUser() != null) {
            auth.signOut();
        }

        sign_up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });

        go_to_login_screen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                go_to_login_screen();
            }
        });
    }

    public void signUp() {
        //Se realizan todas las validaciones antes de crear una nueva cuenta.
        if(name_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresar tu nombre", Toast.LENGTH_LONG).show();
            return;
        }

        if(id_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu documento de identidad", Toast.LENGTH_LONG).show();
            return;
        }

        if(email_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Ingresa tu correo electrónico", Toast.LENGTH_LONG).show();
            return;
        }

        if(password_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Crea una contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if(password_et.getText().toString().length() <= 5 ) {
            Toast.makeText(this, "Tu contraseña debe tener más de 6 caracteres", Toast.LENGTH_LONG).show();
            return;
        }

        if(confirm_password_et.getText().toString().isEmpty() ) {
            Toast.makeText(this, "Confirma tu contraseña", Toast.LENGTH_LONG).show();
            return;
        }

        if(!password_et.getText().toString().equals(confirm_password_et.getText().toString() ) ) {
            Toast.makeText(this, "Las contraseñas no coinciden", Toast.LENGTH_LONG).show();
            return;
        }

        //La cuenta es creada después de validar que cumpla con todos los requisitos.
        auth.createUserWithEmailAndPassword(email_et.getText().toString(), password_et.getText().toString() )
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful() ) {
                            //Se guarda el UID del usuario y se le es asignado.
                            String uid = auth.getCurrentUser().getUid();

                            User user = new User(100, name_et.getText().toString(), id_et.getText().toString(), email_et.getText().toString(), uid);
                            reference.child(uid).setValue(user);

                            go_to_home_screen();
                        }

                        else {
                            Toast.makeText(SignUp.this, "El registro no pudo completarse", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void go_to_login_screen() {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }

    public void go_to_home_screen() {
        Intent intent = new Intent(this, Home.class);
        startActivity(intent);
    }
}
