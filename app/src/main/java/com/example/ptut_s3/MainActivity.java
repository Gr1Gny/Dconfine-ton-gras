package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.ptut_s3.bdd.UtilisateurBDD;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView img = (ImageView) findViewById(R.id.connexion);
        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                startActivity(new Intent(MainActivity.this, page_connexion.class));

            }
        });

        ImageView img2 = (ImageView) findViewById(R.id.inscription);
        img2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                startActivity(new Intent(MainActivity.this, page_inscription.class));
            }
        });
    }
}