package com.example.ptut_s3.metier;

public class CurrentUser {
    private static CurrentUser instance;
    public static Utilisateur user;

    private CurrentUser(Utilisateur user) {
        this.user = user;
    }

    public static CurrentUser getInstance(Utilisateur user) {
        if (instance == null) {
            instance = new CurrentUser(user);
        }
        return instance;
    }
}
