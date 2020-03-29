package com.example.utmreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class logistics extends AppCompatActivity {

    private Button button;
    private TextView userEmail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facilities);
        getSupportActionBar().hide();

        // Spinner element
        Spinner spinner = (Spinner) findViewById(R.id.spinner1);

        // Spinner click listener


        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("PROJECTOR");
        categories.add("TABLE");
        categories.add("CHAIR");
        categories.add("PA SYSTEM");
        categories.add("WALKIE TALKIE");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
        button = (Button) findViewById(R.id.button);
        userEmail = findViewById(R.id.etName);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast ToastMessage = Toast.makeText(getApplicationContext(), "PLEASE WAIT", Toast.LENGTH_SHORT);
                View toastView = ToastMessage.getView();
                toastView.setBackgroundResource(R.layout.toast_background);
                ToastMessage.show();
                startActivity(new Intent(logistics.this, booking.class));

            }
        });
    }
}

