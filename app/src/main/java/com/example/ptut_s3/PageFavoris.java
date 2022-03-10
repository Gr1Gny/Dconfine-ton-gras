package com.example.ptut_s3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptut_s3.bdd.ExerciceBDD;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class PageFavoris extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_favoris);

        String nomexo = (String) getIntent().getSerializableExtra("nom");
        Integer image = (Integer) getIntent().getSerializableExtra("img");
        String series = (String) getIntent().getSerializableExtra("serie");
        String cals = (String) getIntent().getSerializableExtra("cal");
        int id = (int) getIntent().getSerializableExtra("id");

        ImageView img = (ImageView) findViewById(R.id.imageView2);
        TextView titre = (TextView) findViewById(R.id.titreExo);
        TextView serie = (TextView) findViewById(R.id.textView4);
        TextView cal = (TextView) findViewById(R.id.textView3);
        Button retour = findViewById(R.id.button2);
        Button commencer = findViewById(R.id.button);

        img.setImageResource(image);
        titre.setText(nomexo);
        serie.setText("Répétitions : " + series);
        cal.setText("Calories : " + cals);

        retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PageFavoris.this, favoris.class);
                v.getContext().startActivity(i);
            }
        });

        commencer.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(PageFavoris.this, TImer.class);
                i.putExtra("cal", (Serializable) cals);
                v.getContext().startActivity(i);
            }
        });

        TextView favoris = (TextView) findViewById(R.id.favoris);
        favoris.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            public void onClick(View v) {
                ExerciceBDD bdd = new ExerciceBDD();
                try {
                    bdd.SetFav(false,id);
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}