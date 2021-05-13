package com.example.internship;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TeacherView extends AppCompatActivity {
    DatabaseReference databaseReference;
    TextView text1,text2;
    Button button;
    String userEmail,Name,usn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_view);
        text1=findViewById(R.id.text1);
        text2=findViewById(R.id.text2);
        button=findViewById(R.id.clickme);



    }

    public void OnClickmeClicked(View view) {
      databaseReference = FirebaseDatabase.getInstance().getReference("FacultyInfo");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot issue : dataSnapshot.getChildren()) {
                        if (issue.child("Username").getValue().toString().equals("yobro@gmail.com"))
                        Name =dataSnapshot.child("Name").getValue().toString();
                        usn =dataSnapshot.child("USN").getValue().toString();
                        text1.setText("Name" + Name);
                        text2.setText("USN" +usn);
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
//        databaseReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                String Name =dataSnapshot.child("Name").getValue().toString();
//                String usn =dataSnapshot.child("USN").getValue().toString();
//                text1.setText("Name" +Name);
//                text2.setText("USN" +usn);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });

    }
}
