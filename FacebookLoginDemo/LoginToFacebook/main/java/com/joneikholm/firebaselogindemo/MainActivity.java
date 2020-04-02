package com.joneikholm.firebaselogindemo;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.facebook.login.widget.LoginButton;
import com.joneikholm.firebaselogindemo.auth.FacebookManager;
import com.joneikholm.firebaselogindemo.auth.FirebaseManager;

public class MainActivity extends AppCompatActivity {

    private EditText emailText;
    private EditText passwordText;
    private EditText secretText;
    private FirebaseManager firebaseManager;
    private Verify verify ;
    private FacebookManager facebookManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailText = findViewById(R.id.emailText);
        passwordText = findViewById(R.id.passwordText);
        secretText = findViewById(R.id.secretText);
        firebaseManager = new FirebaseManager(this);
        facebookManager = new FacebookManager();
        verify = new Verify();
        LoginButton loginButton = findViewById(R.id.facebook_login); // Facebooks's own login button
        facebookManager.handleFacebookLogin(loginButton, this); // start Facebook login process
    }

    public void showSecret(){
        secretText.setVisibility(View.VISIBLE);
    }

    public void hideSecret(){
        secretText.setVisibility(View.INVISIBLE);
    }

    public void signIn(View view){
        if(verify.isOK()) {
            firebaseManager.signIn(verify.email, verify.password, this);
        }
    }
    public void signUp(View view){
        if(verify.isOK()) {
            firebaseManager.signUp(verify.email, verify.password);
        }
    }

    public void signOut(View view){
        firebaseManager.signOut();
    }
    // keep this activity as slim as possible. Put code in separate classes


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        facebookManager.callbackManager.onActivityResult(requestCode, resultCode, data); // notify callbackManager
        super.onActivityResult(requestCode, resultCode, data);
    }

    private class Verify{
        String email;
        String password;
        boolean isOK(){
            email = emailText.getText().toString();
            password = passwordText.getText().toString();
            return email.length() > 0 && password.length()>0;
        }
    }
}
