package com.atlas.mygoods.models.Item.Category;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity(name = "MainCategory")
@Getter
@Setter
@AllArgsConstructor
@Table(name = "main_category")
@NoArgsConstructor
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name", nullable = false, unique = true)
    private String name;

    @ElementCollection
    @Column(name = "sub_category_id", unique = true)
    private List<String> subCategories;

    public MainCategory(String mainCategory) {
        this.name = mainCategory;
    }

    @Override
    public String toString() {
        return "MainCategory{" +
                "id=" + id +
                ", mainCategory='" + name + '\'' +
                '}';
    }
}
