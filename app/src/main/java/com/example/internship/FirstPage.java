package com.example.internship;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class FirstPage extends AppCompatActivity {

    Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
    }

    public void onAdminClicked(View view) {
        Intent intent=new Intent(FirstPage.this,LoginPageAdmin.class);
        startActivity(intent);
    finish();
    }

    public void onTeacherClicked(View view) {
        Intent intent=new Intent(FirstPage.this, LoginPageTeacher.class);
        startActivity(intent);
    }
}
