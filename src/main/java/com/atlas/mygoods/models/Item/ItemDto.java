package com.atlas.mygoods.models.Item;

import lombok.*;

import java.io.Serializable;
import java.util.*;

@Data
@NoArgsConstructor
public class ItemDto implements Serializable {

    private String name;
    private String address;
    private Long categoryId;
    private String description;
    private String userid;
    private String phone;
    private int amount = 0;
    private double price = 0;
    private Date date = new Date();


    public ItemDto(String name, String address, Long category, String description, String userid, String phone, double price) {
        this.name = name;
        this.address = address;
        this.description = description;
        this.userid = userid;
        this.price = price;
        this.phone = phone;
        this.categoryId = category;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "ItemDto{" +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", categoryId=" + categoryId +
                ", description='" + description + '\'' +
                ", userid='" + userid + '\'' +
                ", phone='" + phone + '\'' +
                ", amount=" + amount +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}


