package com.example.heart_health_app;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import network.ApiClient;
import network.ApiService;
import network.PredictionResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class MainActivity extends AppCompatActivity {

    private ImageButton heartDiagButton;
    private ImageButton heartDiagReportButton;
    private ImageButton heartButton;
    private ImageButton settingsButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        heartDiagButton = findViewById(R.id.heartDiagImgButton);
        heartDiagReportButton = findViewById(R.id.heartDiagReportImgButton);

        heartDiagButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, HeartDiagFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });

        heartDiagReportButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentManager fragmentManager = getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.fragmentContainerView, DiagReportFragment.class, null)
                        .setReorderingAllowed(true)
                        .addToBackStack("name") // Name can be null
                        .commit();

            }
        });




    }
}