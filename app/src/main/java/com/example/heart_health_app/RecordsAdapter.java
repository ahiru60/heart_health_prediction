package com.example.heart_health_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecordsAdapter extends RecyclerView.Adapter<RecordsAdapter.RecordViewHolder> {

    private List<Record> records;

    public static class RecordViewHolder extends RecyclerView.ViewHolder {
        public TextView heartRateTextView;
        public TextView predictionTextView;
        public TextView timeStampTextView;

        public DatabaseHelper databaseHelper;

        public ImageButton deleteRecordImgButton;
        public LinearLayout recordItem;

        public RecordViewHolder(View itemView) {
            super(itemView);
            heartRateTextView = itemView.findViewById(R.id.heartRateTextView);
            predictionTextView = itemView.findViewById(R.id.predictionTextView);
            timeStampTextView = itemView.findViewById(R.id.timeStampTextView);
            deleteRecordImgButton = itemView.findViewById(R.id.deleteRecord);
            recordItem = itemView.findViewById(R.id.recordItem);
        }
    }
    Context context;
    public RecordsAdapter(Context context, List<Record> records) {
        this.records = records;
        this.context = context;
    }

    @Override
    public RecordViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_record, parent, false);
        return new RecordViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecordViewHolder holder, int position) {
        Record record = records.get(position);
        holder.heartRateTextView.setText("Heart Rate: "+record.getHeartRate());

        if(record.getPrediction().equals("1")){
            holder.predictionTextView.setText("Unealthy");
            holder.predictionTextView.setTextColor(ContextCompat.getColor(context,R.color.unhealthy_red));
        }
        else if(record.getPrediction().equals("0")){
            holder.predictionTextView.setText("Healthy");
            holder.predictionTextView.setTextColor(ContextCompat.getColor(context,R.color.healthy_green));
        }

        holder.timeStampTextView.setText(record.getTimeStamp());

        holder.deleteRecordImgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.databaseHelper = new DatabaseHelper(context);
                holder.databaseHelper.deleteData(record.getId());
                Toast.makeText(context, "Data Deleted", Toast.LENGTH_SHORT).show();
                //holder.recordItem.setVisibility(View.GONE);
                // Remove the item from the dataset
                records.remove(position);
                // Notify the adapter about the removed item
                notifyItemRemoved(position);
                // Optionally, notify the adapter about the range change if necessary
                notifyItemRangeChanged(position, records.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return records.size();
    }
}

