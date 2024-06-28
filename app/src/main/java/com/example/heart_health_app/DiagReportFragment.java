package com.example.heart_health_app;

import android.database.Cursor;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DiagReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DiagReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private DatabaseHelper databaseHelper;
    private RecyclerView recyclerView;
    private RecordsAdapter recordsAdapter;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    public DiagReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DiagReportFragment newInstance(String param1, String param2) {
        DiagReportFragment fragment = new DiagReportFragment();
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

        View view = inflater.inflate(R.layout.fragment_diag_report, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        databaseHelper = new DatabaseHelper(getContext());
        List<Record> records = getRecordsFromDatabase();
        recordsAdapter = new RecordsAdapter(getContext(),records);
        recyclerView.setAdapter(recordsAdapter);
        // Inflate the layout for this fragment
        return view;
    }
    private List<Record> getRecordsFromDatabase() {
        List<Record> records = new ArrayList<>();
        Cursor cursor = databaseHelper.getAllData();
        if (cursor != null && cursor.moveToFirst()) {
            int heartRateIndex = cursor.getColumnIndex("heartRate");
            int predictionIndex = cursor.getColumnIndex("prediction");
            int timeStampIndex = cursor.getColumnIndex("timestamp");
            int idIndex = cursor.getColumnIndex("id");

            if (heartRateIndex == -1 || predictionIndex == -1) {
                // Handle the case where the column does not exist
                // You can log an error or throw an exception
                Log.e("DiagReportFragment", "Column not found in the database");
                return records;
            }

            do {
                String heartRate = cursor.getString(heartRateIndex);
                String prediction = cursor.getString(predictionIndex);
                String timeStamp = cursor.getString(timeStampIndex);
                String id = cursor.getString(idIndex);
                records.add(new Record(heartRate, prediction,timeStamp,id));
            } while (cursor.moveToNext());
        }
        return records;
    }


}