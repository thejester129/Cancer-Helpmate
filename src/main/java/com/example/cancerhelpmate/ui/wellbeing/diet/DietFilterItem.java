package com.example.cancerhelpmate.ui.wellbeing.diet;

public class DietFilterItem {
    private boolean vegetarian;
    private boolean vegan;
    private RecipeType recipeType;

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
