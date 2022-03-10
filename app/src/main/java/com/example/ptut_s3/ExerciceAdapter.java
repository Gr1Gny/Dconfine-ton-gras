package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.ptut_s3.metier.ExerciceData;

import java.io.Serializable;

public class ExerciceAdapter extends RecyclerView.Adapter<ExerciceAdapter.ViewHolder>{

    ExerciceData[] exerciseData;
    Context context;

    public ExerciceAdapter(ExerciceData[] exerciseData, PageExercices activity) {
        this.exerciseData = exerciseData;
        this.context = activity;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.exercices_list,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciceAdapter.ViewHolder holder, int position) {
        ExerciceData exerciseDataList = exerciseData[position];
        holder.textViewName.setText(exerciseDataList.getExerciseName());
        holder.textViewCalories.setText(exerciseDataList.getCalories() + " calories");
        holder.textViewSeries.setText(exerciseDataList.getSeries() + " répétitions");
        holder.textViewDifficulty.setText(exerciseDataList.getDifficulty());
        holder.exerciseImage.setImageResource(exerciseDataList.getExerciseImage());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, exerciseDataList.getExerciseName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(v.getContext(), PageExercice.class);
                i.putExtra("nom", (Serializable) exerciseDataList.getExerciseName());
                i.putExtra("img", (Serializable) exerciseDataList.getExerciseImage());
                i.putExtra("serie", (Serializable) exerciseDataList.getSeries());
                i.putExtra("cal", (Serializable) exerciseDataList.getCalories());
                i.putExtra("id", (Serializable) exerciseDataList.getId());
                v.getContext().startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return exerciseData.length;
    }


    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView textViewName;
        TextView textViewCalories;
        TextView textViewSeries;
        TextView textViewDifficulty;
        ImageView exerciseImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewName = itemView.findViewById(R.id.textName);
            textViewCalories = itemView.findViewById(R.id.textCalories);
            textViewSeries = itemView.findViewById(R.id.textSeries);
            textViewDifficulty = itemView.findViewById(R.id.textDifficulty);
            exerciseImage = itemView.findViewById(R.id.imageView);
        }
    }
}