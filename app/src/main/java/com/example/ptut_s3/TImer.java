package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.CompoundButton;
import android.widget.ToggleButton;

import com.example.ptut_s3.bdd.ExerciceBDD;

import java.util.concurrent.ExecutionException;

public class TImer extends AppCompatActivity {

    private Chronometer chronometer;
    private long PauseOffSet = 0;
    private ToggleButton toggleButton;
    private Button reset_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        String cals = (String) getIntent().getSerializableExtra("cal");
        chronometer = findViewById(R.id.chronometer);
        toggleButton = findViewById(R.id.Toggle);
        reset_btn = findViewById(R.id.reset_btn);
        toggleButton.setText(null);
        toggleButton.setTextOn(null);
        toggleButton.setTextOff(null);
        toggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    chronometer.setBase(SystemClock.elapsedRealtime()- PauseOffSet);
                    chronometer.start();
                }else{
                    chronometer.stop();
                    PauseOffSet = SystemClock.elapsedRealtime()- chronometer.getBase();
                }
            }
        });
        reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chronometer.setBase(SystemClock.elapsedRealtime());
                PauseOffSet = 0;

            }
        });

        Button retour = findViewById(R.id.retour);
        Button finis = findViewById(R.id.finis);

        retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(TImer.this, PageExercices.class);
                v.getContext().startActivity(i);
            }
        });

        finis.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                try {
                    new ExerciceBDD().UpdateCal(Integer.parseInt(cals));
                } catch (ExecutionException executionException) {
                    executionException.printStackTrace();
                } catch (InterruptedException interruptedException) {
                    interruptedException.printStackTrace();
                }
                Intent i = new Intent(TImer.this, PageExercices.class);
                v.getContext().startActivity(i);
            }
        });
    }
}