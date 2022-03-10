package com.example.ptut_s3;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ptut_s3.bdd.UtilisateurBDD;
import com.example.ptut_s3.metier.Utilisateur;

import java.util.concurrent.ExecutionException;

public class page_inscription extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page_inscription);

        ImageView button = (ImageView) findViewById(R.id.signUpButton);
        ImageView connexion = (ImageView) findViewById(R.id.connexion);
        EditText nom = (EditText) findViewById(R.id.editLastName);
        EditText prenom = (EditText) findViewById(R.id.editFirstName);
        EditText mail = (EditText) findViewById(R.id.editEmail);
        EditText mdp = (EditText) findViewById(R.id.editPassword);
        EditText mdpconfirm = (EditText) findViewById(R.id.retypePassword);
        TextView erreur = (TextView) findViewById(R.id.erreur);

        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            public void onClick(View v) {
                boolean possible = true;
                // your code here
                //startActivity(new Intent(MainActivity.this, page_connexion.class)
                UtilisateurBDD bdd = new UtilisateurBDD();
                boolean b = false;
                try {
                    b = bdd.exist(String.valueOf(mail.getText()));
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(mdp.getText());
                System.out.println(mdpconfirm.getText());
                if(String.valueOf(nom.getText()).equals("") || String.valueOf(prenom.getText()).equals("") || String.valueOf(mail.getText()).equals("") || String.valueOf(mdp.getText()).equals("")){
                    erreur.setText("remplissez tous les champs");
                    possible = false;
                }
                else if(b){
                    erreur.setText("un compte avec ce mail existe déjà");
                    possible = false;
                }
                else if(!String.valueOf(mdp.getText()).equals(String.valueOf(mdpconfirm.getText()))){
                    erreur.setText("saisissez deux fois le même mot de passe");
                    possible = false;
                }
                if(possible){
                    try {
                        new UtilisateurBDD().InsererUtilisateur(String.valueOf(nom.getText()),String.valueOf(mail.getText()),String.valueOf(mdp.getText()),String.valueOf(prenom.getText()));
                    } catch (ExecutionException | InterruptedException e) {
                        e.printStackTrace();
                    }
                    startActivity(new Intent(page_inscription.this, page_connexion.class));
                }
            }
        });

        connexion.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent i = new Intent(page_inscription.this, page_connexion.class);
                startActivity(i);
            }
        });
    }
}