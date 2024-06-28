package com.example.heart_health_app;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "healthData.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "predictions";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_DATA = "data";
    private static final String COLUMN_CHOLESTEROL = "cholesterol";
    private static final String COLUMN_SYSTOLIC = "systolic";
    private static final String COLUMN_EXERCISE_HOURS_PER_WEEK = "exerciseHoursPerWeek";
    private static final String COLUMN_TRIGLYCERIDES = "triglycerides";
    private static final String COLUMN_INCOME = "income";
    private static final String COLUMN_AGE = "age";
    private static final String COLUMN_HEART_RATE = "heartRate";
    private static final String COLUMN_SEDENTARY_HOURS_PER_DAY = "sedentaryHoursPerDay";
    private static final String COLUMN_DIASTOLIC = "diastolic";
    private static final String COLUMN_SLEEP_HOURS_PER_DAY = "sleepHoursPerDay";
    private static final String COLUMN_ALCOHOL_CONSUMPTION = "alcoholConsumption";
    private static final String COLUMN_DIABETES = "diabetes";
    private static final String COLUMN_DIET = "diet";
    private static final String COLUMN_CONTINENT = "continent";
    private static final String COLUMN_SEX = "sex";
    private static final String COLUMN_MEDICATION_USE = "medicationUse";
    private static final String COLUMN_PREVIOUS_HEART_PROBLEMS = "previousHeartProblems";
    private static final String COLUMN_FAMILY_HISTORY = "familyHistory";
    private static final String COLUMN_SMOKING = "smoking";
    private static final String COLUMN_HEMISPHERE = "hemisphere";
    private static final String COLUMN_OBESITY = "obesity";
    private static final String COLUMN_STRESS_LEVEL = "stressLevel";
    private static final String COLUMN_PHYSICAL_ACTIVITY_DAYS_PER_WEEK = "physicalActivityDaysPerWeek";

    private static final String COLUMN_PREDICTION = "prediction";
    private static final String COLUMN_TIMESTAMP = "timestamp";

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_CHOLESTEROL + " TEXT, " +
                COLUMN_SYSTOLIC + " TEXT, " +
                COLUMN_EXERCISE_HOURS_PER_WEEK + " TEXT, " +
                COLUMN_TRIGLYCERIDES + " TEXT, " +
                COLUMN_INCOME + " TEXT, " +
                COLUMN_AGE + " TEXT, " +
                COLUMN_HEART_RATE + " TEXT, " +
                COLUMN_SEDENTARY_HOURS_PER_DAY + " TEXT, " +
                COLUMN_DIASTOLIC + " TEXT, " +
                COLUMN_SLEEP_HOURS_PER_DAY + " TEXT, " +
                COLUMN_ALCOHOL_CONSUMPTION + " TEXT, " +
                COLUMN_DIABETES + " TEXT, " +
                COLUMN_DIET + " TEXT, " +
                COLUMN_CONTINENT + " TEXT, " +
                COLUMN_SEX + " TEXT, " +
                COLUMN_MEDICATION_USE + " TEXT, " +
                COLUMN_PREVIOUS_HEART_PROBLEMS + " TEXT, " +
                COLUMN_FAMILY_HISTORY + " TEXT, " +
                COLUMN_SMOKING + " TEXT, " +
                COLUMN_HEMISPHERE + " TEXT, " +
                COLUMN_OBESITY + " TEXT, " +
                COLUMN_STRESS_LEVEL + " TEXT, " +
                COLUMN_PHYSICAL_ACTIVITY_DAYS_PER_WEEK + " TEXT, " +
                COLUMN_PREDICTION + " TEXT, " +
                COLUMN_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP)";
        db.execSQL(createTable);
    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String cholesterol, String systolic, String exerciseHoursPerWeek,
                              String triglycerides, String income, String age, String heartRate,
                              String sedentaryHoursPerDay, String diastolic, String sleepHoursPerDay,
                              String alcoholConsumption, String diabetes, String diet, String continent,
                              String sex, String medicationUse, String previousHeartProblems,
                              String familyHistory, String smoking, String hemisphere, String obesity,
                              String stressLevel, String physicalActivityDaysPerWeek, String prediction) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLUMN_CHOLESTEROL, cholesterol);
        contentValues.put(COLUMN_SYSTOLIC, systolic);
        contentValues.put(COLUMN_EXERCISE_HOURS_PER_WEEK, exerciseHoursPerWeek);
        contentValues.put(COLUMN_TRIGLYCERIDES, triglycerides);
        contentValues.put(COLUMN_INCOME, income);
        contentValues.put(COLUMN_AGE, age);
        contentValues.put(COLUMN_HEART_RATE, heartRate);
        contentValues.put(COLUMN_SEDENTARY_HOURS_PER_DAY, sedentaryHoursPerDay);
        contentValues.put(COLUMN_DIASTOLIC, diastolic);
        contentValues.put(COLUMN_SLEEP_HOURS_PER_DAY, sleepHoursPerDay);
        contentValues.put(COLUMN_ALCOHOL_CONSUMPTION, alcoholConsumption);
        contentValues.put(COLUMN_DIABETES, diabetes);
        contentValues.put(COLUMN_DIET, diet);
        contentValues.put(COLUMN_CONTINENT, continent);
        contentValues.put(COLUMN_SEX, sex);
        contentValues.put(COLUMN_MEDICATION_USE, medicationUse);
        contentValues.put(COLUMN_PREVIOUS_HEART_PROBLEMS, previousHeartProblems);
        contentValues.put(COLUMN_FAMILY_HISTORY, familyHistory);
        contentValues.put(COLUMN_SMOKING, smoking);
        contentValues.put(COLUMN_HEMISPHERE, hemisphere);
        contentValues.put(COLUMN_OBESITY, obesity);
        contentValues.put(COLUMN_STRESS_LEVEL, stressLevel);
        contentValues.put(COLUMN_PHYSICAL_ACTIVITY_DAYS_PER_WEEK, physicalActivityDaysPerWeek);

        contentValues.put(COLUMN_PREDICTION, prediction);

        long result = db.insert(TABLE_NAME, null, contentValues);
        return result != -1;
    }

    public void deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,COLUMN_ID + "=?",new String[]{String.valueOf(id)});
        db.close();
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);
        db.close();
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    }
}

