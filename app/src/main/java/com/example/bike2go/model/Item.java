package com.example.bike2go.model;

import java.util.List;

public class Item {

    private String price;

    private List<Integer> images;
    private String description;
    private String shortDescription;

    private int totalImages;

    public Item() {
    }

    public Item(String price,  String shortDescription) {
        this.price = price;

        this.shortDescription = shortDescription;

    }

    public Item(String price, List<Integer> images, String description, String shortDescription, int totalImages) {
        this.price = price;

        this.images = images;
        this.description = description;

        this.shortDescription = shortDescription;
        this.totalImages = totalImages;
    }




    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public List<Integer> getImages() {
        return images;
    }

    public void setImages(List<Integer> images) {
        this.images = images;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public int getTotalImages() {
        return totalImages;
    }

    public void setTotalImages(int totalImages) {
        this.totalImages = totalImages;
    }
}
