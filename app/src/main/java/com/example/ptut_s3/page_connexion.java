package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptut_s3.bdd.UtilisateurBDD;
import com.example.ptut_s3.metier.Utilisateur;

import java.io.Serializable;
import java.util.concurrent.ExecutionException;

public class page_connexion extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_connexion);

        ImageView img = (ImageView) findViewById(R.id.connexion);
        ImageView inscription = (ImageView) findViewById(R.id.signUpButton);
        EditText mail = (EditText) findViewById(R.id.plain_text_input);
        EditText mdp = (EditText) findViewById(R.id.plain_text_input2);
        TextView erreur = (TextView) findViewById(R.id.erreur);

        img.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // your code here
                //startActivity(new Intent(MainActivity.this, page_connexion.class)
                UtilisateurBDD bdd = new UtilisateurBDD();
                try {
                    Utilisateur u;
                    u = bdd.Connexion(String.valueOf(mail.getText()),String.valueOf(mdp.getText()));
                    if(u != null){
                        Intent i = new Intent(page_connexion.this, page_calories.class);
                        i.putExtra("MyClass", (Serializable) u);
                        startActivity(i);
                    }
                    else{
                        erreur.setText("aucun utilisateur ne correspond");
                        mail.setText("");
                        mdp.setText("");
                    }
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        inscription.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(page_connexion.this, page_inscription.class);
                startActivity(i);
            }
        });
    }
}