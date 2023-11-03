package com.example.pushnotificationcustomer;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.internal.FirebaseInstanceIdInternal;
import com.google.firebase.messaging.FirebaseMessaging;

public class Registration extends AppCompatActivity {
Button registration;
EditText email,password;
FirebaseAuth mAuth;
FirebaseDatabase firebaseDatabase;
DatabaseReference reference;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        reference = firebaseDatabase.getReference("Demo");
        setContentView(R.layout.activity_registration);
        email =  findViewById(R.id.rsEmail);
        password = findViewById(R.id.rsPassword);
        registration =  findViewById(R.id.rsRegistration);

        registration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcmToken();
                String Email = email.getText().toString().trim();
                String Password = password.getText().toString().trim();
//            mAuth.createUserWithEmailAndPassword(Email,Password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()){
//
//                        Toast.makeText(Registration.this, "Done", Toast.LENGTH_SHORT).show();
//
//                    }
//                    else{
//                        Toast.makeText(Registration.this, "Failed", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
            }
        });

    }

    private void fcmToken()
    {
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        String token = task.getResult();
                        saveData(token);

                    } else {

                    }
                });
    }

    public void saveData(String Token){
        Model model = new Model(Token);
        reference.child(password.getText().toString()).setValue(model);
    }
}