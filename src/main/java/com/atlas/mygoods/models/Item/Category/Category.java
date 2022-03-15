package com.atlas.mygoods.models.Item.Category;

import com.atlas.mygoods.models.Item.Item;
import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Category")
@Getter
@Setter
@AllArgsConstructor
@Table(name = "category")
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "main_category", nullable = false)
    private String mainCategory;

    @Column(name = "sub_category", nullable = false, unique = true)
    private String subCategory;

    @Column(name = "count")
    private String count;

    @Column(name = "counts")
    private int counts = 0;

    @OneToMany
    @JsonBackReference
    private List<Item> items;

    public Category(String mainCategory, String subCategory) {
        this.mainCategory = mainCategory;
        this.subCategory = subCategory;
    }

    @Override
    public String toString() {
        return "Category{" +
                "id=" + id +
                ", mainCategory='" + mainCategory + '\'' +
                ", subCategory='" + subCategory + '\'' +
                ", items=" + items +
                '}';
    }
}
