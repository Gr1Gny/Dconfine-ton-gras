package com.example.ptut_s3.bdd;

import android.os.AsyncTask;
import android.os.Build;

import androidx.annotation.RequiresApi;

import com.example.ptut_s3.metier.CurrentUser;
import com.example.ptut_s3.metier.ExerciceData;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ExerciceBDD {

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ExerciceData[] List() throws ExecutionException, InterruptedException {
        ExerciceBDD.Read bdd = (ExerciceBDD.Read) new ExerciceBDD.Read();
        //on attend la connection a la bdd
        bdd.execute().get();
        ExerciceData[] exos = bdd.List();
        return exos;
    }

    private class Read extends AsyncTask<String, Void, Void> {

        ArrayList<ExerciceData> exos = new ArrayList<ExerciceData>();
        Boolean ended = false;

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
                PreparedStatement rechercheID = conn.prepareStatement("SELECT * FROM exercice");
                //on execute la requete
                res = rechercheID.executeQuery();

                //si la requete revoie quelque chose
                while(res.next()) {
                    ExerciceData e = new ExerciceData();
                    e.setId(res.getInt("id"));
                    e.setCalories(res.getString("calories"));
                    e.setExerciseImageS(res.getString("exerciseImage"));
                    e.setDifficulty(res.getString("difficulty"));
                    e.setExerciseName(res.getString("exerciseName"));
                    e.setSeries(res.getString("series"));
                    exos.add(e);
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }

        public Boolean Ended() {
            return ended;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ExerciceData[] List() {
            ExerciceData[] ex = new ExerciceData[exos.size()];
            ex = exos.toArray(ex);
            return ex;
        }
    }

    public void UpdateCal(int cal) throws ExecutionException, InterruptedException {
        ExerciceBDD.cal bdd = (ExerciceBDD.cal) new ExerciceBDD.cal(cal);
        //on attend la connection a la bdd
        bdd.execute().get();

    }

    private class cal extends AsyncTask<String, Void, Void> {

        private int cal;

        public cal(int cal) {
            this.cal = cal;
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
                int calories = cal;
                cal += CurrentUser.user.getCcal();
                CurrentUser.user.setCal(calories + CurrentUser.user.getCcal());
                Statement stm = conn.createStatement();
                PreparedStatement updatequery = conn.prepareStatement("UPDATE utilisateur set calories = ? WHERE id = ?");
                //on execute la requete
                updatequery.setInt(1, cal);
                updatequery.setInt(2, CurrentUser.user.getID());
                updatequery.executeUpdate();
                System.out.println(CurrentUser.user.getID());
                System.out.println(cal);

            } catch (SQLException throwables) {
                System.out.println(throwables);
                ;
            }
            return null;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public ExerciceData[] ListFav() throws ExecutionException, InterruptedException {
        ExerciceBDD.ReadFav bdd = (ExerciceBDD.ReadFav) new ExerciceBDD.ReadFav();
        //on attend la connection a la bdd
        bdd.execute().get();
        ExerciceData[] exos = bdd.ListFav();
        return exos;
    }

    private class ReadFav extends AsyncTask<String, Void, Void> {

        ArrayList<ExerciceData> exos = new ArrayList<ExerciceData>();
        Boolean ended = false;

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
                PreparedStatement rechercheID = conn.prepareStatement("SELECT * FROM exercice WHERE favoris = 'Y'");
                //on execute la requete
                res = rechercheID.executeQuery();

                //si la requete revoie quelque chose
                while(res.next()) {
                    ExerciceData e = new ExerciceData();
                    e.setId(res.getInt("id"));
                    e.setCalories(res.getString("calories"));
                    e.setExerciseImageS(res.getString("exerciseImage"));
                    e.setDifficulty(res.getString("difficulty"));
                    e.setExerciseName(res.getString("exerciseName"));
                    e.setSeries(res.getString("series"));
                    exos.add(e);
                }
            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }

        public Boolean Ended() {
            return ended;
        }

        @RequiresApi(api = Build.VERSION_CODES.N)
        public ExerciceData[] ListFav() {
            ExerciceData[] ex = new ExerciceData[exos.size()];
            ex = exos.toArray(ex);
            return ex;
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void SetFav(boolean b,int id) throws ExecutionException, InterruptedException {
        ExerciceBDD.setFavclass bdd = (ExerciceBDD.setFavclass) new ExerciceBDD.setFavclass(b,id);
        //on attend la connection a la bdd
        bdd.execute().get();
    }

    private class setFavclass extends AsyncTask<String, Void, Void> {

        Boolean b;
        int id;

        public setFavclass(Boolean b,int id) {
            this.b = b;
            this.id = id;
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
                PreparedStatement rechercheID = null;
                Statement stm = conn.createStatement();
                if(b){
                    rechercheID = conn.prepareStatement("UPDATE exercice SET favoris = 'Y' WHERE id = ?");
                    System.out.println("Y");
                }
                else{
                    rechercheID = conn.prepareStatement("UPDATE exercice SET favoris = 'N' WHERE id = ?");
                    System.out.println(id);
                }
                rechercheID.setInt(1,this.id);

                //on execute la requete
                rechercheID.executeUpdate();
            } catch (SQLException throwables) {
                System.out.println(throwables);;
            }
            return null;
        }
    }
}
