package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class LoginPageTeacher extends AppCompatActivity {
    EditText userName,password;
    private FirebaseAuth firebaseAuth;
    String usnValue,nameValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page_teacher);
        userName = findViewById(R.id.username);
        password=findViewById(R.id.password);
        firebaseAuth= FirebaseAuth.getInstance();

    }
    public void onLoginClicked(View view) {
        if(userName.getText().toString().equals(""))
        {
            userName.setError("Enter a valid Email Id");
            userName.requestFocus();
            return;
        }
        if(password.getText().toString().length() < 5)
        {
            password.setError("Set A Password Length greater than 5");
            password.requestFocus();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(userName.getText().toString(),password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(LoginPageTeacher.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginPageTeacher.this, TeacherView.class);
                    startActivity(intent);
                    finish();
                }else {
                    Toast.makeText(LoginPageTeacher.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                }
            }
        });






    }
}