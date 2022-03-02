package com.atlas.mygoods.models.Item;

import com.atlas.mygoods.models.Image;
import com.atlas.mygoods.models.Item.Category.Category;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity(name = "Item")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "items")
public class Item implements Serializable {

    public static final String ITEM_ID = "item_id";

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = ITEM_ID, updatable = false)
    private Long itemid;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "address", nullable = false)
    private String address;

//    @Column(name = "subCategory", nullable = false)
//    private String subCategory;
//
//    @Column(name = "mainCategory", nullable = false)
//    private String mainCategory;


    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "userid", nullable = false)
    private String userid;

    @Column(name = "phone", nullable = false)
    private String phone;

    @OneToMany
    private List<Image> images = new ArrayList<>();

    @Column(name = "amount", nullable = false)
    private int amount = 0;

    @Column(name = "price", nullable = false)
    private double price = 0;

    @ElementCollection
    private List<String> viewers = new ArrayList<>();

    @Column(name = "views", nullable = false)
    private int views = 0;

    @Column(name = "date", nullable = false)
    private Date date = new Date();


    public Item(String name, String address, List<Image> images, Category category, String description, String userid, String phone, double price, Date date) {
        this.name = name;
        this.address = address;
        this.images = images;
        this.description = description;
        this.userid = userid;
        this.price = price;
        this.phone = phone;
        this.views = 0;
        this.viewers = new ArrayList<>();
        this.category = category;
//        this.mainCategory = mainCategory;
//        this.subCategory = subCategory;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        if (images == null) {
            this.images = null;
        } else {
            this.images = Collections.unmodifiableList(images);
        }
    }

    public Long getItemid() {
        return itemid;
    }

    public void setItemid(Long itemid) {
        this.itemid = itemid;
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

    public List<String> getViewers() {
        return viewers;
    }

    public void setViewers(List<String> viewers) {
        this.viewers = viewers;
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
                "itemid=" + itemid +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", category=" + category +
                ", description='" + description + '\'' +
                ", userid='" + userid + '\'' +
                ", phone='" + phone + '\'' +
                ", images=" + images +
                ", amount=" + amount +
                ", price=" + price +
                ", viewers=" + viewers +
                ", views=" + views +
                ", date=" + date +
                '}';
    }
}


