package com.example.ptut_s3.metier;

import java.io.Serializable;

public class Utilisateur implements Serializable {
    private int ID;
    private String mail;
    private String mdp;
    private String nom;
    private String prenom;
    private int age;
    private float poids;
    private int obj_cal;
    private int cal;
    private float taille;

    public int getID() {
        return ID;
    }

    public String getMdp() {
        return mdp;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public int getAge() {
        return age;
    }

    public float getPoids() {
        return poids;
    }

    public float getTaille() {
        return taille;
    }

    public int getObj_cal() {
        return obj_cal;
    }

    public int getCcal() {
        return cal;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPoids(float poids) {
        this.poids = poids;
    }

    public void setObj_cal(int obj_cal) { this.obj_cal = obj_cal; }

    public void setCal(int cal) { this.cal = cal; }

    public void setTaille(float taille) { this.taille = taille; }
}
