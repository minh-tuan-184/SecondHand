package com.example.secondhand.product;

public class Product {
    String nameProduct;
    String priceProduct;
    Float ratingProduct;
    String detailProduct;
    String categoryProduct;
    Integer imageUrl;
    Integer remainProduct;

    public Product(String nameProduct, String priceProduct, Float ratingProduct, String detailProduct, String categoryProduct, Integer imageUrl, Integer remainProduct) {
        this.nameProduct = nameProduct;
        this.priceProduct = priceProduct;
        this.ratingProduct = ratingProduct;
        this.detailProduct = detailProduct;
        this.categoryProduct = categoryProduct;
        this.imageUrl = imageUrl;
        this.remainProduct = remainProduct;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public void setNameProduct(String nameProduct) {
        this.nameProduct = nameProduct;
    }

    public String getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(String priceProduct) {
        this.priceProduct = priceProduct;
    }

    public Float getRatingProduct() {
        return ratingProduct;
    }

    public void setRatingProduct(Float ratingProduct) {
        this.ratingProduct = ratingProduct;
    }

    public String getDetailProduct() {
        return detailProduct;
    }

    public void setDetailProduct(String detailProduct) {
        this.detailProduct = detailProduct;
    }

    public String getCategoryProduct() {
        return categoryProduct;
    }

    public void setCategoryProduct(String categoryProduct) {
        this.categoryProduct = categoryProduct;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(Integer imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getRemainProduct() {
        return remainProduct;
    }

    public void setRemainProduct(Integer remainProduct) {
        this.remainProduct = remainProduct;
    }
}
