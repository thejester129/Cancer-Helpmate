package com.example.cancerhelpmate.database.recipes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import org.jetbrains.annotations.NotNull;

import java.io.Serializable;

@Entity(tableName = "recipes")
public class RecipeEntry implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "recipe_id")
    private int id;
    @ColumnInfo(name = "recipe_name")
    private String name;
    @ColumnInfo(name = "recipe_calories")
    private double calories;
    @ColumnInfo(name = "recipe_protein")
    private double protein;
    @ColumnInfo(name = "recipe_fat")
    private double fat;
    @ColumnInfo(name = "recipe_saturates")
    private double saturates;
    @ColumnInfo(name = "recipe_carbohydrates")
    private double carbohydrates;
    @ColumnInfo(name = "recipe_fibre")
    private double fibre;
    @ColumnInfo(name = "recipe_instructions")
    private String instructions;
    @ColumnInfo(name = "recipe_preparation_time")
    private int preparation_time;
    @ColumnInfo(name = "recipe_cooking_time")
    private int cooking_time;
    @ColumnInfo(name = "recipe_image_link")
    private int imageLink;
    @ColumnInfo(name = "recipe_video_link")
    private String videoLink;

    @Ignore
    public RecipeEntry( ){
    }

    @Ignore
    public RecipeEntry(String name){
        this.name = name;
    }

    @Ignore
    public RecipeEntry(String name, int imageLink){
        this.name = name;
        this.imageLink = imageLink;
    }

    public RecipeEntry( String name, double calories, double protein, double fat,double saturates, double carbohydrates, double fibre,  int preparation_time, int cooking_time, int imageLink, String videoLink,String instructions) {
        this.name = name;
        this.calories = calories;
        this.protein = protein;
        this.fat = fat;
        this.saturates = saturates;
        this.carbohydrates = carbohydrates;
        this.fibre = fibre;
        this.instructions = instructions;
        this.preparation_time = preparation_time;
        this.cooking_time = cooking_time;
        this.imageLink = imageLink;
        this.videoLink = videoLink;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCalories() {
        return calories;
    }

    public void setCalories(double calories) {
        this.calories = calories;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public double getSaturates() {
        return saturates;
    }

    public void setSaturates(double saturates) {
        this.saturates = saturates;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public double getFibre() {
        return fibre;
    }

    public void setFibre(double fibre) {
        this.fibre = fibre;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getPreparation_time() {
        return preparation_time;
    }

    public void setPreparation_time(int preparation_time) {
        this.preparation_time = preparation_time;
    }

    public int getCooking_time() {
        return cooking_time;
    }

    public void setCooking_time(int cooking_time) {
        this.cooking_time = cooking_time;
    }

    public int getImageLink() {
        return imageLink;
    }

    public void setImageLink(int imageLink) {
        this.imageLink = imageLink;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }
}
