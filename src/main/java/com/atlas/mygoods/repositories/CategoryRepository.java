package com.atlas.mygoods.repositories;


import com.atlas.mygoods.models.Item.Category.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    @Query(value = "select Count(mygoods.items.item_id) \n" +
            "from mygoods.items \n" +
            "inner join mygoods.category_items \n" +
            "on mygoods.category_items.items_item_id = mygoods.items.item_id", nativeQuery = true)
    int something();
}
