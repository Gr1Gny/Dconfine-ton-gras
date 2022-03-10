package com.example.ptut_s3.metier;

public class ExerciceData {

    private int id;
    private String exerciseName;
    private String calories;
    private String series;
    private String difficulty;
    private Integer exerciseImage;
    private String exerciseImageS;

    public ExerciceData(){

    }

    public ExerciceData(String exerciseName, String calories, String series, String difficulty, Integer exerciseImage) {
        this.exerciseName = exerciseName;
        this.calories = calories;
        this.series = series;
        this.difficulty = difficulty;
        this.exerciseImage = exerciseImage;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExerciseName() {
        return exerciseName;
    }

    public void setExerciseName(String exerciseName) {
        this.exerciseName = exerciseName;
    }

    public String getCalories() {
        return calories;
    }

    public void setCalories(String calories) {
        this.calories = calories;
    }

    public String getSeries() {
        return series;
    }

    public void setSeries(String series) {
        this.series = series;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public Integer getExerciseImage() {
        return exerciseImage;
    }

    public void setExerciseImage(Integer exerciseImage) {
        this.exerciseImage = exerciseImage;
    }

    public String getExerciseImageS() {
        return exerciseImageS;
    }

    public void setExerciseImageS(String exerciseImage) {
        this.exerciseImageS = exerciseImage;
    }
}
