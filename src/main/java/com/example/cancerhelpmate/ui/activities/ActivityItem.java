package com.example.cancerhelpmate.ui.activities;

public class ActivityItem {

    private String name;
    private ActivityCategory category;
    private int navigationLink;
    private int colorResource;
    private int iconResource;

    public ActivityItem(){

    }

    public ActivityItem(String name, int navigationLink, int colorResource, int iconResource) {
        this.name = name;
        this.navigationLink = navigationLink;
        this.colorResource = colorResource;
        this.iconResource = iconResource;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ActivityCategory getCategory() {
        return category;
    }

    public void setCategory(ActivityCategory category) {
        this.category = category;
    }

    public int getNavigationLink() {
        return navigationLink;
    }

    public void setNavigationLink(int navigationLink) {
        this.navigationLink = navigationLink;
    }

    public int getColorResource() {
        return colorResource;
    }

    public void setColorResource(int colorResource) {
        this.colorResource = colorResource;
    }

    public int getIconResource() {
        return iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
