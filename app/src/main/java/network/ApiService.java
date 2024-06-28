package network;

import retrofit2.Call;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Field;

public interface ApiService {
    @FormUrlEncoded
    @POST("/predict")
    Call<PredictionResponse> getPrediction(
            @Field("Cholesterol") float cholesterol,
            @Field("Systolic") float systolic,
            @Field("Diabetes") float diabetes,
            @Field("Exercise Hours per Week") float exerciseHoursPerWeek,
            @Field("Triglycerides") float triglycerides,
            @Field("Income") float income,
            @Field("Age") float age,
            @Field("Diet") float diet,
            @Field("Continent") float continent,
            @Field("Sex") float sex,
            @Field("Medication Use") float medicationUse,
            @Field("Previous Heart Problems") float previousHeartProblems,
            //@Field("BMI") float bmi,
            @Field("Family History") float familyHistory,
            @Field("Smoking") float smoking,
            @Field("Stress Level") float stressLevel,
            @Field("Heart Rate") float heartRate,
            @Field("Physical Activity Days per Week") float physicalActivityDaysPerWeek,
            @Field("Sedentary Hours per Day") float sedentaryHoursPerDay,
            @Field("Diastolic") float diastolic,
            @Field("Hemisphere") float hemisphere,
            @Field("Obesity") float obesity,
            @Field("Alcohol Consumption") float alcoholConsumption,
            @Field("Sleep Hours per Day") float sleepHoursPerDay
    );
}
