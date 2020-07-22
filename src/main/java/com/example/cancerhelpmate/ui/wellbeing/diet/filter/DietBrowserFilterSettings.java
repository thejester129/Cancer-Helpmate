package com.example.cancerhelpmate.ui.wellbeing.diet.filter;

import com.example.cancerhelpmate.ui.wellbeing.diet.DietRecipeType;

public class DietBrowserFilterSettings {
    private boolean dryAndSoreMouth;
    private boolean problemsChewing;
    private boolean lossOfOfTasteOrSmell;
    private boolean feelingSick;
    private boolean healthyEating;
    private boolean vegetarian;
    private DietFilterRecipeType dietFilterRecipeType = DietFilterRecipeType.ANY;

    public DietBrowserFilterSettings(){

    }

    public boolean isDryAndSoreMouth() {
        return dryAndSoreMouth;
    }

    public void setDryAndSoreMouth(boolean dryAndSoreMouth) {
        this.dryAndSoreMouth = dryAndSoreMouth;
    }

    public boolean isProblemsChewing() {
        return problemsChewing;
    }

    public void setProblemsChewing(boolean problemsChewing) {
        this.problemsChewing = problemsChewing;
    }

    public boolean isLossOfOfTasteOrSmell() {
        return lossOfOfTasteOrSmell;
    }

    public void setLossOfOfTasteOrSmell(boolean lossOfOfTasteOrSmell) {
        this.lossOfOfTasteOrSmell = lossOfOfTasteOrSmell;
    }

    public boolean isFeelingSick() {
        return feelingSick;
    }

    public void setFeelingSick(boolean feelingSick) {
        this.feelingSick = feelingSick;
    }

    public boolean isHealthyEating() {
        return healthyEating;
    }

    public void setHealthyEating(boolean healthyEating) {
        this.healthyEating = healthyEating;
    }

    public DietFilterRecipeType getDietFilterRecipeType() {
        return dietFilterRecipeType;
    }

    public void setDietFilterRecipeType(DietFilterRecipeType dietFilterRecipeType) {
        this.dietFilterRecipeType = dietFilterRecipeType;
    }

    public boolean isVegetarian() {
        return vegetarian;
    }

    public void setVegetarian(boolean vegetarian) {
        this.vegetarian = vegetarian;
    }


}
