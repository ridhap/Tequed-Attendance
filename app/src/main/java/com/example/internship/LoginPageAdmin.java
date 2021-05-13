package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class LoginPageAdmin extends AppCompatActivity {
    EditText email,password;
    private FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_admin);

        email = findViewById(R.id.email);
        password=findViewById(R.id.password);
        firebaseAuth= FirebaseAuth.getInstance();
    }
    public void onLoginClicked(View view) {
        if (email.getText().toString().equals("")) {
            email.setError("Enter a valid Email Id");
            email.requestFocus();
            return;
        }
        if (password.getText().toString().equals("")) {
            email.setError("Enter Valid Admin Password");
            email.requestFocus();
            return;
        }
        if (password.getText().toString().matches("ad123")) {

            Toast.makeText(LoginPageAdmin.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginPageAdmin.this, AdminView.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginPageAdmin.this, "create new account", Toast.LENGTH_SHORT).show();
        }
    }
}
