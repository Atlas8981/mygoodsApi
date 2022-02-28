package com.atlas.mygoods.repositories;


import com.atlas.mygoods.models.Item.Category.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MainCategoryRepository extends JpaRepository<MainCategory,Long> {
    MainCategory findByName(String name);
}
