package com.example.utmreserve;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.graphics.Bitmap;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.IOException;

public class  RegistrationActivity extends AppCompatActivity {

    private EditText userName, userPassword, userEmail, userID;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private static int PICK_IMAGE = 123;
    private ImageView userProfilePic;
    String name, email, password;
    Uri imagePath;
    private Bundle savedInstanceState;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();



        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (validate()) {
                    //Upload
                    String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();


                    firebaseAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                sendEmailVerification();
                                //sendUserData();
                                //firebaseAuth.signOut();
                                //Toast.makeText(RegistrationActivity.this, "Upload complete!", Toast.LENGTH_SHORT).show();
                                //finish();
                            } else {
                                Toast.makeText(RegistrationActivity.this, "Registration Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

        userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }



            private void setupUIViews () {
                userName = findViewById(R.id.etNameUpdate);
                userPassword = findViewById(R.id.etUserPassword);
                userEmail = findViewById(R.id.etUserEmail);
                regButton = findViewById(R.id.btnSave);
                userLogin = findViewById(R.id.tvUserLogin);
                userProfilePic = (ImageView) findViewById(R.id.ivProfile);
            }


        private Boolean validate () {
            Boolean result = false;

            name = userName.getText().toString();
            password = userPassword.getText().toString();

            email = userEmail.getText().toString();

            if (name.isEmpty() && password.isEmpty() || email.isEmpty()) {
                Toast.makeText(this, "Please enter all the details", Toast.LENGTH_SHORT).show();
            } else {
                result = true;
            }

            return result;
        }


    private void sendEmailVerification() {
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();

        if (firebaseUser != null) {
            firebaseUser.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()) {
                        sendUserData();
                        Toast.makeText(RegistrationActivity.this, "Successfully Registered, Verification mail sent!", Toast.LENGTH_SHORT).show();
                        firebaseAuth.signOut();
                        finish();
                        startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
                    } else {
                        Toast.makeText(RegistrationActivity.this, "Verification mail has'nt been sent!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }

    private void sendUserData() {
        FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
        DatabaseReference myRef = firebaseDatabase.getReference(firebaseAuth.getUid());
        UserProfile userProfile = new UserProfile(email, name);
        myRef.setValue(userProfile);
    }
}

