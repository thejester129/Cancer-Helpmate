package com.example.cancerhelpmate.ui.my_hospital;

public class MyHospitalItem {
    private String name;
    private int image;
    private String address;
    private String link;
    private String description;
    private String noOfBeds;

    public MyHospitalItem(String name, int image, String address, String link, String description, String noOfBeds) {
        this.name = name;
        this.image = image;
        this.address = address;
        this.link = link;
        this.description = description;
        this.noOfBeds = noOfBeds;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNoOfBeds() {
        return noOfBeds;
    }

    public void setNoOfBeds(String noOfBeds) {
        this.noOfBeds = noOfBeds;
    }
}
