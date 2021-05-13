package com.example.internship;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AdminView extends AppCompatActivity {
    Calendar calendar;
    String currentdate,currenttime;
    TextView date,time;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_view);

        calendar=Calendar.getInstance();
        currentdate= DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        date= findViewById(R.id.dates);
        date.setText(currentdate);
        SimpleDateFormat format=new SimpleDateFormat("HH:mm:ss");
        currenttime = format.format(calendar.getTime());
        time=findViewById(R.id.times);
        time.setText(currenttime);




    }
    public void onAddFacultyClicked(View view) {
        Intent intent = new Intent(AdminView.this, AddFaculty.class);
        startActivity(intent);
    }



    public void onViewAttendanceClicked(View view) {
    }

    public void onStatisticClicked(View view) {
    }


    public void onAddStudentClicked(View view) {
        Intent intent = new Intent(AdminView.this,AddStudents.class);
        startActivity(intent);
    }
}
