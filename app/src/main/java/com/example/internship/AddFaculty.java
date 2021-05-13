package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddFaculty extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    EditText name,username,password,course;
    String nameValue,usernameValue,passwordValue,courseValue;
    DatabaseReference databaseReference;
    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_faculty);
        firebaseAuth= FirebaseAuth.getInstance();
        name=findViewById(R.id.facultyname);
        username=findViewById(R.id.usernameforfaculty);
        password=findViewById(R.id.passwordforfaculty);
        course=findViewById(R.id.courseforfaculty);
        databaseReference = FirebaseDatabase.getInstance().getReference();

    }

    public void onSubmitClicked(View view) {
        nameValue = name.getText().toString();
        usernameValue = username.getText().toString();
        passwordValue=password.getText().toString();
        courseValue=course.getText().toString();


        if (usernameValue.equals("")) {
           username.setError("Enter a valid Email Id");
           username.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(usernameValue).matches()) {
            username.setError("Enter a valid Email Id");
            username.requestFocus();
            return;
        }

        if (password.getText().toString().length() < 6) {
            password.setError("Set A Password Length greater than 6");
            password.requestFocus();
            return;
        }

        firebaseAuth.createUserWithEmailAndPassword(usernameValue,passwordValue).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(AddFaculty.this, "New USER Created", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddFaculty.this, "Already Signed UP try logging In!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if (name.getText().toString().equals("")) {
            name.setError("Enter your Name");
            name.requestFocus();
            return;
        }
        if (nameValue.contains(".") || nameValue.contains("/")) {
            name.setError("Enter your FirstName Space LastName. Do not use '.' or '/'");
            name.requestFocus();
            return;
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("FacultyInfo").child(nameValue);
        databaseReference.child("Name").setValue(nameValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    databaseReference.child("Username").setValue(usernameValue);
                    databaseReference.child("Password").setValue(passwordValue);
                    databaseReference.child("Course").setValue(courseValue);

                    Toast.makeText(AddFaculty.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddFaculty.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });


        intent=new Intent(AddFaculty.this,AdminView.class);
        startActivity(intent);
        finish();
    }

}