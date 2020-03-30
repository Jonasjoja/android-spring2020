package com.joneikholm.firebaselogindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.joneikholm.firebaselogindemo.auth.FirebaseManager;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private EditText secretText;
    private FirebaseManager firebaseManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        secretText = findViewById(R.id.secretText);
        firebaseManager = new FirebaseManager(this);
    }

    public void showSecret(){
        secretText.setVisibility(View.VISIBLE);
    }

    public void hideSecret(){
        secretText.setVisibility(View.INVISIBLE);
    }

    public void signIn(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.length() > 0 && password.length()>0) {
            firebaseManager.signIn(email, password, this);
        }
    }
    public void signUp(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();
        if(email.length() > 0 && password.length()>0) {
            firebaseManager.signUp(email, password);
        }
    }




    public void signOut(View view){
        firebaseManager.signOut();
    }
    // keep this activity as slim as possible. Put code in separate classes
}
