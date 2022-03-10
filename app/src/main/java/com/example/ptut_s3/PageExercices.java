package com.example.ptut_s3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ptut_s3.bdd.ExerciceBDD;
import com.example.ptut_s3.metier.ExerciceData;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;

public class PageExercices extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_exercices);

        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ExerciceBDD bdd = new ExerciceBDD();
        ExerciceData[] exerciseData2 = null;
        try {
            exerciseData2 = bdd.List();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        for (ExerciceData var : exerciseData2)
        {
            var.setExerciseImage(getResources().getIdentifier(var.getExerciseImageS(),"drawable", getPackageName()));
        }
        /*ExerciceData[] exerciseData = new ExerciceData[]{
                new ExerciceData("Pompes","7", "4x10 répétitions", "Facile", R.drawable.pushup),
                new ExerciceData("Squats","15", "4x25 répétitions", "Facile", R.drawable.squat),
                new ExerciceData("Abdos","12", "4x15 répétitions", "Facile", R.drawable.situp),
                new ExerciceData("Crunchs","14", "4x5 répétitions", "Facile", getResources().getIdentifier("situp","drawable", getPackageName()))
        };*/

        ExerciceAdapter exerciseAdapter = new ExerciceAdapter(exerciseData2, PageExercices.this);
        recyclerView.setAdapter(exerciseAdapter);

        ImageView exos = (ImageView) findViewById(R.id.imageView6);
        exos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PageExercices.this, page_calories.class);
                v.getContext().startActivity(i);
            }
        });

        ImageView profil = (ImageView) findViewById(R.id.imageView8);
        profil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PageExercices.this, profil.class);
                v.getContext().startActivity(i);
            }
        });

        ImageView favoris = (ImageView) findViewById(R.id.imageView7);
        favoris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PageExercices.this, favoris.class);
                v.getContext().startActivity(i);
            }
        });

    }
}