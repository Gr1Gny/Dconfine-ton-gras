package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptut_s3.bdd.UtilisateurBDD;
import com.example.ptut_s3.metier.CurrentUser;
import com.example.ptut_s3.metier.Utilisateur;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class profil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        EditText cals = (EditText) findViewById(R.id.Cals);
        EditText taille = (EditText) findViewById(R.id.Taille);
        EditText age = (EditText) findViewById(R.id.Age);
        EditText poids = (EditText) findViewById(R.id.Poids);
        TextView erreur = (TextView) findViewById(R.id.erreur);

        Button modifier = (Button) findViewById(R.id.finis);
        Button retour = (Button) findViewById(R.id.retour);

        age.setText(String.valueOf(CurrentUser.user.getAge()));
        poids.setText(String.valueOf(CurrentUser.user.getPoids()));
        taille.setText(String.valueOf(CurrentUser.user.getTaille()));
        cals.setText(String.valueOf(CurrentUser.user.getObj_cal()));

        modifier.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int ncals;
                float ntaille;
                float npoids;
                int nage;
                boolean b;
                UtilisateurBDD bdd = new UtilisateurBDD();
                try {
                    Integer.parseInt(String.valueOf(cals.getText()));
                    Float.parseFloat(String.valueOf(taille.getText()));
                    Float.parseFloat(String.valueOf(poids.getText()));
                    Integer.parseInt(String.valueOf(age.getText()));
                    b = true;
                }
                catch( Exception e ) {
                    b = false;
                }
                if(b) {
                    try {
                        bdd.UpdateUtilisateur(Integer.parseInt(String.valueOf(cals.getText())), Float.parseFloat(String.valueOf(taille.getText())),
                                Float.parseFloat(String.valueOf(poids.getText())), Integer.parseInt(String.valueOf(age.getText())));
                        Intent i = new Intent(profil.this, page_calories.class);
                        startActivity(i);
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    erreur.setText("veuillez renseigner des valeurs correctes");
                    age.setText(String.valueOf(CurrentUser.user.getAge()));
                    poids.setText(String.valueOf(CurrentUser.user.getPoids()));
                    taille.setText(String.valueOf(CurrentUser.user.getTaille()));
                    cals.setText(String.valueOf(CurrentUser.user.getObj_cal()));
                }
            }
        });

        retour.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(profil.this, page_calories.class);
                startActivity(i);
            }
        });

    }
}