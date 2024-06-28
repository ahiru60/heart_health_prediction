package com.example.heart_health_app;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import network.ApiClient;
import network.ApiService;
import network.PredictionResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HeartDiagFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HeartDiagFragment extends Fragment {

    private EditText cholesterolEditText;
    private EditText systolicEditText;
    private EditText exerciseHoursPerWeekEditText;
    private EditText triglyceridesEditText;
    private EditText incomeEditText;
    private EditText ageEditText;
    private EditText bmiEditText;
    private EditText heartRateEditText;
    private EditText sedentaryHoursPerDayEditText;
    private EditText diastolicEditText;
    private EditText sleepHoursPerDayEditText;

    private Spinner alcoholConsumptionSpinner;
    private Spinner diabetesSpinner;
    private Spinner dietSpinner;
    private Spinner continentSpinner;
    private Spinner sexSpinner;
    private Spinner medicationUseSpinner;
    private Spinner previousHeartProblemsSpinner;
    private Spinner familyHistorySpinner;
    private Spinner smokingSpinner;
    private Spinner hemisphereSpinner;
    private Spinner obesitySpinner;

    private Spinner stressLevelSpinner;
    private Spinner physicalActivityDaysPerWeekSpinner;

    private Button predictButton;

    private TextView resultTextView;

    private ProgressBar HeartDiagprogressBar;

    private DatabaseHelper databaseHelper;


    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;



    public HeartDiagFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HeartDiagFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HeartDiagFragment newInstance(String param1, String param2) {
        HeartDiagFragment fragment = new HeartDiagFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_heart_diag, container, false);
        // Inflate the layout for this fragment

        databaseHelper = new DatabaseHelper(getContext());

        // Initialize editText fields
        cholesterolEditText = view.findViewById(R.id.cholesterolEditText);
        systolicEditText = view.findViewById(R.id.systolicEditText);
        exerciseHoursPerWeekEditText = view.findViewById(R.id.exerciseHoursPerWeekEditText);
        triglyceridesEditText = view.findViewById(R.id.triglyceridesEditText);
        incomeEditText = view.findViewById(R.id.incomeEditText);
        ageEditText = view.findViewById(R.id.ageEditText);
        // bmiEditText = view.findViewById(R.id.bmiEditText);
        heartRateEditText = view.findViewById(R.id.heartRateEditText);
        physicalActivityDaysPerWeekSpinner = view.findViewById(R.id.physicalActivityDaysPerWeekSpinner);
        sedentaryHoursPerDayEditText = view.findViewById(R.id.sedentaryHoursPerDayEditText);
        diastolicEditText = view.findViewById(R.id.diastolicEditText);
        sleepHoursPerDayEditText = view.findViewById(R.id.sleepHoursPerDayEditText);

        // Initialize Spinner fields
        alcoholConsumptionSpinner = view.findViewById(R.id.alcoholConsumptionSpinner);
        diabetesSpinner = view.findViewById(R.id.diabetesSpinner);
        dietSpinner = view.findViewById(R.id.dietSpinner);
        continentSpinner = view.findViewById(R.id.continentSpinner);
        sexSpinner = view.findViewById(R.id.sexSpinner);
        medicationUseSpinner = view.findViewById(R.id.medicationUseSpinner);
        previousHeartProblemsSpinner = view.findViewById(R.id.previousHeartProblemsSpinner);
        familyHistorySpinner = view.findViewById(R.id.familyHistorySpinner);
        smokingSpinner = view.findViewById(R.id.smokingSpinner);
        hemisphereSpinner = view.findViewById(R.id.hemisphereSpinner);
        obesitySpinner = view.findViewById(R.id.obesitySpinner);

        stressLevelSpinner = view.findViewById(R.id.stressLevelEditText);

        // Initialize button
        predictButton = view.findViewById(R.id.predictButton);
        resultTextView = view.findViewById(R.id.resultTextView);
        HeartDiagprogressBar = view.findViewById(R.id.heartDiagprogressBar);


        predictButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                makePrediction();
            }
        });
        // Add TextWatchers and OnItemSelectedListeners
        addTextWatchers();
        addSpinnerListeners();

        // Initial check to enable/disable the button
        checkFieldsForEmptyValues();

        return view;
    }

    private void makePrediction() {
        float cholesterol = Float.parseFloat(cholesterolEditText.getText().toString());
        float systolic = Float.parseFloat(systolicEditText.getText().toString());
        float exerciseHoursPerWeek = Float.parseFloat(exerciseHoursPerWeekEditText.getText().toString());
        float triglycerides = Float.parseFloat(triglyceridesEditText.getText().toString());
        float income = Float.parseFloat(incomeEditText.getText().toString());
        float age = Float.parseFloat(ageEditText.getText().toString());
        // float bmi = Float.parseFloat(bmiEditText.getText().toString());
        float heartRate = Float.parseFloat(heartRateEditText.getText().toString());
        float sedentaryHoursPerDay = Float.parseFloat(sedentaryHoursPerDayEditText.getText().toString());
        float diastolic = Float.parseFloat(diastolicEditText.getText().toString());
        float sleepHoursPerDay = Float.parseFloat(sleepHoursPerDayEditText.getText().toString());

        int alcoholConsumption = alcoholConsumptionSpinner.getSelectedItemPosition();
        int diabetes = diabetesSpinner.getSelectedItemPosition();
        int diet = dietSpinner.getSelectedItemPosition();
        int continent = continentSpinner.getSelectedItemPosition();
        int sex = sexSpinner.getSelectedItemPosition();
        int medicationUse = medicationUseSpinner.getSelectedItemPosition();
        int previousHeartProblems = previousHeartProblemsSpinner.getSelectedItemPosition();
        int familyHistory = familyHistorySpinner.getSelectedItemPosition();
        int smoking = smokingSpinner.getSelectedItemPosition();
        int hemisphere = hemisphereSpinner.getSelectedItemPosition();
        int obesity = obesitySpinner.getSelectedItemPosition();
        int stressLevel = Integer.parseInt(stressLevelSpinner.getSelectedItem().toString());
        int physicalActivityDaysPerWeek = Integer.parseInt(physicalActivityDaysPerWeekSpinner.getSelectedItem().toString());

        // Convert the data to strings
        String cholesterolStr = String.valueOf(cholesterol);
        String systolicStr = String.valueOf(systolic);
        String exerciseHoursPerWeekStr = String.valueOf(exerciseHoursPerWeek);
        String triglyceridesStr = String.valueOf(triglycerides);
        String incomeStr = String.valueOf(income);
        String ageStr = String.valueOf(age);
        String heartRateStr = String.valueOf(heartRate);
        String sedentaryHoursPerDayStr = String.valueOf(sedentaryHoursPerDay);
        String diastolicStr = String.valueOf(diastolic);
        String sleepHoursPerDayStr = String.valueOf(sleepHoursPerDay);
        String alcoholConsumptionStr = String.valueOf(alcoholConsumption);
        String diabetesStr = String.valueOf(diabetes);
        String dietStr = String.valueOf(diet);
        String medicationUseStr = String.valueOf(medicationUse);
        String previousHeartProblemsStr = String.valueOf(previousHeartProblems);
        String familyHistoryStr = String.valueOf(familyHistory);
        String smokingStr = String.valueOf(smoking);
        String obesityStr = String.valueOf(obesity);
        String stressLevelStr = String.valueOf(stressLevel);
        String physicalActivityDaysPerWeekStr = String.valueOf(physicalActivityDaysPerWeek);

        String sexStr = sexSpinner.getSelectedItem().toString();
        String continentStr = continentSpinner.getSelectedItem().toString();
        String hemisphereStr = hemisphereSpinner.getSelectedItem().toString();

        ApiService apiService = ApiClient.getRetrofitInstance().create(ApiService.class);
        Call<PredictionResponse> call = apiService.getPrediction(
                cholesterol, systolic, diabetes, exerciseHoursPerWeek, triglycerides, income, age, diet, continent,
                sex, medicationUse, previousHeartProblems, /* bmi, */ familyHistory, smoking, stressLevel, heartRate,
                physicalActivityDaysPerWeek, sedentaryHoursPerDay, diastolic, hemisphere, obesity, alcoholConsumption,
                sleepHoursPerDay);

        predictButton.setVisibility(View.GONE);
        HeartDiagprogressBar.setVisibility(View.VISIBLE);

        call.enqueue(new Callback<PredictionResponse>() {

            @Override
            public void onResponse(Call<PredictionResponse> call, Response<PredictionResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    String prediction = response.body().getPlacement();
                    resultTextView.setText("Placement: " + prediction);
                    startPredictionResultActivity(response.body().getPlacement());
                    // Save data and prediction to the database
                    boolean isInserted = databaseHelper.insertData(
                            cholesterolStr, systolicStr, exerciseHoursPerWeekStr, triglyceridesStr, incomeStr, ageStr, heartRateStr,
                            sedentaryHoursPerDayStr, diastolicStr, sleepHoursPerDayStr, alcoholConsumptionStr, diabetesStr,
                            dietStr, continentStr, sexStr, medicationUseStr, previousHeartProblemsStr, familyHistoryStr,
                            smokingStr, hemisphereStr, obesityStr, stressLevelStr, physicalActivityDaysPerWeekStr, prediction);

                    if (isInserted) {
                        Toast.makeText(getContext(), "Data Saved", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(getContext(), "Data Not Saved", Toast.LENGTH_SHORT).show();
                    }
                    predictButton.setVisibility(View.VISIBLE);
                    HeartDiagprogressBar.setVisibility(View.GONE);
                } else {
                    resultTextView.setText("Failed to get prediction");
                    Toast.makeText(getContext(), "Failed to get prediction", Toast.LENGTH_SHORT).show();
                    predictButton.setVisibility(View.VISIBLE);
                    HeartDiagprogressBar.setVisibility(View.GONE);
                }
            }

            @Override
            public void onFailure(Call<PredictionResponse> call, Throwable t) {
                resultTextView.setText("Error: " + t.getMessage());
                Toast.makeText(getContext(), "Error: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                predictButton.setVisibility(View.VISIBLE);
                HeartDiagprogressBar.setVisibility(View.GONE);
            }
        });
    }

    private void startPredictionResultActivity(String predictionResult) {
        Intent intent = new Intent(getActivity(), HeartDiagResultActivity.class);
        intent.putExtra("prediction_result", predictionResult);
        startActivity(intent);
    }

    private void addTextWatchers() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        };

        cholesterolEditText.addTextChangedListener(textWatcher);
        systolicEditText.addTextChangedListener(textWatcher);
        exerciseHoursPerWeekEditText.addTextChangedListener(textWatcher);
        triglyceridesEditText.addTextChangedListener(textWatcher);
        incomeEditText.addTextChangedListener(textWatcher);
        ageEditText.addTextChangedListener(textWatcher);
        // bmiEditText.addTextChangedListener(textWatcher);
        heartRateEditText.addTextChangedListener(textWatcher);
        sedentaryHoursPerDayEditText.addTextChangedListener(textWatcher);
        diastolicEditText.addTextChangedListener(textWatcher);
        sleepHoursPerDayEditText.addTextChangedListener(textWatcher);
    }

    private void addSpinnerListeners() {
        AdapterView.OnItemSelectedListener spinnerListener = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                checkFieldsForEmptyValues();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        };

        alcoholConsumptionSpinner.setOnItemSelectedListener(spinnerListener);
        diabetesSpinner.setOnItemSelectedListener(spinnerListener);
        dietSpinner.setOnItemSelectedListener(spinnerListener);
        continentSpinner.setOnItemSelectedListener(spinnerListener);
        sexSpinner.setOnItemSelectedListener(spinnerListener);
        medicationUseSpinner.setOnItemSelectedListener(spinnerListener);
        previousHeartProblemsSpinner.setOnItemSelectedListener(spinnerListener);
        familyHistorySpinner.setOnItemSelectedListener(spinnerListener);
        smokingSpinner.setOnItemSelectedListener(spinnerListener);
        hemisphereSpinner.setOnItemSelectedListener(spinnerListener);
        obesitySpinner.setOnItemSelectedListener(spinnerListener);
        stressLevelSpinner.setOnItemSelectedListener(spinnerListener);
        physicalActivityDaysPerWeekSpinner.setOnItemSelectedListener(spinnerListener);
    }

    private void checkFieldsForEmptyValues() {
        boolean allFieldsFilled = !cholesterolEditText.getText().toString().isEmpty()
                && !systolicEditText.getText().toString().isEmpty()
                && !exerciseHoursPerWeekEditText.getText().toString().isEmpty()
                && !triglyceridesEditText.getText().toString().isEmpty()
                && !incomeEditText.getText().toString().isEmpty()
                && !ageEditText.getText().toString().isEmpty()
                // && !bmiEditText.getText().toString().isEmpty()
                && !heartRateEditText.getText().toString().isEmpty()
                && !sedentaryHoursPerDayEditText.getText().toString().isEmpty()
                && !diastolicEditText.getText().toString().isEmpty()
                && !sleepHoursPerDayEditText.getText().toString().isEmpty()
                ;

        predictButton.setEnabled(allFieldsFilled);
    }


}
