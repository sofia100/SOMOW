package com.example.android.somow;

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

public class login extends AppCompatActivity {
    Button regis;
    private FirebaseAuth mAuth;
    EditText emailText,passwordText;
    String email, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        regis = findViewById(R.id.button);
        regis.setText("LOGIN");
        mAuth = FirebaseAuth.getInstance();


        regis.setOnClickListener(new View.OnClickListener() {
                                     @Override
                                     public void onClick(View view) {

                                         emailText = findViewById(R.id.editText);
                                         email = emailText.getText().toString().trim();

                                         passwordText = findViewById(R.id.editText2);
                                         password = passwordText.getText().toString().trim();

                                         (mAuth.signInWithEmailAndPassword(email,password))
                                                 .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                                     @Override
                                                     public void onComplete(@NonNull Task<AuthResult> task) {
                                                         if (task.isSuccessful()) {
                                                             Toast.makeText(getApplicationContext(), "SUCCESSFULLY LOGGED IN", Toast.LENGTH_SHORT).show();

                                                             Intent i= new Intent (getApplicationContext(),DASHBOARD.class);
                                                             startActivity(i);
                                                         }
                                                         else
                                                             Toast.makeText(getApplicationContext(), "SUCCESSFULLY NOT LOGGED IN", Toast.LENGTH_SHORT).show();

                                                     }
                                                 });
                                     }
                                 }
        );
    }
}
