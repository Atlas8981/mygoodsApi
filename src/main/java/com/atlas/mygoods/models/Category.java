package com.atlas.mygoods.models;

public class Category {
    private String mainCategory;
    private String subCategory;

    public Category(){}

    public Category(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "mainCategory='" + mainCategory + '\'' +
                ", subCategory='" + subCategory + '\'' +
                '}';
    }
}
