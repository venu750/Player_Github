package com.eadp.assignment.player.model;

public class Product {
    private String productName;
    private String franchise;

    public Product(){

    }
    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getFranchise() {
        return franchise;
    }

    public void setFranchise(String franchise) {
        this.franchise = franchise;
    }

    public Product(String productName, String franchise) {
        this.productName = productName;
        this.franchise = franchise;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productName='" + productName + '\'' +
                ", franchise='" + franchise + '\'' +
                '}';
    }
}
