package com.example.heart_health_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class HeartDiagResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_heart_diag_result);

        FloatingActionButton formResultBackFloatingActionButton = findViewById(R.id.formResultBackFloatingActionButton);
        formResultBackFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // This will navigate back to the previous activity or fragment
            }
        });

        String predictionResult = getIntent().getStringExtra("prediction_result");
        TextView resultTextView = findViewById(R.id.resultTextView);
        ImageView imageView = findViewById(R.id.ResultImage);

        if(predictionResult.equals("0")){
            imageView.setImageResource(R.drawable.baseline_health_and_safety_green_24);
            resultTextView.setText("Your Heart is healthy");
        } else if (predictionResult.equals("1")){
            imageView.setImageResource(R.drawable.baseline_health_and_safety_24);
            resultTextView.setText("You are in Risk of Heart Attack");
        }
        else {
            imageView.setImageResource(R.drawable.baseline_error_24);
            resultTextView.setText("Error Occurred: "+predictionResult);
        }
    }
}