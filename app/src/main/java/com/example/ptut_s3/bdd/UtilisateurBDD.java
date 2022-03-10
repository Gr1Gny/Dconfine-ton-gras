package com.example.ptut_s3.bdd;

import android.os.AsyncTask;

import com.example.ptut_s3.metier.CurrentUser;
import com.example.ptut_s3.metier.Utilisateur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

public class UtilisateurBDD {

    public Utilisateur Connexion(String mail, String mdp) throws ExecutionException, InterruptedException {
        Read bdd = (Read) new Read(mail,mdp);
        //on attend la connection a la bdd
        bdd.execute().get();
        Utilisateur u = bdd.User();
        return u;
    }

    public void InsererUtilisateur(String nom, String mail, String mdp, String prenom) throws ExecutionException, InterruptedException {
        Insert bdd = (Insert) new Insert(nom,mail,mdp,prenom);
        //on attend la connection a la bdd
        bdd.execute().get();
    }

    public void UpdateUtilisateur(int cal, float taille, float poids, int age) throws ExecutionException, InterruptedException {
        Update bdd = (Update) new Update(cal,taille,poids,age);
        //on attend la connection a la bdd
        bdd.execute().get();
    }

    public boolean exist(String mail) throws ExecutionException, InterruptedException {
        Existe bdd = (Existe) new Existe(mail);
        //on attend la connection a la bdd
        bdd.execute().get();
        return bdd.exist();
    }


    private class Read extends AsyncTask<String, Void, Void> {

        private String mail;
        private String mdp;
        private Utilisateur u;
        Boolean ended = false;

        public Read(String mail, String mdp){
            this.mail = mail;
            this.mdp = mdp;
        }

        @Override
        protected Void doInBackground(String... params) {

            Connection conn = null;
            ResultSet res;

            //connection a la bdd
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println(conn = DriverManager.getConnection("jdbc:mysql://mysql-noham.alwaysdata.net:3306/noham_test", "noham", "H7ASFa2dv423zez"));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }

            //on prepare notre requete
            try {

                Statement stm = conn.createStatement();
                PreparedStatement rechercheID = conn.prepareStatement("SELECT * FROM utilisateur WHERE mail = ? AND mdp = ?");

                rechercheID.setString(1, mail);
                rechercheID.setString(2, mdp);

                //on execute la requete
                res = rechercheID.executeQuery();

                //si la requete revoie quelque chose
                if (res.next()) {
                    u = new Utilisateur();
                    u.setID(res.getInt("id"));
                    u.setMail(res.getString("mail"));
                    u.setMdp(res.getString("mdp"));
                    u.setNom(res.getString("nom"));
                    u.setPrenom(res.getString("prenom"));
                    u.setAge(res.getInt("age"));
                    u.setPoids(res.getFloat("poids"));
                    u.setObj_cal(res.getInt("caloriesObj"));
                    u.setCal(res.getInt("calories"));
                    u.setTaille(res.getFloat("taille"));
                }
                else{
                    System.out.println("existe pas");
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }

        public Boolean Ended() {
            return ended;
        }

        public Utilisateur User() {
            return u;
        }
    }

    private class Insert extends AsyncTask<String, Void, Void> {

        private String nom;
        private String mail;
        private String mdp;
        private String prenom;

        public Insert(String name, String email, String pass, String prenom) {
            this.nom = name;
            this.mail = email;
            this.mdp = pass;
            this.prenom = prenom;
        }

        @Override
        protected Void doInBackground(String... params) {

            Connection conn = null;
            ResultSet res;

            //connection a la bdd
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println(conn = DriverManager.getConnection("jdbc:mysql://mysql-noham.alwaysdata.net:3306/noham_test", "noham", "H7ASFa2dv423zez"));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }


            //on prepare notre requete
            try {

                Statement stm2 = conn.createStatement();
                PreparedStatement verif = conn.prepareStatement("SELECT * FROM utilisateur WHERE mail = ?");

                verif.setString(1, mail);

                //on execute la requete
                res = verif.executeQuery();

                //si la requete revoie quelque chose
                if (!res.next()) {
                    //insertion
                    Statement stm = conn.createStatement();
                    PreparedStatement insert = conn.prepareStatement("INSERT INTO utilisateur (mail, mdp, nom, prenom, calories, caloriesObj) VALUES (?,?,?,?,?,?)");

                    insert.setString(1, this.mail);
                    insert.setString(2, this.mdp);
                    insert.setString(3, this.nom);
                    insert.setString(4, this.prenom);
                    insert.setInt(5, 1750);
                    insert.setInt(6, 2500);

                    //on execute la requete
                    insert.executeUpdate();
                }
                else{
                    System.out.println("existe");
                }



            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }

    }

    private class Update extends AsyncTask<String, Void, Void> {

        private int cal;
        private float taille;
        private float poids;
        private int age;

        public Update(int cal,float taille,float poids,int age) {
            this.cal = cal;
            this.taille = taille;
            this.poids = poids;
            this.age = age;
        }

        @Override
        protected Void doInBackground(String... params) {

            Connection conn = null;
            ResultSet res;

            //connection a la bdd
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println(conn = DriverManager.getConnection("jdbc:mysql://mysql-noham.alwaysdata.net:3306/noham_test", "noham", "H7ASFa2dv423zez"));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }


            //on prepare notre requete
            try {

                Statement stm2 = conn.createStatement();
                PreparedStatement updatequery = conn.prepareStatement("UPDATE utilisateur SET age = ?, poids = ?, taille = ?, caloriesObj = ? WHERE id = ?");

                updatequery.setInt(1,this.age);
                CurrentUser.user.setAge(this.age);
                updatequery.setFloat(2,this.poids);
                CurrentUser.user.setPoids(this.poids);
                updatequery.setFloat(3,this.taille);
                CurrentUser.user.setTaille(this.taille);
                updatequery.setInt(4,this.cal);
                CurrentUser.user.setObj_cal(this.cal);
                updatequery.setInt(5,CurrentUser.user.getID());

                updatequery.executeUpdate();
                updatequery.close();
            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }

    }

    private class Existe extends AsyncTask<String, Void, Void> {

        private String mail;
        boolean b = false;

        public Existe(String mail) {
            this.mail = mail;
        }

        @Override
        protected Void doInBackground(String... params) {

            Connection conn = null;
            ResultSet res;


            //connection a la bdd
            try {
                Class.forName("com.mysql.jdbc.Driver");
                System.out.println(conn = DriverManager.getConnection("jdbc:mysql://mysql-noham.alwaysdata.net:3306/noham_test", "noham", "H7ASFa2dv423zez"));
            } catch (ClassNotFoundException | SQLException e) {
                System.out.println(e);
            }

            //on prepare notre requete
            try {

                Statement stm = conn.createStatement();
                PreparedStatement rechercheID = conn.prepareStatement("SELECT * FROM utilisateur WHERE mail = ?");

                rechercheID.setString(1, mail);

                //on execute la requete
                res = rechercheID.executeQuery();

                //si la requete revoie quelque chose
                if (res.next()) {
                    b = true;
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);
                ;
            }
            return null;
        }

        public Boolean exist() {
            return b;
        }
    }

}