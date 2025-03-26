package com.app.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String author;
    private String imageUrl;  // URL to the product image
    private String category;  // Category of the product (e.g., electronics, clothing, etc.)
   /* private String title;
    private String author;*/

    // Getters and setters for the fields
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

   /* public Object getTitle() {
    return title;}

    public Object getAuthor() {
        return author;
    }*/
}
