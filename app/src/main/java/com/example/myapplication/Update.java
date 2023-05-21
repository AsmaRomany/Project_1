package com.example.myapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Update extends AppCompatActivity {
    private TextView textName;
    private TextView textPassword;
    private TextView textEmail;
    private TextView textDob;
 //   private TextView textLocation;


    //   private ProgressBar progressBar;
    private String Name,Password,Email,Dob;
    private ImageView imageView;
    private FirebaseAuth authFirebase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        textName = findViewById(R.id.textName);
        textPassword = findViewById(R.id.textPassword);
        textEmail = findViewById(R.id.textEmail);
        textDob = findViewById(R.id.textDob);
       // textLocation= findViewById(R.id.textLocation);
        authFirebase =FirebaseAuth.getInstance();
        FirebaseUser firebaseUser= authFirebase.getCurrentUser();
        showProfile( firebaseUser);
    }

    private void showProfile(FirebaseUser firebaseUser) {
        String userId = firebaseUser.getUid();
        DatabaseReference reference= FirebaseDatabase.getInstance().getReference(" Update Profile");
        reference.child(userId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                UserDetails details= snapshot.getValue((UserDetails.class));
                if (details != null){
                //    private String Name,Password,Email,Dob,Location;
                    Name=firebaseUser.getDisplayName();
                    Email=firebaseUser.getEmail();
                    Dob=details.dob;
                   // Location=details.location;
                }
                else {
                    Toast.makeText(Update.this, "Somthing Wrong...!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Update.this, "Somthing Wrong...!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}