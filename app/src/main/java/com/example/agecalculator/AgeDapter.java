package com.example.agecalculator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.agecalculator.Age.AgeElement;

import java.util.ArrayList;

public class AgeDapter extends RecyclerView.Adapter<AgeDapter.ViewHolder> {

    private ArrayList <AgeElement> age;
    private Context context;
    itemClick activity;

    public interface itemClick{
        public void onItemClick(int index);
    }

    public AgeDapter (Context context, ArrayList<AgeElement> ageElements){
        age = ageElements;
        activity = (itemClick) context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.demo_card,  parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.itemView.setTag(age.get(position));
        holder.title.setText(age.get(position).getTitle());
        holder.dateFrom.setText(age.get(position).getFromDate());
    }

    @Override
    public int getItemCount() {
        return age.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView title, dateFrom;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.tvTitle);
            dateFrom = itemView.findViewById(R.id.tvDate);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    activity.onItemClick(age.indexOf(itemView.getTag()));
                }
            });
        }
    }
}
