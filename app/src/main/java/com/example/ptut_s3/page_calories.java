package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.ptut_s3.bdd.UtilisateurBDD;
import com.example.ptut_s3.metier.CurrentUser;
import com.example.ptut_s3.metier.Utilisateur;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class page_calories extends AppCompatActivity {

    private ProgressBar circular_pro;
    private TextView status;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_calories);


        Utilisateur u = (Utilisateur) getIntent().getSerializableExtra("MyClass");
        TextView bvn = (TextView) findViewById(R.id.textView);
        if(u != null){
            CurrentUser.getInstance(u);
        }
        u = CurrentUser.user;
        bvn.setText("Bonjour " + u.getPrenom() + "!");

        circular_pro = (ProgressBar)findViewById(R.id.progressbar_circular);
        status = (TextView)findViewById(R.id.text);
        float pourcent = ((float)u.getCcal()/(float)u.getObj_cal())*100;
        System.out.println(u.getCcal());
        System.out.println(u.getObj_cal());
        circular_pro.setProgress((int)pourcent);
        String s = u.getCcal()+"/"+u.getObj_cal()+"Kcal";
        status.setText(s);

        ImageView exos = (ImageView) findViewById(R.id.imageView5);
        exos.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(page_calories.this, PageExercices.class);
                v.getContext().startActivity(i);
            }
        });

        ImageView profil = (ImageView) findViewById(R.id.imageView8);
        profil.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(page_calories.this, profil.class);
                v.getContext().startActivity(i);
            }
        });

        ImageView favoris = (ImageView) findViewById(R.id.imageView7);
        favoris.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(page_calories.this, favoris.class);
                v.getContext().startActivity(i);
            }
        });
    }
}