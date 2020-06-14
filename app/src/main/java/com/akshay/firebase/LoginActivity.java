package com.akshay.firebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;

    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=findViewById(R.id.email);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        auth= FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Email = email.getText().toString();
                String Password = password.getText().toString();
                if(TextUtils.isEmpty(Email)||TextUtils.isEmpty(Password))
                    Toast.makeText(LoginActivity.this,"Empty Credentials",Toast.LENGTH_SHORT).show();
                else
                    loginUser(Email,Password);
            }
        });
    }

    private void loginUser(String email, String password) {
        auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(LoginActivity.this, new OnSuccessListener<AuthResult>() {
            @Override
            public void onSuccess(AuthResult authResult) {
                Toast.makeText(LoginActivity.this,"Login Succesful",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(LoginActivity.this,StartActivity.class));
                finish();
            }
        })
                .addOnCanceledListener(LoginActivity.this, new OnCanceledListener() {
                    @Override
                    public void onCanceled() {
                        Toast.makeText(LoginActivity.this,"Login UnSuccesful",Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
