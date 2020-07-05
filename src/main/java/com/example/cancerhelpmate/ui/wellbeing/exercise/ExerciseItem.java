package com.example.cancerhelpmate.ui.wellbeing.exercise;

public class ExerciseItem {
    private String title;
    private String benefits;
    private int imageResource;
    private String videoLink;
    private boolean expanded;
    private String description;

    public ExerciseItem(String title, String benefits, int imageResource, String videoLink) {
        this.title = title;
        this.benefits = benefits;
        this.imageResource = imageResource;
        this.videoLink = videoLink;
        this.expanded = false;
    }

    public ExerciseItem(String title, String description){
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }

    public String getVideoLink() {
        return videoLink;
    }

    public void setVideoLink(String videoLink) {
        this.videoLink = videoLink;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public void toggleExpanded(){
        this.expanded = !expanded;
    }

    public String getBenefits() {
        return benefits;
    }

    public void setBenefits(String benefits) {
        this.benefits = benefits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
