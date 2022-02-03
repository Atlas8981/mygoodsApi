package com.atlas.mygoods.model;


import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "item")
public class Item implements Comparable<Item> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;
    private String name;
    private String address;
    private String subCategory;
    private String mainCategory;
    private String description;
    private String userid;
    private String phone;

    @Autowired
    @ElementCollection(targetClass = Image.class)
    private List<Image> images;
    private int amount;
    private double price;
    //    @Column
    @ElementCollection(targetClass = Integer.class)
    @Autowired
    private List<String> viewers;
    private int views;
    private Date date;

    public Item() {
    }

    public Item(String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, int amount, double price, int views, Date date) {
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

    public Item(String id, String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, int amount, double price, int views, Date date) {
        this.id = id;
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

    public Item(String id, String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, List<Image> images, int amount, double price, List<String> viewers, int views, Date date) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.description = description;
        this.userid = userid;
        this.phone = phone;
        this.images = images;
        this.amount = amount;
        this.price = price;
        this.viewers = viewers;
        this.views = views;
        this.date = date;
    }

    public Item(String name, String address, String subCategory, String mainCategory, String description, String userid, String phone, List<Image> images, int amount, double price, List<String> viewers, int views, Date date) {
        this.name = name;
        this.address = address;
        this.subCategory = subCategory;
        this.mainCategory = mainCategory;
        this.description = description;
        this.userid = userid;
        this.phone = phone;
        this.images = images;
        this.amount = amount;
        this.price = price;
        this.viewers = viewers;
        this.views = views;
        this.date = date;
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


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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
        return "Item{" +
                "itemid='" + id + '\'' +
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


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Item)) return false;
        Item item = (Item) o;
        return amount == item.amount &&
                Double.compare(item.price, price) == 0 &&
                views == item.views &&
                Objects.equals(id, item.id) &&
                Objects.equals(name, item.name) &&
                Objects.equals(address, item.address) &&
                Objects.equals(subCategory, item.subCategory) &&
                Objects.equals(mainCategory, item.mainCategory) &&
                Objects.equals(description, item.description) &&
                Objects.equals(userid, item.userid) &&
                Objects.equals(phone, item.phone) &&
                Objects.equals(date, item.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, subCategory, mainCategory, description, userid, phone, amount, price, views, date);
    }

    @Override
    public int compareTo(Item o) {
        int compareView = ((Item) o).getViews();
        return compareView - this.views;
    }


}


