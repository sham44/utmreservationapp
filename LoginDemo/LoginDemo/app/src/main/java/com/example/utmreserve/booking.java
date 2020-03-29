package com.example.utmreserve;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class booking extends AppCompatActivity {

    private Button button1, button2;
    private TextView x1,x2,a2,a3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking);
        getSupportActionBar().hide();


        button1 = (Button) findViewById(R.id.button1);
        button2 = (Button) findViewById(R.id.button2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast ToastMessage = Toast.makeText(getApplicationContext(), "BOOKING CANCELLED", Toast.LENGTH_SHORT);
                View toastView = ToastMessage.getView();
                toastView.setBackgroundResource(R.layout.toast_background);
                ToastMessage.show();
                startActivity(new Intent(booking.this, SecondActivity.class));

            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast ToastMessage = Toast.makeText(getApplicationContext(), "Booking Received, please check email for confirmation", Toast.LENGTH_SHORT);
                View toastView = ToastMessage.getView();
                toastView.setBackgroundResource(R.layout.toast_background);
                ToastMessage.show();
                startActivity(new Intent(booking.this, MainActivity.class));

            }
        });

    }
}



