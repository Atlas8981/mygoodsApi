package com.atlas.mygoods.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

//@Entity
//@Table
public class ItemEntity {
    //    @Id
//    @GeneratedValue
    private Long itemid;
    private String name;
    private String address;
    private String subCategory;
    private String mainCategory;
    private String description;
    private String userid;
    private String phone;
    private int amount;
    private double price;
    private int views;
    private Date date;

    public ItemEntity() {
    }

    public ItemEntity(Long itemid, String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, int amount, double price, int views, Date date) {
        this.itemid = itemid;
        this.name = name;
        this.address = address;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.description = description;
        this.userid = userid;
        this.phone = phone;
        this.amount = amount;
        this.price = price;
        this.views = views;
        this.date = date;
    }

    public ItemEntity(String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, int amount, double price, int views, Date date) {
        this.name = name;
        this.address = address;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.description = description;
        this.userid = userid;
        this.phone = phone;
        this.amount = amount;
        this.price = price;
        this.views = views;
        this.date = date;
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getSubCategory() {
        return subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public String getMainCategory() {
        return mainCategory;
    }

    public void setMainCategory(String mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getViews() {
        return views;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ItemEntity{" +
                "itemid='" + itemid + '\'' +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", mainCategory='" + mainCategory + '\'' +
                ", description='" + description + '\'' +
                ", userid='" + userid + '\'' +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", views=" + views +
                ", date=" + date +
                '}';
    }
}
