package com.example.cancerhelpmate.database.recipes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.cancerhelpmate.R;

import org.jetbrains.annotations.NotNull;

@Entity(tableName = "recipes")
public class RecipeEntry {

    @PrimaryKey(autoGenerate = true)
    @NotNull
    @ColumnInfo(name = "recipe_id")
    private int id;
    @ColumnInfo(name = "recipe_name")
    private String name;
    @ColumnInfo(name = "recipe_breakfast_type")
    private boolean breakfastType;
    @ColumnInfo(name = "recipe_lunch_type")
    private boolean lunchType;
    @ColumnInfo(name = "recipe_dinner_type")
    private boolean dinnerType;
    @ColumnInfo(name = "recipe_supper_type")
    private boolean supperType;
    @ColumnInfo(name = "recipe_extra_type")
    private boolean extraType;
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
    @ColumnInfo(name = "recipe_vegetarian")
    private boolean vegetarian;
    @ColumnInfo(name = "recipe_vegan")
    private boolean vegan;


    @Ignore
    public RecipeEntry( ){
        this.imageLink = R.drawable.ic_meal_default;
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

    public RecipeEntry( String name,double calories, double protein, double fat,double saturates, double carbohydrates, double fibre,  int preparation_time, int cooking_time, int imageLink, String videoLink,String instructions) {
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

    public boolean isBreakfastType() {
        return breakfastType;
    }

    public void setBreakfastType(boolean breakfastType) {
        this.breakfastType = breakfastType;
    }

    public boolean isLunchType() {
        return lunchType;
    }

    public void setLunchType(boolean lunchType) {
        this.lunchType = lunchType;
    }

    public boolean isDinnerType() {
        return dinnerType;
    }

    public void setDinnerType(boolean dinnerType) {
        this.dinnerType = dinnerType;
    }

    public boolean isSupperType() {
        return supperType;
    }

    public void setSupperType(boolean supperType) {
        this.supperType = supperType;
    }

    public boolean isExtraType() {
        return extraType;
    }

    public void setExtraType(boolean extraType) {
        this.extraType = extraType;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }

    public boolean isVegan() {
        return vegan;
    }

    public void setVegan(boolean vegan) {
        this.vegan = vegan;
    }
}
