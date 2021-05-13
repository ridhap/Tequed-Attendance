package com.example.internship;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.ListViewAutoScrollHelper;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

import in.galaxyofandroid.spinerdialog.OnSpinerItemClick;
import in.galaxyofandroid.spinerdialog.SpinnerDialog;

public class AddStudents extends AppCompatActivity {

    ArrayList<String> items=new ArrayList<>();
    SpinnerDialog spinnerDialog;
    EditText courseNameList;
    private FirebaseAuth firebaseAuth;
    EditText name,usn,course;
    String nameValue,usnValue,courseValue;
    DatabaseReference databaseReference;
    Intent intent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_students);

        firebaseAuth= FirebaseAuth.getInstance();
        //-------------------------------------------------------------------------------------------
        name=findViewById(R.id.studentname);
        usn=findViewById(R.id.usnforstudent);
        course=findViewById(R.id.courseForStudent);

        databaseReference = FirebaseDatabase.getInstance().getReference();

        //------------------------------------------------------------------------
        courseNameList=findViewById(R.id.courseForStudent);
        items.add("Internet Of Things");
        items.add("Artificial Intelligence");
        items.add("Full Stack Web Development");
        items.add("Electric Vehicle Technology");
        items.add("IC Engine design and Management");
        items.add("Android Application Development");
        items.add("Cyber Security and Ethical Hacking");
        items.add("Virtual and Augmented Reality");


        spinnerDialog=new SpinnerDialog(AddStudents.this,items,"Select or Search City","Close Button Text");// With No Animation
        spinnerDialog=new SpinnerDialog(AddStudents.this,items,"Select or Search City",R.style.DialogAnimations_SmileWindow,"Close Button Text");// With 	Animation

        spinnerDialog.setCancellable(true); // for cancellable
        spinnerDialog.setShowKeyboard(false);// for open keyboard by default


        spinnerDialog.bindOnSpinerListener(new OnSpinerItemClick() {
            @Override
            public void onClick(String item, int position) {
                Toast.makeText(AddStudents.this, item, Toast.LENGTH_SHORT).show();
               courseNameList.setText(item );
            }
        });
//---------------------------------------------------------------------------------------------------

    }
    public void onShowClicked(View view) {
        spinnerDialog.showSpinerDialog();
    }

    public void onSubmitClicked(View view) {

        nameValue = name.getText().toString();
        usnValue = usn.getText().toString();
        courseValue=course.getText().toString();

        if (name.getText().toString().equals("")) {
            name.setError("Enter your Name");
            name.requestFocus();
            return;
        }
        if (usn.getText().toString().equals("")) {
            usn.setError("Enter your Usn");
           usn.requestFocus();
            return;
        }
        if (course.getText().toString().equals("")) {
           course.setError("Enter your Name");
           course.requestFocus();
            return;
        }
        if (nameValue.contains(".") || nameValue.contains("/")) {
            name.setError("Enter your FirstName Space LastName. Do not use '.' or '/'");
            name.requestFocus();
            return;
        }
        databaseReference = FirebaseDatabase.getInstance().getReference("StudentInfo").child(nameValue);
        databaseReference.child("Name").setValue(nameValue).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    databaseReference.child("USN").setValue(usnValue);
                    databaseReference.child("Course").setValue(courseValue);

                    Toast.makeText(AddStudents.this, "Data Added Succesfully", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AddStudents.this, "Try Again Later", Toast.LENGTH_SHORT).show();
                }
            }
        });


        intent=new Intent(AddStudents.this,AdminView.class);
        startActivity(intent);
        finish();
    }


}
