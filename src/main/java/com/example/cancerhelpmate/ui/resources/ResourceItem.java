package com.example.cancerhelpmate.ui.resources;

public class ResourceItem {
    private String name;
    private int image;
    private String link;
    private int bgColor;

    public ResourceItem(String name, int image, String link, int bgColor){
        this.name = name;
        this.image = image;
        this.link = link;
        this.bgColor = bgColor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBgColor() {
        return bgColor;
    }

    public void setBgColor(int bgColor) {
        this.bgColor = bgColor;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
